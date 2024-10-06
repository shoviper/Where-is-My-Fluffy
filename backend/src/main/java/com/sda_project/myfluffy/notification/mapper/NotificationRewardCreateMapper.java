package com.sda_project.myfluffy.notification.mapper;

import com.sda_project.myfluffy.notification.dto.NotificationRewardCreateDto;
import com.sda_project.myfluffy.notification.model.Notification;

public class NotificationRewardCreateMapper {

    public static NotificationRewardCreateDto mapToNotificationRewardCreateDto(Notification notification,
            NotificationRewardCreateDto notificationRewardCreateDto) {
        notificationRewardCreateDto.setTitle(notification.getTitle());
        notificationRewardCreateDto.setMessage(notification.getMessage());
        notificationRewardCreateDto.setRewardAmountToPay(notification.getRewardAmountToPay());
        notificationRewardCreateDto.setNotificationType(notification.getNotificationType());
        return notificationRewardCreateDto;
    }

    public static Notification mapToNotification(NotificationRewardCreateDto notificationRewardCreateDto,
            Notification notification) {
        notification.setTitle(notificationRewardCreateDto.getTitle());
        notification.setMessage(notificationRewardCreateDto.getMessage());
        notification.setRewardAmountToPay(notificationRewardCreateDto.getRewardAmountToPay());
        notification.setNotificationType(notificationRewardCreateDto.getNotificationType());
        return notification;
    }

}
