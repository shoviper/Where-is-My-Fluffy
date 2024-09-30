package com.sda_project.myfluffy.animal_type;

import com.sda_project.myfluffy.dto.animalTypeDto.AnimalTypeDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/animal-type", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AnimalTypeController {

    private IAnimalTypeService iAnimalTypeService;

    @Operation(
            summary = "Get Animal Type by Animal Type Id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<AnimalTypeDto> fetchAnimalTypeByType(@PathVariable int id) {
        AnimalTypeDto animalTypeDto = iAnimalTypeService.fetchAnimalType(id);
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(animalTypeDto);
    }
}
