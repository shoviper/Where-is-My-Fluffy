package com.sda_project.myfluffy.common.observers.events.pet;

import com.sda_project.myfluffy.common.observers.events.BaseEvent;
import com.sda_project.myfluffy.pet.model.Pet;
import lombok.Getter;

@Getter
public class PetFounderChangeEvent extends BaseEvent {

    private final Pet pet;

    public PetFounderChangeEvent(Object source, Pet pet) {
        super(source);
        this.pet = pet;
    }

    @Override
    public void process() {
        System.out.println("Processing Pet Founder Change Event");
        // Your specific logic for PetFounderChangeEvent
    }
}