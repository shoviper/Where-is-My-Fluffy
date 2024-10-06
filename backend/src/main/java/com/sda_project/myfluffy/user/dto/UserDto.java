package com.sda_project.myfluffy.user.dto;

import com.sda_project.myfluffy.geolocation.dto.LocationDto;

import lombok.Data;

@Data
public class UserDto {

    private int id;

    private String name;

    private String email;

    private String phone;

    private double balance;

    private String userImage;

    private LocationDto userLocation;

}
