package com.sda_project.myfluffy.notification.types;

public class ApprovedNotification extends Notification {

    @Override
    public void send() {
        System.out.println("Approved notification sent: " + message);
    }
}
