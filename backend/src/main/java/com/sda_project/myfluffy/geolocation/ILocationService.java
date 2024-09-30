package com.sda_project.myfluffy.geolocation;


import com.sda_project.myfluffy.dto.LocationCreateDto;
import com.sda_project.myfluffy.dto.LocationDto;

public interface ILocationService {

    Location createLocation(LocationCreateDto locationCreateDto);

    LocationDto fetchLocationById(int id);

}
