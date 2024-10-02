package com.sda_project.myfluffy.notification.decorators;

import com.sda_project.myfluffy.notification.types.Notification;

public class ClientNotificationDecorator extends NotificationDecorator {

    public ClientNotificationDecorator(Notification notification) {
        super(notification);
    }

    @Override
    public void send() {
        super.send();
        sendProgramNotification();
    }

    private void sendProgramNotification() {
        System.out.println("Sending Program Notification: " + super.getMessage());
    }
}
