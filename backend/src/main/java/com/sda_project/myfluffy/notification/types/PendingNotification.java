package com.sda_project.myfluffy.notification.types;

public class PendingNotification extends Notification {

    @Override
    public void send() {
        System.out.println("Pending notification sent: " + message);
    }
}
