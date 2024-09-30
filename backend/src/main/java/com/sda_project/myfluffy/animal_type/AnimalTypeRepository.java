package com.sda_project.myfluffy.animal_type;

import com.sda_project.myfluffy.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalTypeRepository  extends JpaRepository<AnimalType, Integer> {

    Optional<AnimalType> findByType(String type);
}
