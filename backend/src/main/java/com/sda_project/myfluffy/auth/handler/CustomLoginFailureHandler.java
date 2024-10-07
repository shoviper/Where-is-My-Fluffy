package com.sda_project.myfluffy.auth.handler;

import com.sda_project.myfluffy.user.service.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final String frontendUrl;
    private final IUserService iUserService;

    public CustomLoginFailureHandler(@Value("${frontend.url}") String frontendUrl, IUserService iUserService) {
        this.frontendUrl = frontendUrl;
        this.iUserService = iUserService;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        logger.warn("Authentication failed: " + exception.getMessage());

        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", exception);

        request.setAttribute("error", "Authentication failed: " + exception.getMessage());

        String redirectUrl = UriComponentsBuilder.fromUriString(frontendUrl)
                .queryParam("error", "true")
                .toUriString();

        getRedirectStrategy().sendRedirect(request, response, frontendUrl);
    }
}