package com.sda_project.myfluffy.notification.service;

import com.sda_project.myfluffy.common.exception.ResourceNotFoundException;
import com.sda_project.myfluffy.common.exception.UnauthorizedException;
import com.sda_project.myfluffy.notification.dto.NotificationCreateDto;
import com.sda_project.myfluffy.notification.dto.NotificationDto;
import com.sda_project.myfluffy.notification.mapper.NotificationMapper;
import com.sda_project.myfluffy.notification.model.Notification;
import com.sda_project.myfluffy.notification.repository.NotificationRepository;
import com.sda_project.myfluffy.user.dto.UserDto;
import com.sda_project.myfluffy.user.mapper.UserMapper;
import com.sda_project.myfluffy.user.model.User;
import com.sda_project.myfluffy.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements INotificationService {

    private NotificationRepository notificationRepository;
    private UserRepository userRepository;

    @Override
    @Transactional
    public void createNotification(User user, NotificationCreateDto notificationCreateDto) {
        Notification notification = new Notification();
        notification.setMessage(notificationCreateDto.getMessage());
        notification.setNotificationType(notificationCreateDto.getNotificationType());
        notification.setNotificationOwner(user);

        notificationRepository.save(notification);
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
        UserDto ownerDto = UserMapper.mapToUserDto(notification.getNotificationOwner(), new UserDto());

        NotificationDto notificationDto = NotificationMapper.mapToNotificationDto(notification, new NotificationDto());
        notificationDto.setNotificationOwner(ownerDto);
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
}
