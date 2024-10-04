package com.sda_project.myfluffy.notification.repository;

import com.sda_project.myfluffy.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository  extends JpaRepository<Notification, Integer> {
}