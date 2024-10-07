package com.sda_project.myfluffy.notification.service;

import com.sda_project.myfluffy.common.exception.InvalidStatusException;
import com.sda_project.myfluffy.common.exception.ResourceNotFoundException;
import com.sda_project.myfluffy.common.exception.UnauthorizedException;
import com.sda_project.myfluffy.common.utils.enums.NotificationType;
import com.sda_project.myfluffy.notification.dto.NotificationCreateDto;
import com.sda_project.myfluffy.notification.dto.NotificationCreateResponseDto;
import com.sda_project.myfluffy.notification.dto.NotificationDto;
import com.sda_project.myfluffy.notification.dto.NotificationRewardCreateDto;
import com.sda_project.myfluffy.notification.mapper.NotificationCreateResponseMapper;
import com.sda_project.myfluffy.notification.mapper.NotificationMapper;
import com.sda_project.myfluffy.notification.model.Notification;
import com.sda_project.myfluffy.notification.model.NotificationImage;
import com.sda_project.myfluffy.notification.repository.NotificationImageRepository;
import com.sda_project.myfluffy.notification.repository.NotificationRepository;
import com.sda_project.myfluffy.user.dto.UserDto;
import com.sda_project.myfluffy.user.mapper.UserMapper;
import com.sda_project.myfluffy.user.model.User;
import com.sda_project.myfluffy.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements INotificationService {

    private NotificationRepository notificationRepository;
    private UserRepository userRepository;
    private NotificationImageRepository notificationImageRepository;

    @Override
    @Transactional
    public NotificationCreateResponseDto createNotification(User user,
            NotificationCreateDto notificationCreateDto) {

        Notification notification = new Notification();
        notification.setTitle(notificationCreateDto.getTitle());
        notification.setMessage(notificationCreateDto.getMessage());
        notification.setNotificationType(notificationCreateDto.getNotificationType());
        notification.setNotificationOwner(user);

        notificationRepository.save(notification);

        return NotificationCreateResponseMapper.mapToNotificationCreateResponseDto(notification,
                new NotificationCreateResponseDto());
    }

    @Override
    @Transactional
    public NotificationCreateResponseDto createNotificationReward(
            User user,
            NotificationRewardCreateDto notificationRewardCreateDto) {
        User sender = userRepository.findById(notificationRewardCreateDto.getNotificationSenderId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "email",
                        Integer.toString(notificationRewardCreateDto.getNotificationSenderId())));

        Notification notification = new Notification();
        notification.setTitle(notificationRewardCreateDto.getTitle());
        notification.setMessage(notificationRewardCreateDto.getMessage());
        notification.setNotificationType(notificationRewardCreateDto.getNotificationType());
        notification.setRewardAmountToPay(notificationRewardCreateDto.getRewardAmountToPay());
        notification.setNotificationOwner(user);
        notification.setNotificationSender(sender);

        notificationRepository.save(notification);

        return NotificationCreateResponseMapper.mapToNotificationCreateResponseDto(notification,
                new NotificationCreateResponseDto());
    }

    private User getAuthenticatedUser(OAuth2User oAuth2User) {
        if (oAuth2User == null) {
            throw new UnauthorizedException("User must be authenticated");
        }
        String email = oAuth2User.getAttribute("email");
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    @Override
    public NotificationDto fetchNotificationById(int id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet", "id", Integer.toString(id)));
        return mapNotificationToDto(notification);
    }

    @Override
    public List<NotificationDto> fetchMyNotification(OAuth2User oAuth2User) {
        User owner = getAuthenticatedUser(oAuth2User);
        List<Notification> notifications = owner.getMyNotifications();
        return notifications.stream()
                .map(this::mapNotificationToDto)
                .collect(Collectors.toList());
    }

    private NotificationDto mapNotificationToDto(Notification notification) {
        NotificationDto notificationDto = NotificationMapper.mapToNotificationDto(notification, new NotificationDto());

        UserDto ownerDto = UserMapper.mapToUserDto(notification.getNotificationOwner(), new UserDto());

        notificationDto.setNotificationOwner(ownerDto);

        if (notification.getNotificationSender() != null) {
            UserDto senderDto = UserMapper.mapToUserDto(notification.getNotificationSender(), new UserDto());
            notificationDto.setNotificationSender(senderDto);
        }

        if (notification.getNotificationImage() != null) {
            Optional<NotificationImage> notificationImage = notificationImageRepository
                    .findById(notification.getNotificationImage().getId());

            notificationImage.ifPresent(image -> notificationDto.setImage(image.getImageBase64()));
        } else {
            notificationDto.setImage(null);
        }

        return notificationDto;
    }

    @Override
    public List<NotificationDto> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(this::mapNotificationToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean deleteNotification(int id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pet", "id", Integer.toString(id)));
        notificationRepository.deleteById(notification.getId());
        return true;
    }

    @Override
    public NotificationDto updateNotificationImageBase64(int notificationId, String base64Image) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Notification", "id", Integer.toString(notificationId)));
        NotificationImage notificationimage = new NotificationImage();
        notificationimage.setImageBase64(base64Image);
        NotificationImage createdNotificationImage = notificationImageRepository.save(notificationimage);

        notification.setNotificationImage(createdNotificationImage);
        notificationRepository.save(notification);

        return NotificationMapper.mapToNotificationDto(notification, new NotificationDto());
    }

    @Override
    public boolean updateNotificationStatus(int notificationId, NotificationType newType) {
        boolean isUpdated = false;

        if (newType != NotificationType.NOTIFICATION_APPROVED && newType != NotificationType.NOTIFICATION_REJECTED) {
            throw new InvalidStatusException("Update Notification Type must be in APPROVED or REJECTED type.");
        }

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Notification", "id", Integer.toString(notificationId)));

        if (newType == NotificationType.NOTIFICATION_APPROVED) {
            notification.setNotificationType(newType);

            User sender = notification.getNotificationSender();
            User reciever = notification.getNotificationOwner();

            NotificationCreateDto notificationCreateDtoSender = new NotificationCreateDto();
            notificationCreateDtoSender.setTitle("Approved pet found!");
            notificationCreateDtoSender
                    .setMessage("Dear " + sender.getName()
                            + ", that pet you found has been approved. waiting to receive the reward from "
                            + reciever.getName() + " , Reward amount: " + notification.getRewardAmountToPay()
                            + " baht.");
            notificationCreateDtoSender.setNotificationType(NotificationType.NOTIFICATION_APPROVED);
            this.createNotification(sender, notificationCreateDtoSender);

            isUpdated = true;
        }

        if (newType == NotificationType.NOTIFICATION_REJECTED) {
            notification.setNotificationType(newType);

            User sender = notification.getNotificationSender();

            NotificationCreateDto notificationCreateDtoSender = new NotificationCreateDto();
            notificationCreateDtoSender.setTitle("Rejected pet found!");
            notificationCreateDtoSender
                    .setMessage("Dear " + sender.getName() + ", that pet you found has been rejected.");
            notificationCreateDtoSender.setNotificationType(NotificationType.NOTIFICATION_REJECTED);
            this.createNotification(sender, notificationCreateDtoSender);

            isUpdated = true;
        }

        notificationRepository.save(notification);

        return isUpdated;
    }

}
