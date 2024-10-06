package com.sda_project.myfluffy.user.service;

import com.sda_project.myfluffy.user.dto.UserPhoneUpdateDto;
import com.sda_project.myfluffy.user.dto.UserDto;
import com.sda_project.myfluffy.user.dto.UserLocationUpdateDto;

import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;

public interface IUserService {

    /**
     * @param oAuth2User - Get oAuth2User
     */
    void createUser(OAuth2User oAuth2User);

    /**
     * @param oAuth2User - Get oAuth2User
     * @return Accounts Details based on a given oAuth2User
     */
    UserDto fetchMe(OAuth2User oAuth2User);

    /**
     * @param email - Input Email
     * @return Users Details based on a given email
     */
    UserDto fetchUserByEmail(String email);

    /**
     * @param oAuth2User            - OAuth2User Object
     * @param userLocationUpdateDto - UserLocationUpdateDto Object;
     * @return boolean indicating if the update of User Location details is
     *         successful or not
     */
    boolean updateUserLocation(OAuth2User oAuth2User, UserLocationUpdateDto userLocationUpdateDto);

    /**
     * @param oAuth2User         - OAuth2User Object
     * @param userPhoneUpdateDto - Input Phone Number
     */
    boolean updatePhoneNumber(OAuth2User oAuth2User, UserPhoneUpdateDto userPhoneUpdateDto);

    /**
     * @param email - Input Email
     * @return boolean indicating if the delete of User details is successful or not
     */
    boolean deleteUser(String email);

    List<UserDto> fetchAllUsers(int page, int size, String sortBy, String sortDir);

}
