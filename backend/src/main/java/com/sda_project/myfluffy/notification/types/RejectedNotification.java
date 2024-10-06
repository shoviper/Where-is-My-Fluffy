package com.sda_project.myfluffy.notification.types;

public class RejectedNotification extends Notification {

    @Override
    public void send() {
        System.out.println("Rejected notification sent: " + message);
    }
}
