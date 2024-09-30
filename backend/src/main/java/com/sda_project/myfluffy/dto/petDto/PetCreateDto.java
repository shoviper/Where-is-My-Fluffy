package com.sda_project.myfluffy.dto.petDto;

import com.sda_project.myfluffy.enums.Status;
import lombok.Data;

@Data
public class PetCreateDto {

    private String name;

    private float age;

    private String animalType;

    private String description;

    private String Location;

    private Status status;

}
