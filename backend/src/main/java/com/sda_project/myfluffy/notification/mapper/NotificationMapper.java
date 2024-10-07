package com.sda_project.myfluffy.notification.mapper;

import com.sda_project.myfluffy.notification.dto.NotificationDto;
import com.sda_project.myfluffy.notification.model.Notification;

public class NotificationMapper {

    public static NotificationDto mapToNotificationDto(Notification notification, NotificationDto notificationDto) {
        notificationDto.setId(notification.getId());
        notificationDto.setTitle(notification.getTitle());
        notificationDto.setMessage(notification.getMessage());
        notificationDto.setNotificationType(notification.getNotificationType());
        notificationDto.setReward_amount_to_pay(notification.getRewardAmountToPay());
        return notificationDto;
    }

    public static Notification mapToNotification(NotificationDto notificationDto, Notification notification) {
        notification.setId(notificationDto.getId());
        notificationDto.setTitle(notification.getTitle());
        notification.setMessage(notificationDto.getMessage());
        notification.setNotificationType(notificationDto.getNotificationType());
        notification.setRewardAmountToPay(notificationDto.getReward_amount_to_pay());
        return notification;
    }
}
