package com.sda_project.myfluffy.geolocation;

import com.sda_project.myfluffy.dto.LocationCreateDto;
import com.sda_project.myfluffy.dto.LocationDto;
import com.sda_project.myfluffy.dto.ResponseDto;
import com.sda_project.myfluffy.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/locations", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class LocationController {

    private ILocationService iLocationService;

    @PostMapping
    public ResponseEntity<ResponseDto> createLocation(@RequestBody LocationCreateDto locationCreateDto) {
        Location location = iLocationService.createLocation(locationCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(Constants.STATUS_201, Constants.MESSAGE_201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> fetchUserDetailsById(@PathVariable int id) {
        LocationDto locationDto = iLocationService.fetchLocationById(id);
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(locationDto);
    }
}
