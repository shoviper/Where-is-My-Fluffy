package com.sda_project.myfluffy.common.config;

import com.sda_project.myfluffy.notification.Notification;
import com.sda_project.myfluffy.notification.decorators.EmailNotificationDecorator;
import com.sda_project.myfluffy.notification.decorators.SmsNotificationDecorator;
import com.sda_project.myfluffy.notification.impl.ProgramNotification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

    @Bean
    public Notification notification() {
        return new EmailNotificationDecorator(new SmsNotificationDecorator(new ProgramNotification()));
    }
}