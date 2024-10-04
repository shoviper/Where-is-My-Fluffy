package com.sda_project.myfluffy.notification.decorators;

import com.sda_project.myfluffy.notification.Notification;

public abstract class NotificationDecorator implements Notification {

    protected Notification decoratedNotification;

    public NotificationDecorator(Notification decoratedNotification) {
        this.decoratedNotification = decoratedNotification;
    }

    @Override
    public void send(String message) {
        decoratedNotification.send(message);
    }
}

