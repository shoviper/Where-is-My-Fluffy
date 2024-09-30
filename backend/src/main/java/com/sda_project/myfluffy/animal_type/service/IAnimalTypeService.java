package com.sda_project.myfluffy.animal_type.service;


import com.sda_project.myfluffy.animal_type.dto.AnimalTypeDto;

public interface IAnimalTypeService {

    /**
     * Retrieves the animal type based on the provided type ID.
     *
     * @param id - The ID of the type of the animal to be retrieved.
     * @return The AnimalTypeDto details if found, otherwise empty.
     */
    AnimalTypeDto fetchAnimalType(int id);

}
