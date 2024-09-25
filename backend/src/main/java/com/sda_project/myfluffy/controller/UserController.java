package com.sda_project.myfluffy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/user")
    public ResponseEntity<?> getUser(Authentication authentication) {
        if (authentication != null) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) authentication).getPrincipal();
            Map<String, Object> userDetails = new HashMap<>();
            userDetails.put("name", oAuth2User.getAttribute("name"));
            userDetails.put("email", oAuth2User.getAttribute("email"));
            return ResponseEntity.ok(userDetails);
        }

        return ResponseEntity.badRequest().body("No authenticated user found");
    }

    @GetMapping("/profile")
    public Map<String, String> profile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> userInfo = new HashMap<>();

        if (authentication != null && authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            userInfo.put("name", oauth2User.getAttribute("name"));
            userInfo.put("email", oauth2User.getAttribute("email"));
        }

        return userInfo;
    }

}