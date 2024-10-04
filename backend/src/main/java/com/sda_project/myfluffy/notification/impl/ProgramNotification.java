package com.sda_project.myfluffy.notification.impl;

import com.sda_project.myfluffy.notification.Notification;

public class ProgramNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("Program Notification: " + message);
    }
}