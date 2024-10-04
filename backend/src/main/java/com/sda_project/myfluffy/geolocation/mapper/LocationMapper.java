package com.sda_project.myfluffy.geolocation.mapper;

import com.sda_project.myfluffy.geolocation.dto.LocationDto;
import com.sda_project.myfluffy.geolocation.model.Location;

public class LocationMapper {

    public static LocationDto mapToLocationDto(Location location, LocationDto locationDto) {
        locationDto.setId(location.getId());
        locationDto.setAddress(location.getAddress());
        return locationDto;
    }

    public static Location mapToLocation(LocationDto locationDto, Location location) {
        location.setId(locationDto.getId());
        location.setAddress(locationDto.getAddress());
        return location;
    }
}
