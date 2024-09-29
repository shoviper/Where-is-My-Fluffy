package com.sda_project.myfluffy.geolocation;


import com.sda_project.myfluffy.dto.LocationDto;

public interface ILocationService {

    LocationDto fetchLocationById(int id);

}
