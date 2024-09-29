// service/PetService.java
package com.sda_project.myfluffy.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sda_project.myfluffy.enums.Status;
import com.sda_project.myfluffy.exception.UnauthorizedException;

import org.springframework.security.oauth2.core.user.OAuth2User;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public Pet getPet(int petId) {
        return petRepository.findById(petId).orElseThrow();
    }

    public Iterable<Pet> getPets() {
        return petRepository.findAll();
    }

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet updatePetStatus(int petId, Status status, OAuth2User oAuth2User) {
        Pet pet = petRepository.findById(petId).orElseThrow();
        int id = Integer.parseInt(oAuth2User.getAttribute("id"));

        if (pet.getOwner().getId() != id) {
            throw new UnauthorizedException("cannot update other users pets");
        }
        pet.setStatus(status);
        return petRepository.save(pet);
    }
}
