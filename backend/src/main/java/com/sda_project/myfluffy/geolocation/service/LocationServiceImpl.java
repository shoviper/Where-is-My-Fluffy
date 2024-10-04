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
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements ILocationService {

    private LocationRepository locationRepository;
    private MapService mapService;

    @Override
    @Transactional
    public Location createLocation(LocationCreateDto locationCreateDto) {
        if (locationCreateDto == null) {
            throw new IllegalArgumentException("LocationCreateDto cannot be null");
        }
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

        String encodedAddress = URLEncoder.encode(location.getAddress(), StandardCharsets.UTF_8);
        locationDto.setAddressUrl(mapService.buildMapUrl(encodedAddress));

        return locationDto;
    }
}
