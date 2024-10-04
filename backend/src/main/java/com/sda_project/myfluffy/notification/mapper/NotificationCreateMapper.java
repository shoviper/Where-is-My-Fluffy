package com.sda_project.myfluffy.notification.mapper;

import com.sda_project.myfluffy.notification.dto.NotificationCreateDto;
import com.sda_project.myfluffy.notification.model.Notification;

public class NotificationCreateMapper {

    public static NotificationCreateDto mapToNotificationCreateDto(Notification notification, NotificationCreateDto notificationCreateDto) {
        notificationCreateDto.setMessage(notification.getMessage());
        notificationCreateDto.setNotificationType(notification.getNotificationType());
        return notificationCreateDto;
    }

    public static Notification mapToNotification(NotificationCreateDto notificationCreateDto, Notification notification) {
        notification.setMessage(notificationCreateDto.getMessage());
        notification.setNotificationType(notificationCreateDto.getNotificationType());
        return notification;
    }
}