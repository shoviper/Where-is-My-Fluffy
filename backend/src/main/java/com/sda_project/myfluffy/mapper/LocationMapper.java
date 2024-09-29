package com.sda_project.myfluffy.mapper;

import com.sda_project.myfluffy.dto.LocationDto;
import com.sda_project.myfluffy.geolocation.Location;

public class LocationMapper {

    public static LocationDto mapToLocationDto(Location location, LocationDto locationDto) {
        locationDto.setAddress(location.getAddress());
        return locationDto;
    }

    public static Location mapToLocation(LocationDto locationDto, Location location) {
        location.setAddress(locationDto.getAddress());
        return location;
    }
}
