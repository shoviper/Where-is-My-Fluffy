package com.sda_project.myfluffy.geolocation;

import com.sda_project.myfluffy.dto.locationDto.LocationCreateDto;
import com.sda_project.myfluffy.dto.locationDto.LocationDto;
import com.sda_project.myfluffy.dto.responseDto.ResponseDto;
import com.sda_project.myfluffy.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/locations", produces = { MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
public class LocationController {

    private ILocationService iLocationService;

    @Operation(summary = "Create Location")
    @PostMapping
    public ResponseEntity<ResponseDto> createLocation(@RequestBody LocationCreateDto locationCreateDto) {
        iLocationService.createLocation(locationCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(Constants.STATUS_201, Constants.MESSAGE_201));
    }

    @Operation(summary = "Get Location by Location Id")
    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> fetchLocationDetailsById(@PathVariable int id) {
        LocationDto locationDto = iLocationService.fetchLocationById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(locationDto);
    }
}
