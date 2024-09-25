// repository/PetRepository.java
package com.sda_project.myfluffy.repository;

import com.sda_project.myfluffy.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}