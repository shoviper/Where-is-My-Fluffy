package com.sda_project.myfluffy.notification.dto;

import com.sda_project.myfluffy.common.utils.enums.NotificationType;
import lombok.Data;

@Data
public class NotificationCreateDto {

    private String title;

    private String message;

    private NotificationType notificationType;
}
