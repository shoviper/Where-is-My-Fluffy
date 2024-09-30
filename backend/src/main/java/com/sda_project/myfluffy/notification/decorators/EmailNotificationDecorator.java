package com.sda_project.myfluffy.notification.decorators;

import com.sda_project.myfluffy.notification.Notification;

public class EmailNotificationDecorator extends NotificationDecorator {
    public EmailNotificationDecorator(Notification decoratedNotification) {
        super(decoratedNotification);
    }

    @Override
    public void send(String message) {
        super.send(message);
        sendEmail(message);
    }

    private void sendEmail(String message) {
        System.out.println("Sending Email: " + message);
    }
}