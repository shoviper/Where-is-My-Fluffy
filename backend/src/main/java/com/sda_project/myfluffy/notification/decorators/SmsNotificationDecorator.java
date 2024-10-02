package com.sda_project.myfluffy.notification.decorators;

import com.sda_project.myfluffy.notification.Notification;

public class SmsNotificationDecorator extends NotificationDecorator {

    public SmsNotificationDecorator(Notification decoratedNotification) {
        super(decoratedNotification);
    }

    @Override
    public void send(String message) {
        super.send(message);
        sendSms(message);
    }

    private void sendSms(String message) {
        System.out.println("Sending SMS: " + message);
    }
}
