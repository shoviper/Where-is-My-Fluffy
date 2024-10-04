package com.sda_project.myfluffy.notification.decorators;

import com.sda_project.myfluffy.notification.types.Notification;

public class SmsNotificationDecorator extends NotificationDecorator {

    public SmsNotificationDecorator(Notification notification) {
        super(notification);
    }

    @Override
    public void send() {
        super.send();
        sendSMS();
    }

    private void sendSMS() {
        System.out.println("Sending SMS Notification: " + super.getMessage());
    }
}
