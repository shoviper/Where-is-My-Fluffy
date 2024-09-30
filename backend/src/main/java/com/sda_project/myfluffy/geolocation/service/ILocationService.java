package com.sda_project.myfluffy.geolocation.service;


import com.sda_project.myfluffy.geolocation.dto.LocationCreateDto;
import com.sda_project.myfluffy.geolocation.dto.LocationDto;
import com.sda_project.myfluffy.geolocation.model.Location;

public interface ILocationService {

    Location createLocation(LocationCreateDto locationCreateDto);

    LocationDto fetchLocationById(int id);

}
