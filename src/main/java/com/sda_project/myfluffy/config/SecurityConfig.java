package com.sda_project.myfluffy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").permitAll();
                    auth.anyRequest().authenticated();
                })
                .oauth2Login(withDefaults())
                .formLogin(withDefaults())
                .logout(logout -> logout
                    .logoutUrl("/logout")                       // The URL to trigger logout
                    .logoutSuccessUrl("/")                      // Redirect after logout (to home page)
                    .invalidateHttpSession(true)                // Invalidate session on logout
                    .deleteCookies("JSESSIONID")                // Delete session cookie
                )
                .build();
    }

}
