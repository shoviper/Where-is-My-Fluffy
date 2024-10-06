package com.sda_project.myfluffy.notification.dto;

import com.sda_project.myfluffy.common.utils.enums.NotificationType;

import lombok.Data;

@Data
public class NotificationRewardCreateDto {

    private String title;

    private String message;

    private NotificationType notificationType;

    private int notificationSenderId;

    private double rewardAmountToPay;

}
