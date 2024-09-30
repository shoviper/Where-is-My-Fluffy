package com.sda_project.myfluffy.mapper;

import com.sda_project.myfluffy.dto.locationDto.LocationCreateDto;
import com.sda_project.myfluffy.geolocation.Location;

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
