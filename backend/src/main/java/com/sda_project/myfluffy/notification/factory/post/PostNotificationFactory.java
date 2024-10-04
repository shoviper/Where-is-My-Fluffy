package com.sda_project.myfluffy.notification.factory.post;

import com.sda_project.myfluffy.common.utils.enums.NotificationType;
import com.sda_project.myfluffy.notification.factory.NotificationFactory;
import com.sda_project.myfluffy.notification.types.CreatedNotification;
import com.sda_project.myfluffy.notification.types.ModifiedNotification;
import com.sda_project.myfluffy.notification.types.Notification;
import com.sda_project.myfluffy.notification.types.RemovedNotification;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostNotificationFactory extends NotificationFactory {

    private String message;

    @Override
    protected Notification createNotification(NotificationType type) {
        Notification notification = null;

        if (type.equals(NotificationType.NOTIFICATION_CREATED)) {
            notification = new CreatedNotification();
            notification.setName("Post Created");
            notification.setMessage(message);
            notification.setType(type);
        } else if (type.equals(NotificationType.NOTIFICATION_MODIFIED)) {
            notification = new ModifiedNotification();
            notification.setName("Post Modified");
            notification.setMessage(message);
            notification.setType(type);
        } else if (type.equals(NotificationType.NOTIFICATION_REMOVED)) {
            notification = new RemovedNotification();
            notification.setName("Post Removed");
            notification.setMessage(message);
            notification.setType(type);
        }

        return notification;
    }
}
