package com.sda_project.myfluffy.common.events;

import com.sda_project.myfluffy.pet.model.Pet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PetStatusChangeEvent extends ApplicationEvent {

    private final Pet pet;

    public PetStatusChangeEvent(Object source, Pet pet) {
        super(source);
        this.pet = pet;
    }

}
