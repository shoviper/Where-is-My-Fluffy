package com.sda_project.myfluffy.animal_type.repository;

import com.sda_project.myfluffy.animal_type.model.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalTypeRepository extends JpaRepository<AnimalType, String> {
}
