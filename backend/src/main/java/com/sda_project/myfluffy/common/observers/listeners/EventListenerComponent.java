package com.sda_project.myfluffy.common.observers.listeners;

import com.sda_project.myfluffy.common.observers.events.BaseEvent;
import com.sda_project.myfluffy.common.observers.events.pet.PetFounderChangeEvent;
import com.sda_project.myfluffy.common.observers.events.pet.PetStatusChangeEvent;
import com.sda_project.myfluffy.common.observers.events.post.PostCreatedEvent;
import com.sda_project.myfluffy.common.observers.factory.EventFactory;
import com.sda_project.myfluffy.pet.model.Pet;
import com.sda_project.myfluffy.post.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventListenerComponent {

    private EventFactory petEventFactory;  // This will be injected based on domain

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