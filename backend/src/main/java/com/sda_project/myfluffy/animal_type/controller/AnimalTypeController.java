package com.sda_project.myfluffy.animal_type.controller;

import com.sda_project.myfluffy.animal_type.service.IAnimalTypeService;
import com.sda_project.myfluffy.animal_type.dto.AnimalTypeDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/animal-type", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AnimalTypeController {

    private IAnimalTypeService iAnimalTypeService;

    @Operation(
            summary = "Get All Animal Type"
    )
    @GetMapping
    public ResponseEntity<List<AnimalTypeDto>> fetchAnimalTypeByType() {
        List<AnimalTypeDto> animalTypeDto = iAnimalTypeService.fetchAllAnimalType();
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(animalTypeDto);
    }
}
