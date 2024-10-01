package com.sda_project.myfluffy.animal_type.service;

import com.sda_project.myfluffy.animal_type.model.AnimalType;
import com.sda_project.myfluffy.animal_type.repository.AnimalTypeRepository;
import com.sda_project.myfluffy.animal_type.dto.AnimalTypeDto;
import com.sda_project.myfluffy.common.exception.ResourceNotFoundException;
import com.sda_project.myfluffy.animal_type.mapper.AnimalTypeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnimalTypeServiceImpl implements IAnimalTypeService {

    private AnimalTypeRepository animalTypeRepository;

    /**
     * Retrieves the animal type based on the provided type ID.
     *
     * @param id - The ID of the type of the animal to be retrieved.
     * @return The AnimalTypeDto details if found, otherwise empty.
     */
    @Override
    public AnimalTypeDto fetchAnimalType(int id) {
        AnimalType animalType = animalTypeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("AnimalType", "id", Integer.toString(id))
        );

        return AnimalTypeMapper.mapToAnimalTypeDto(animalType, new AnimalTypeDto());
    }
}