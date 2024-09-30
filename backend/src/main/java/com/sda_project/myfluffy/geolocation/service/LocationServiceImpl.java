package com.sda_project.myfluffy.geolocation.service;

import com.sda_project.myfluffy.geolocation.adapter.MapService;
import com.sda_project.myfluffy.geolocation.dto.LocationCreateDto;
import com.sda_project.myfluffy.geolocation.dto.LocationDto;
import com.sda_project.myfluffy.common.exception.ResourceNotFoundException;
import com.sda_project.myfluffy.geolocation.model.Location;
import com.sda_project.myfluffy.geolocation.repository.LocationRepository;
import com.sda_project.myfluffy.geolocation.mapper.LocationCreateMapper;
import com.sda_project.myfluffy.geolocation.mapper.LocationMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements ILocationService {

    private LocationRepository locationRepository;
    private MapService mapService;

    @Override
    public Location createLocation(LocationCreateDto locationCreateDto) {
        Location location = new Location();
        LocationCreateMapper.mapToLocation(locationCreateDto, location);
        return locationRepository.save(location);
    }

    @Override
    public LocationDto fetchLocationById(int id) {
        Location location = locationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Location", "id", Integer.toString(id))
        );
        LocationDto locationDto = LocationMapper.mapToLocationDto(location, new LocationDto());

        locationDto.setAddressUrl(mapService.buildMapUrl(location.getAddress()));

        return locationDto;
    }
}
