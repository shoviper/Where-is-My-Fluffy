package com.sda_project.myfluffy.notification.service;

import com.sda_project.myfluffy.common.utils.enums.NotificationType;
import com.sda_project.myfluffy.notification.dto.NotificationCreateDto;
import com.sda_project.myfluffy.notification.dto.NotificationCreateResponseDto;
import com.sda_project.myfluffy.notification.dto.NotificationDto;
import com.sda_project.myfluffy.user.model.User;

import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;

public interface INotificationService {

    NotificationCreateResponseDto createNotification(User user, NotificationCreateDto notificationCreateDto);

    NotificationDto fetchNotificationById(int id);

    List<NotificationDto> fetchMyNotification(OAuth2User oAuth2User);

    List<NotificationDto> getAllNotifications();

    boolean deleteNotification(int id);

    NotificationDto updateNotificationImageBase64(int notificationId, String base64Image);

    boolean updateNotificationStatus(int notificationId, NotificationType newType);

}
