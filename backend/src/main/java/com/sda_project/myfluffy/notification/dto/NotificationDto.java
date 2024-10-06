package com.sda_project.myfluffy.notification.dto;

import com.sda_project.myfluffy.common.utils.enums.NotificationType;
import com.sda_project.myfluffy.post.dto.PostDto;
import com.sda_project.myfluffy.user.dto.UserDto;
import lombok.Data;

@Data
public class NotificationDto {

    private int id;

    private String title;

    private String message;

    private NotificationType notificationType;

    private double reward_amount_to_pay;

    private UserDto notificationOwner;

    private UserDto notificationSender;

    private PostDto notificationPost;

    private String image;
}
