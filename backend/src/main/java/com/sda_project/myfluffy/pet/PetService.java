// service/PetService.java
package com.sda_project.myfluffy.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sda_project.myfluffy.enums.Status;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet updatePetStatus(int petId, Status status) {
        Pet pet = petRepository.findById(petId).orElseThrow();
        pet.setStatus(status);
        return petRepository.save(pet);
    }
}
