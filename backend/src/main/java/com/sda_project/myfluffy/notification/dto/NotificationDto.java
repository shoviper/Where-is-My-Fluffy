package com.sda_project.myfluffy.notification.dto;

import com.sda_project.myfluffy.common.utils.enums.NotificationType;
import com.sda_project.myfluffy.user.dto.UserDto;
import lombok.Data;

@Data
public class NotificationDto {

    private int id;

    private String message;

    private NotificationType notificationType;

    private UserDto notificationOwner;
}
