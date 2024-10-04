package com.sda_project.myfluffy.notification.types;

public class ModifiedNotification extends Notification {

    @Override
    public void send() {
        System.out.println("Modified notification sent: " + message);
    }
}
