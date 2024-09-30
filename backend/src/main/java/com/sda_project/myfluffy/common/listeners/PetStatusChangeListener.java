package com.sda_project.myfluffy.common.listeners;

import com.sda_project.myfluffy.common.events.PetStatusChangeEvent;
import com.sda_project.myfluffy.common.exception.ResourceNotFoundException;
import com.sda_project.myfluffy.common.utils.enums.Status;
import com.sda_project.myfluffy.notification.Notification;
import com.sda_project.myfluffy.pet.model.Pet;
import com.sda_project.myfluffy.user.model.User;
import com.sda_project.myfluffy.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PetStatusChangeListener {

    private UserRepository userRepository;
    private Notification notification;

    @EventListener
    public void handlePetStatusChange(PetStatusChangeEvent event) {
        Pet pet = event.getPet();
        System.out.println("Pet status changed: " + pet.getName() + " is now " + pet.getStatus());

        if (pet.getStatus().equals(Status.FOUND)) {
            notifyOwner(pet);
        }
    }

    private void notifyOwner(Pet pet) {
        User owner = userRepository.findById(pet.getOwnerId()).orElseThrow(
                () -> new ResourceNotFoundException("Pet-Owner", "id", Integer.toString(pet.getOwnerId()))
        );
        System.out.println("Notifying " + owner.getName() + " that their pet " + pet.getName() + " is found.");
        sendNotification(owner, pet);
    }

    private void sendNotification(User owner, Pet pet) {
        String message = "Dear " + owner.getName() + ", your pet '" + pet.getName() + "' has been marked as found.";
        System.out.println("Sending notification: " + message);
        notification.send(message);
    }
}
