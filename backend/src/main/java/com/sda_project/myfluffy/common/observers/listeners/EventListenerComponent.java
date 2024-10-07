package com.sda_project.myfluffy.common.observers.listeners;

import com.sda_project.myfluffy.common.observers.events.pet.PetFounderChangeEvent;
import com.sda_project.myfluffy.common.observers.events.pet.PetStatusChangeEvent;
import com.sda_project.myfluffy.common.observers.events.post.PostCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventListenerComponent {

    @EventListener
    public void handlePetStatusChange(PetStatusChangeEvent event) {
        event.process();
    }

    @EventListener
    public void handlePetFounderChange(PetFounderChangeEvent event) {
        event.process();
    }

    @EventListener
    public void handlePostCreated(PostCreatedEvent event) {
        event.process();
    }
}