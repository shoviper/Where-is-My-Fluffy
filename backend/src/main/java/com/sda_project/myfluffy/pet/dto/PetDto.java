package com.sda_project.myfluffy.pet.dto;

import com.sda_project.myfluffy.user.dto.UserDto;
import com.sda_project.myfluffy.geolocation.dto.LocationDto;
import com.sda_project.myfluffy.common.utils.enums.Status;
import lombok.Data;

@Data
public class PetDto {

    private int id;

    private String name;

    private double age;

    private String animalType;

    private String description;

    private Status status;

    private UserDto user;

    private UserDto founder;

    private LocationDto location;

}
