package com.sda_project.myfluffy.user;

import com.sda_project.myfluffy.dto.UserDto;
import com.sda_project.myfluffy.exception.ResourceNotFoundException;
import com.sda_project.myfluffy.exception.UnauthorizedException;
import com.sda_project.myfluffy.exception.UserAlreadyExistsException;
import com.sda_project.myfluffy.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;

    /**
     * @param oAuth2User - oAuth2User Object
     */
    @Override
    public void createUser(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("Customer already registered with given Email");
        }

        User user = createUserFromOAuth2(oAuth2User);
    }

    /**
     * @param oAuth2User - Get oAuth2User
     * @return Accounts Details based on a given oAuth2User
     */
    @Override
    public UserDto fetchMe(OAuth2User oAuth2User) {
        if (oAuth2User == null) {
            throw new UnauthorizedException("Unauthorized, Please login to get access.");
        }

        String email = oAuth2User.getAttribute("email");
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

        return UserMapper.mapToUserDto(user, new UserDto());
    }

    private User createUserFromOAuth2(OAuth2User oAuth2User) {
        User user = new User();
        user.setName(oAuth2User.getAttribute("name"));
        user.setEmail(oAuth2User.getAttribute("email"));
        // Set other attributes as needed, e.g., location, phone if provided by OAuth2 provider

        return userRepository.save(user);
    }
}
