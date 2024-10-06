package com.sda_project.myfluffy.common.observers.listeners.pet;

import com.sda_project.myfluffy.common.observers.events.pet.PetFounderChangeEvent;
import com.sda_project.myfluffy.common.utils.enums.NotificationType;
import com.sda_project.myfluffy.notification.dto.NotificationCreateDto;
import com.sda_project.myfluffy.notification.factory.NotificationFactory;
import com.sda_project.myfluffy.notification.factory.pet.PetNotificationFactory;
import com.sda_project.myfluffy.notification.service.INotificationService;
import com.sda_project.myfluffy.pet.model.Pet;
import com.sda_project.myfluffy.user.model.User;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PetFounderChangeListener {

    private INotificationService iNotificationService;

    @EventListener
    public void handlePetFounderChange(PetFounderChangeEvent event) {
        Pet pet = event.getPet();
        User founder = pet.getFounder();
        User petOwner = pet.getPetOwner();

        sendFounderNotification(founder, pet);
        sendOwnerNotification(founder, petOwner, pet);
    }

    private void sendFounderNotification(User founder, Pet pet) {
        String title = "You found " + pet.getPetOwner().getName() + "'s '" + pet.getName() + "''.";
        String message = "Dear " + founder.getName() + ", thank you for finding the pet '" + pet.getName() + "''.";
        System.out.println("Sending notification: " + title);

        NotificationCreateDto notificationCreateDto = new NotificationCreateDto();
        notificationCreateDto.setTitle(title);
        notificationCreateDto.setMessage(message);
        notificationCreateDto.setNotificationType(NotificationType.NOTIFICATION_MODIFIED);
        iNotificationService.createNotification(founder, notificationCreateDto);

        NotificationFactory PetNotificationFactory = new PetNotificationFactory(title, message);
        PetNotificationFactory.sendNotification(NotificationType.NOTIFICATION_MODIFIED);
    }

    private void sendOwnerNotification(User founder, User owner, Pet pet) {
        String title = founder.getName() + " find your " + pet.getName() + ".";
        String message = "Dear " + owner.getName() + ", your '" + pet.getName() + "' is found by '" + founder.getName() + "'.";
        System.out.println("Sending notification: " + title);

        NotificationCreateDto notificationCreateDto = new NotificationCreateDto();
        notificationCreateDto.setTitle(title);
        notificationCreateDto.setMessage(message);
        notificationCreateDto.setNotificationType(NotificationType.NOTIFICATION_MODIFIED);
        iNotificationService.createNotification(owner, notificationCreateDto);

        NotificationFactory PetNotificationFactory = new PetNotificationFactory(title, message);
        PetNotificationFactory.sendNotification(NotificationType.NOTIFICATION_MODIFIED);
    }
}
