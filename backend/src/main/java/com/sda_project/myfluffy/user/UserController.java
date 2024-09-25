package com.sda_project.myfluffy.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/profile")
    public Map<String, String> profile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> userInfo = new HashMap<>();

        if (authentication != null && authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

            Map<String, Object> attributes = oauth2User.getAttributes();
            for (Map.Entry<String, Object> entry : attributes.entrySet()) {
                userInfo.put(entry.getKey(), entry.getValue().toString());
            }
        }

        return userInfo;
    }

}