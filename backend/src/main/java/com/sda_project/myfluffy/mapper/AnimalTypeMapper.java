package com.sda_project.myfluffy.mapper;


import com.sda_project.myfluffy.animal_type.AnimalType;
import com.sda_project.myfluffy.dto.animalTypeDto.AnimalTypeDto;

public class AnimalTypeMapper {

    public static AnimalTypeDto mapToAnimalTypeDto(AnimalType animalType, AnimalTypeDto animalTypeDto) {
        animalTypeDto.setId(animalType.getId());
        animalTypeDto.setType(animalType.getType());
        return animalTypeDto;
    }

    public static AnimalType mapToAnimalType(AnimalTypeDto animalTypeDto, AnimalType animalType) {
        animalType.setId(animalTypeDto.getId());
        animalType.setType(animalTypeDto.getType());
        return animalType;
    }
}
