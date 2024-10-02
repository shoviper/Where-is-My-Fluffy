package com.sda_project.myfluffy.geolocation.mapper;

import com.sda_project.myfluffy.geolocation.dto.LocationCreateDto;
import com.sda_project.myfluffy.geolocation.model.Location;

public class LocationCreateMapper {

    public static LocationCreateDto mapToLocationCreateDto(Location location, LocationCreateDto locationCreateDto) {
        locationCreateDto.setAddress(location.getAddress());
        return locationCreateDto;
    }

    public static Location mapToLocation(LocationCreateDto locationCreateDto, Location location) {
        location.setAddress(locationCreateDto.getAddress());
        return location;
    }
}
