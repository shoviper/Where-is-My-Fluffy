package com.sda_project.myfluffy.notification.repository;

import com.sda_project.myfluffy.notification.model.NotificationImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationImageRepository extends JpaRepository<NotificationImage, Integer> {
}
