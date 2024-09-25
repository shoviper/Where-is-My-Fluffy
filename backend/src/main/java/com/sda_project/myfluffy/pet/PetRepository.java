// repository/PetRepository.java
package com.sda_project.myfluffy.pet;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}