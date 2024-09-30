package com.sda_project.myfluffy.animal_type;


import com.sda_project.myfluffy.dto.AnimalTypeDto;

public interface IAnimalTypeService {

    /**
     * Retrieves the animal type based on the provided type ID.
     *
     * @param id - The ID of the type of the animal to be retrieved.
     * @return The AnimalTypeDto details if found, otherwise empty.
     */
    AnimalTypeDto fetchAnimalType(int id);

}
