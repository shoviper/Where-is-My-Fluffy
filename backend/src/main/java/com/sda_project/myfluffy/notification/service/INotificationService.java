package com.sda_project.myfluffy.notification.service;

import com.sda_project.myfluffy.notification.dto.NotificationCreateDto;
import com.sda_project.myfluffy.notification.dto.NotificationDto;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;

public interface INotificationService {

    void createNotification(NotificationCreateDto notificationCreateDto);

    NotificationDto fetchNotificationById(int id);

    List<NotificationDto> fetchMyNotification(OAuth2User oAuth2User);

    List<NotificationDto> getAllNotifications();

    boolean deleteNotification(int id);
}
