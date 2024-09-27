// service/PetService.java
package com.sda_project.myfluffy.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sda_project.myfluffy.enums.Status;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Transactional(readOnly = true)
    public Pet getPet(int petId) {
        return petRepository.findById(petId).orElseThrow();
    }

    @Transactional(readOnly = true)
    public Iterable<Pet> getPets() {
        return petRepository.findAll();
    }

    @Transactional
    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    @Transactional
    public Pet updatePetStatus(int petId, Status status) {
        Pet pet = petRepository.findById(petId).orElseThrow();
        pet.setStatus(status);
        return petRepository.save(pet);
    }
}
