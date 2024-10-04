package com.sda_project.myfluffy.common.observers.events.pet;

import com.sda_project.myfluffy.common.observers.events.BaseEvent;
import com.sda_project.myfluffy.pet.model.Pet;
import lombok.Getter;

@Getter
public class PetStatusChangeEvent extends BaseEvent {

    private final Pet pet;

    public PetStatusChangeEvent(Object source, Pet pet) {
        super(source);
        this.pet = pet;
    }

    @Override
    public void process() {
        System.out.println("Processing Pet Status Change Event");
        // Your specific logic for PetStatusChangeEvent
    }
}
