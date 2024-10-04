package com.sda_project.myfluffy.common.observers.factory.pet;

import com.sda_project.myfluffy.common.observers.events.BaseEvent;
import com.sda_project.myfluffy.common.observers.events.pet.PetFounderChangeEvent;
import com.sda_project.myfluffy.common.observers.events.pet.PetStatusChangeEvent;
import com.sda_project.myfluffy.common.observers.factory.EventFactory;
import com.sda_project.myfluffy.pet.model.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetEventFactory implements EventFactory {

    @Override
    public BaseEvent createStatusChangeEvent(Object source, Object entity) {
        return new PetStatusChangeEvent(source, (Pet) entity);
    }

    @Override
    public BaseEvent createFounderChangeEvent(Object source, Object entity) {
        return new PetFounderChangeEvent(source, (Pet) entity);
    }

    @Override
    public BaseEvent createPostCreatedEvent(Object source, Object entity) {
        throw new UnsupportedOperationException("Post created event not supported in PetEventFactory");
    }
}
