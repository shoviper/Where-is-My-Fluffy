package com.sda_project.myfluffy.animal_type.service;

import com.sda_project.myfluffy.animal_type.model.AnimalType;
import com.sda_project.myfluffy.animal_type.repository.AnimalTypeRepository;
import com.sda_project.myfluffy.animal_type.dto.AnimalTypeDto;
import com.sda_project.myfluffy.common.exception.ResourceNotFoundException;
import com.sda_project.myfluffy.animal_type.mapper.AnimalTypeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnimalTypeServiceImpl implements IAnimalTypeService {

    private AnimalTypeRepository animalTypeRepository;

    /**
     * Retrieves the animal type based on the provided type ID.
     *
     * @return All AnimalTypeDto details if found, otherwise empty.
     */
    @Override
    public List<AnimalTypeDto> fetchAllAnimalType() {
        List<AnimalType> animalTypes = animalTypeRepository.findAll();

        return animalTypes.stream()
                .map(animalType -> AnimalTypeMapper.mapToAnimalTypeDto(animalType, new AnimalTypeDto()))
                .toList();
    }

    /**
     * Retrieves the animal type based on the provided type ID.
     *
     * @param type - The Type of the animal to be retrieved.
     * @return The AnimalTypeDto details if found, otherwise empty.
     */
    @Override
    public AnimalTypeDto fetchAnimalType(String type) {
        AnimalType animalType = animalTypeRepository.findById(type).orElseThrow(
                () -> new ResourceNotFoundException("AnimalType", "type", type)
        );

        return AnimalTypeMapper.mapToAnimalTypeDto(animalType, new AnimalTypeDto());
    }
}
