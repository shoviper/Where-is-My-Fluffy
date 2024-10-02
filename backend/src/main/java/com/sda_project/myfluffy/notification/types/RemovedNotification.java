package com.sda_project.myfluffy.notification.types;

public class RemovedNotification extends Notification {

    @Override
    public void send() {
        System.out.println("Removed notification sent: " + message);
    }
}
