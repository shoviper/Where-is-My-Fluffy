package com.sda_project.myfluffy.user.service;

import com.sda_project.myfluffy.user.dto.UserPhoneUpdateDto;
import com.sda_project.myfluffy.user.dto.UserDto;
import com.sda_project.myfluffy.user.dto.UserLocationUpdateDto;
import com.sda_project.myfluffy.common.exception.ResourceNotFoundException;
import com.sda_project.myfluffy.common.exception.UnauthorizedException;
import com.sda_project.myfluffy.common.exception.UserAlreadyExistsException;
import com.sda_project.myfluffy.common.utils.enums.NotificationType;
import com.sda_project.myfluffy.geolocation.dto.LocationCreateDto;
import com.sda_project.myfluffy.geolocation.dto.LocationDto;
import com.sda_project.myfluffy.geolocation.model.Location;
import com.sda_project.myfluffy.geolocation.service.ILocationService;
import com.sda_project.myfluffy.notification.dto.NotificationCreateDto;
import com.sda_project.myfluffy.notification.service.INotificationService;
import com.sda_project.myfluffy.payment.dto.PaymentDto;
import com.sda_project.myfluffy.user.mapper.UserMapper;
import com.sda_project.myfluffy.pet.repository.PetRepository;
import com.sda_project.myfluffy.user.model.User;
import com.sda_project.myfluffy.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;
    private PetRepository petRepository;
    private ILocationService iLocationService;
    private INotificationService iNotificationService;

    /**
     * @param email - String email
     * @return User Object
     */
    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    /**
     * @param oAuth2User - OAuth2User Object
     */
    @Override
    @Transactional
    public void createUser(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("User already registered with given Email, Log In ...");
        }

        createUserFromOAuth2(oAuth2User);
    }

    /**
     * @param oAuth2User - OAuth2User Object
     */
    private void createUserFromOAuth2(OAuth2User oAuth2User) {
        User user = new User();
        user.setName(oAuth2User.getAttribute("name"));
        user.setEmail(oAuth2User.getAttribute("email"));
        user.setUserImage(oAuth2User.getAttribute("picture"));
        user.setBalance(10000.00);

        userRepository.save(user);
    }

    /**
     * @param oAuth2User - Get OAuth2User
     * @return Accounts Details based on a given oAuth2User
     */
    @Override
    public UserDto fetchMe(OAuth2User oAuth2User) {
        if (oAuth2User == null) {
            throw new UnauthorizedException("Unauthorized, Please login to get access.");
        }

        String email = oAuth2User.getAttribute("email");
        User user = getUserByEmail(email);

        UserDto userDto = UserMapper.mapToUserDto(user, new UserDto());
        if (user.getUserLocation() != null) {
            LocationDto locationDto = iLocationService.fetchLocationById(user.getUserLocation().getId());
            userDto.setUserLocation(locationDto);
        }

        return userDto;
    }

    /**
     * @param email - Input Email
     * @return Users Details based on a given email
     */
    @Override
    public UserDto fetchUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", email));

        UserDto userDto = UserMapper.mapToUserDto(user, new UserDto());
        if (user.getUserLocation() != null) {
            LocationDto locationDto = iLocationService.fetchLocationById(user.getUserLocation().getId());
            userDto.setUserLocation(locationDto);
        }

        return userDto;
    }

    /**
     * @param oAuth2User         - OAuth2User Object
     * @param userPhoneUpdateDto - Input Phone Number
     */
    @Override
    @Transactional
    public boolean updatePhoneNumber(OAuth2User oAuth2User, UserPhoneUpdateDto userPhoneUpdateDto) {
        boolean isUpdated = false;

        if (oAuth2User == null) {
            throw new UnauthorizedException("Unauthorized, Please login to get access.");
        }

        String email = oAuth2User.getAttribute("email");
        User user = getUserByEmail(email);

        String newPhoneNumber = userPhoneUpdateDto.getPhone();

        if (newPhoneNumber != null && !newPhoneNumber.isEmpty()) {
            user.setPhone(newPhoneNumber);
            userRepository.save(user);
            isUpdated = true;
        }

        return isUpdated;
    }

    /**
     * @param oAuth2User            - OAuth2User Object
     * @param userLocationUpdateDto - UserLocationUpdateDto Object;
     * @return boolean indicating if the update of User Location details is
     *         successful or not
     */
    @Override
    public boolean updateUserLocation(OAuth2User oAuth2User, UserLocationUpdateDto userLocationUpdateDto) {
        boolean isUpdated = false;

        if (oAuth2User == null) {
            throw new UnauthorizedException("Unauthorized, Please login to get access.");
        }

        String email = oAuth2User.getAttribute("email");
        User user = getUserByEmail(email);

        String updatedLocation = userLocationUpdateDto.getLocation();

        if (updatedLocation != null && !updatedLocation.isEmpty()) {
            Location location = createLocationForUser(updatedLocation);
            user.setUserLocation(location);
            userRepository.save(user);
            isUpdated = true;
        }

        return isUpdated;
    }

    private Location createLocationForUser(String address) {
        LocationCreateDto locationCreateDto = new LocationCreateDto();
        locationCreateDto.setAddress(address);
        return iLocationService.createLocation(locationCreateDto);
    }

    /**
     * @param email - Input Email
     * @return boolean indicating if the delete of User details is successful or not
     */
    @Override
    @Transactional
    public boolean deleteUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", email));
        petRepository.deleteByPetOwner(user);
        userRepository.deleteById(user.getId());
        return true;
    }

    /**
     * @return List of all users
     */
    @Override
    public List<UserDto> fetchAllUsers(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> usersPage = userRepository.findAll(pageable);

        return usersPage.getContent().stream()
                .map(this::mapToUser)
                .collect(Collectors.toList());
    }

    private UserDto mapToUser(User user) {
        UserDto userDto = UserMapper.mapToUserDto(user, new UserDto());

        if (user.getUserLocation() != null) {
            LocationDto locationDto = iLocationService.fetchLocationById(user.getUserLocation().getId());
            userDto.setUserLocation(locationDto);
        }

        return userDto;
    }

    @Override
    @Transactional
    public boolean updateBalance(PaymentDto paymentDto) {
        boolean isUpdated = false;

        User sender = userRepository.findById(paymentDto.getSenderId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", Integer.toString(paymentDto.getSenderId())));

        User receiver = userRepository.findById(paymentDto.getReceiverId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", Integer.toString(paymentDto.getReceiverId())));

        if (paymentDto.getAmount() >= 0) {
            sender.setBalance(sender.getBalance() - paymentDto.getAmount());
            receiver.setBalance(receiver.getBalance() + paymentDto.getAmount());
            isUpdated = true;

            NotificationCreateDto notificationCreateDtoSender = new NotificationCreateDto();
            notificationCreateDtoSender.setTitle("Transfer Money");
            notificationCreateDtoSender
                    .setMessage("Send " + paymentDto.getAmount() + " baht to " + receiver.getName() + ".");
            notificationCreateDtoSender.setNotificationType(NotificationType.NOTIFICATION_APPROVED);
            iNotificationService.createNotification(sender, notificationCreateDtoSender);

            NotificationCreateDto notificationCreateDtoReceiver = new NotificationCreateDto();
            notificationCreateDtoReceiver.setTitle("Receive Money");
            notificationCreateDtoReceiver
                    .setMessage("Receive " + paymentDto.getAmount() + " baht from " + sender.getName() + ".");
            notificationCreateDtoReceiver.setNotificationType(NotificationType.NOTIFICATION_APPROVED);
            iNotificationService.createNotification(receiver, notificationCreateDtoReceiver);
        }

        return isUpdated;

    }
}
