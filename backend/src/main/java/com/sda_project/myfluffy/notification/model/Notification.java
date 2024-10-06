package com.sda_project.myfluffy.notification.model;

import com.sda_project.myfluffy.common.utils.enums.NotificationType;
import com.sda_project.myfluffy.pet.model.PetImage;
import com.sda_project.myfluffy.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "message")
    private String message;

    @Column(name = "reward_amount_to_pay")
    private double rewardAmountToPay;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private NotificationType notificationType;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "notification_image_id")
    private NotificationImage notificationImage;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "owner_id")
    private User notificationOwner;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "sender_id")
    private User notificationSender;

}
