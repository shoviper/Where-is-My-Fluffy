package com.sda_project.myfluffy.notification.factory.pet;

import com.sda_project.myfluffy.common.utils.enums.NotificationType;
import com.sda_project.myfluffy.notification.factory.NotificationFactory;
import com.sda_project.myfluffy.notification.types.CreatedNotification;
import com.sda_project.myfluffy.notification.types.ModifiedNotification;
import com.sda_project.myfluffy.notification.types.Notification;
import com.sda_project.myfluffy.notification.types.RemovedNotification;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PetNotificationFactory extends NotificationFactory {

    private String title;
    private String message;

    @Override
    protected Notification createNotification(NotificationType type) {
        Notification notification = null;

        if (type.equals(NotificationType.NOTIFICATION_CREATED)) {
            notification = new CreatedNotification();
            notification.setName("Pet Created");
            notification.setTitle(title);
            notification.setMessage(message);
            notification.setType(type);
        } else if (type.equals(NotificationType.NOTIFICATION_MODIFIED)) {
            notification = new ModifiedNotification();
            notification.setName("Pet Modified");
            notification.setTitle(title);
            notification.setMessage(message);
            notification.setType(type);
        } else if (type.equals(NotificationType.NOTIFICATION_REMOVED)) {
            notification = new RemovedNotification();
            notification.setName("Pet Removed");
            notification.setTitle(title);
            notification.setMessage(message);
            notification.setType(type);
        } else if (type.equals(NotificationType.NOTIFICATION_PENDING)) {
            notification = new RemovedNotification();
            notification.setName("Pet Verification Pending");
            notification.setTitle(title);
            notification.setMessage(message);
            notification.setType(type);
        }

        return notification;
    }
}