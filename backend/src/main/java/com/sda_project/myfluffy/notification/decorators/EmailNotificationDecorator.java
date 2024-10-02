package com.sda_project.myfluffy.notification.decorators;

import com.sda_project.myfluffy.notification.types.Notification;

public class EmailNotificationDecorator extends NotificationDecorator {

    public EmailNotificationDecorator(Notification notification) {
        super(notification);
    }

    @Override
    public void send() {
        super.send();
        sendEmail();
    }

    private void sendEmail() {
        System.out.println("Sending Email Notification: " + super.getMessage());
    }
}