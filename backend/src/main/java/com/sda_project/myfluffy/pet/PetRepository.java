// repository/PetRepository.java
package com.sda_project.myfluffy.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    Optional<Pet> findByOwnerId(int ownerId);

    @Transactional
    @Modifying
    void deleteByOwnerId(int ownerId);
}