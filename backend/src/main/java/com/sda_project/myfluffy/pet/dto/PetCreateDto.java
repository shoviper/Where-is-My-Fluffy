package com.sda_project.myfluffy.pet.dto;

import com.sda_project.myfluffy.common.utils.enums.Status;
import lombok.Data;

@Data
public class PetCreateDto {

    private String name;

    private double age;

    private String animalType;

    private String description;

    private String Location;

    private Status status;

}
