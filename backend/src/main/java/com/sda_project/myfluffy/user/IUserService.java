package com.sda_project.myfluffy.user;

import com.sda_project.myfluffy.dto.UserDto;
import org.springframework.security.oauth2.core.user.OAuth2User;

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
}
