package com.sda_project.myfluffy.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello, Home!!";
    }

    @GetMapping("/secured")
    public String secured(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Map<String, Object> sessionAttributes = new HashMap<>();
        Enumeration<String> attributeNames = session.getAttributeNames();

        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            sessionAttributes.put(attributeName, session.getAttribute(attributeName));
        }

        return sessionAttributes.toString();
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler()  .logout(request, response, auth);
        }
        return "You have been logged out successfully!";
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
