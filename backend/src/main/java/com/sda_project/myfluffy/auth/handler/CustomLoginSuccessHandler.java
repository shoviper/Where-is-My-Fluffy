package com.sda_project.myfluffy.auth.handler;

import com.sda_project.myfluffy.user.service.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.IOException;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final String frontendUrl;
    private final IUserService iUserService;

    public CustomLoginSuccessHandler(@Value("${frontend.url}") String frontendUrl, IUserService iUserService) {
        this.frontendUrl = frontendUrl;
        this.iUserService = iUserService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        try {
            iUserService.createUser(oAuth2User);
        } catch (RuntimeException e) {
            // User already exists, do nothing
            System.out.println("Error: " + e);
        }

        getRedirectStrategy().sendRedirect(request, response, frontendUrl);
    }
}