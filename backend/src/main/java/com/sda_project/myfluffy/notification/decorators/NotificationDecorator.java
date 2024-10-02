package com.sda_project.myfluffy.notification.decorators;

import com.sda_project.myfluffy.notification.types.Notification;

public abstract class NotificationDecorator extends Notification {

    protected Notification notification;

    public NotificationDecorator(Notification notification) {
        this.notification = notification;
    }

    @Override
    public void send() {
        notification.send();
    }

    @Override
    public String getMessage() {
        return notification.getMessage();
    }
}

