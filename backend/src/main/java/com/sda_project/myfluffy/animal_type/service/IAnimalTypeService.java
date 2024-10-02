package com.sda_project.myfluffy.animal_type.service;


import com.sda_project.myfluffy.animal_type.dto.AnimalTypeDto;

import java.util.List;

public interface IAnimalTypeService {

    /**
     * Retrieves the animal type based on the provided type ID.
     *
     * @return All AnimalTypeDto details if found, otherwise empty.
     */
    List<AnimalTypeDto> fetchAllAnimalType();

    /**
     * Retrieves the animal type based on the provided type ID.
     *
     * @param type - The Type of the animal to be retrieved.
     * @return The AnimalTypeDto details if found, otherwise empty.
     */
    AnimalTypeDto fetchAnimalType(String type);

}
