package com.sda_project.myfluffy.common.observers.listeners.pet;

import com.sda_project.myfluffy.common.observers.events.pet.PetFounderChangeEvent;
import com.sda_project.myfluffy.notification.Notification;
import com.sda_project.myfluffy.pet.model.Pet;
import com.sda_project.myfluffy.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PetFounderChangeListener {

    private Notification notification;

    @EventListener
    public void handlePetFounderChange(PetFounderChangeEvent event) {
        Pet pet = event.getPet();
        User founder = pet.getFounder();
        System.out.println("Pet founder changed: " + pet.getName() + " is now found by " + founder.getName());

        notifyFounder(founder, pet);
    }

    private void notifyFounder(User founder, Pet pet) {
        String message = "Dear " + founder.getName() + ", thank you for finding the pet '" + pet.getName() + "'.";
        System.out.println("Sending notification: " + message);
        sendNotification(founder, pet);
    }

    private void sendNotification(User founder, Pet pet) {
        String message = "Dear " + founder.getName() + ", thank you for finding the pet '" + pet.getName() + "'.";
        System.out.println("Sending notification: " + message);
        notification.send(message);
    }
}
