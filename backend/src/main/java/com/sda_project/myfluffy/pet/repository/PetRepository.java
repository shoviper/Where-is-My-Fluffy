// repository/PetRepository.java
package com.sda_project.myfluffy.pet.repository;

import com.sda_project.myfluffy.pet.model.Pet;
import com.sda_project.myfluffy.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    @Transactional
    @Modifying
    void deleteByPetOwner(User petOwner);
}