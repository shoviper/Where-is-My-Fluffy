package com.sda_project.myfluffy.notification.impl;

import com.sda_project.myfluffy.notification.Notification;

public class SmsNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("Sending SMS Notification: " + message);
    }
}