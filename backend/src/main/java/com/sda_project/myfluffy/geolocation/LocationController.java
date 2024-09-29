package com.sda_project.myfluffy.geolocation;

import com.sda_project.myfluffy.dto.LocationDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/locations", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class LocationController {

    private ILocationService iLocationService;

    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> fetchUserDetailsById(@PathVariable int id) {
        LocationDto locationDto = iLocationService.fetchLocationById(id);
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(locationDto);
    }
}
