package com.sda_project.myfluffy.notification.mapper;

import com.sda_project.myfluffy.notification.dto.NotificationDto;
import com.sda_project.myfluffy.notification.model.Notification;

public class NotificationMapper {

    public static NotificationDto mapToNotificationDto(Notification notification, NotificationDto notificationDto) {
        notificationDto.setId(notification.getId());
        notificationDto.setMessage(notification.getMessage());
        notificationDto.setNotificationType(notification.getNotificationType());
        return notificationDto;
    }

    public static Notification mapToNotification(NotificationDto notificationDto, Notification notification) {
        notification.setId(notificationDto.getId());
        notification.setMessage(notificationDto.getMessage());
        notification.setNotificationType(notificationDto.getNotificationType());
        return notification;
    }
}
