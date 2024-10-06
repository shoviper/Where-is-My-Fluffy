package com.sda_project.myfluffy.notification.mapper;

import com.sda_project.myfluffy.notification.dto.NotificationCreateResponseDto;
import com.sda_project.myfluffy.notification.model.Notification;

public class NotificationCreateResponseMapper {

    public static NotificationCreateResponseDto mapToNotificationCreateResponseDto(Notification notification, NotificationCreateResponseDto notificationCreateResponseDto) {
        notificationCreateResponseDto.setId(notification.getId());
        notificationCreateResponseDto.setTitle(notification.getTitle());
        return notificationCreateResponseDto;
    }

    public static Notification mapToNotification(NotificationCreateResponseDto notificationCreateResponseDto, Notification notification) {
        notification.setId(notificationCreateResponseDto.getId());
        notification.setTitle(notificationCreateResponseDto.getTitle());
        return notification;
    }
}
