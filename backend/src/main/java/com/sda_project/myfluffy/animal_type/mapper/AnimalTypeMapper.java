package com.sda_project.myfluffy.animal_type.mapper;


import com.sda_project.myfluffy.animal_type.model.AnimalType;
import com.sda_project.myfluffy.animal_type.dto.AnimalTypeDto;

public class AnimalTypeMapper {

    public static AnimalTypeDto mapToAnimalTypeDto(AnimalType animalType, AnimalTypeDto animalTypeDto) {
        animalTypeDto.setType(animalType.getType());
        return animalTypeDto;
    }

    public static AnimalType mapToAnimalType(AnimalTypeDto animalTypeDto, AnimalType animalType) {
        animalType.setType(animalTypeDto.getType());
        return animalType;
    }
}
