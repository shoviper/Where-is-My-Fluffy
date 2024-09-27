// service/PetService.java
package com.sda_project.myfluffy.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sda_project.myfluffy.model.Status;
import com.sda_project.myfluffy.model.User;
import com.sda_project.myfluffy.repository.UserRepository;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    public Pet getPet(int petId) {
        return petRepository.findById(petId).orElseThrow();
    }

    public Iterable<Pet> getPets() {
        return petRepository.findAll();
    }

    public Pet savePet(Pet pet) {
        // Fetch the full User object before saving the Pet
        if (pet.getOwner() != null && pet.getOwner().getId() != 0) {
            User owner = userRepository.findById(pet.getOwner().getId()).orElseThrow(); 
            pet.setOwner(owner);  // Set the full User object
        }
        return petRepository.save(pet);
    }

    public Pet updatePetStatus(int petId, Status status) {
        Pet pet = petRepository.findById(petId).orElseThrow();
        pet.setStatus(status);
        return petRepository.save(pet);
    }
}
