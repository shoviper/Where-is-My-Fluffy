package com.sda_project.myfluffy.dto;

import com.sda_project.myfluffy.enums.Status;
import lombok.Data;

@Data
public class PetDto {

    private String name;

    private float age;

    private String description;

    private Status status;

    private UserDto userDto;

}
