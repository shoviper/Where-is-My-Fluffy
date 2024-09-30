package com.sda_project.myfluffy.dto.petDto;

import com.sda_project.myfluffy.dto.userDto.UserDto;
import com.sda_project.myfluffy.dto.locationDto.LocationDto;
import com.sda_project.myfluffy.enums.Status;
import lombok.Data;

@Data
public class PetDto {

    private int id;

    private String name;

    private float age;

    private String animalType;

    private String description;

    private Status status;

    private UserDto userDto;

    private LocationDto locationDto;

}
