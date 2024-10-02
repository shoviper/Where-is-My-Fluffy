package com.sda_project.myfluffy.notification.types;

public class CreatedNotification extends Notification {

    @Override
    public void send() {
        System.out.println("Created notification sent: " + message);
    }
}
