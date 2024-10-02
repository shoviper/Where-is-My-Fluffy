package com.sda_project.myfluffy.notification.factory;

import com.sda_project.myfluffy.common.utils.enums.NotificationType;
import com.sda_project.myfluffy.notification.decorators.ClientNotificationDecorator;
import com.sda_project.myfluffy.notification.decorators.EmailNotificationDecorator;
import com.sda_project.myfluffy.notification.decorators.SmsNotificationDecorator;
import com.sda_project.myfluffy.notification.types.Notification;

public abstract class NotificationFactory {

    public Notification sendNotification(NotificationType type) {
        Notification notification = createNotification(type);
        System.out.println("--- Sending a " + notification.getName() + " ---");

        Notification decoratedNotification = new EmailNotificationDecorator(new SmsNotificationDecorator(new ClientNotificationDecorator(notification)));
        decoratedNotification.send();

        return notification;
    }

    protected abstract Notification createNotification(NotificationType type);
}
