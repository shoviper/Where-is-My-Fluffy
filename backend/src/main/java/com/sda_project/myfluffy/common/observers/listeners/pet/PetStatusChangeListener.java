package com.sda_project.myfluffy.common.observers.listeners.pet;

import com.sda_project.myfluffy.common.observers.events.pet.PetStatusChangeEvent;
import com.sda_project.myfluffy.common.utils.enums.NotificationType;
import com.sda_project.myfluffy.common.utils.enums.Status;
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
public class PetStatusChangeListener {

    private INotificationService iNotificationService;

    @EventListener
    public void handlePetStatusChange(PetStatusChangeEvent event) {
        Pet pet = event.getPet();
        User owner = pet.getPetOwner();

        sendNotification(owner, pet);
    }

    private void sendNotification(User owner, Pet pet) {
        Status status = pet.getStatus();
        String title = "Your " + pet.getName() + " is " + status + "!!!";
        String message = "Dear " + owner.getName() + ", your pet '" + pet.getName() + "' has been marked as " + status
                + ".";
        System.out.println("Sending notification: " + message);

        NotificationCreateDto notificationCreateDto = new NotificationCreateDto();
        notificationCreateDto.setTitle(title);
        notificationCreateDto.setMessage(message);
        notificationCreateDto.setNotificationType(NotificationType.NOTIFICATION_MODIFIED);
        iNotificationService.createNotification(owner, notificationCreateDto);

        NotificationFactory PetNotificationFactory = new PetNotificationFactory(title, message);
        PetNotificationFactory.sendNotification(NotificationType.NOTIFICATION_MODIFIED);
    }
}
