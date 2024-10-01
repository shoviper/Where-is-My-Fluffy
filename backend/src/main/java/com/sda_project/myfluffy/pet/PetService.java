// service/PetService.java
package com.sda_project.myfluffy.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.sda_project.myfluffy.enums.Status;
import com.sda_project.myfluffy.exception.UnauthorizedException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.sda_project.myfluffy.plugin.MyRewardPlugin;
import com.sda_project.myfluffy.plugin.RewardPlugin;
import com.sda_project.myfluffy.user.User;
import com.sda_project.myfluffy.user.UserRepository;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private RewardPlugin rewardPlugin;  // Inject the RewardPlugin (PayPal or any other plugin)

    @Autowired
    private UserRepository userRepository;

    public Pet getPet(int petId) {
        return petRepository.findById(petId).orElseThrow();
    }

    public Iterable<Pet> getPets() {
        return petRepository.findAll();
    }

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public boolean addFounder(int petId, OAuth2User oAuth2User, int founderId) {

        String email = oAuth2User.getAttribute("email");
        Pet pet = petRepository.findById(petId).orElseThrow();

        if (!pet.getOwner().getEmail().equals(email)) {
            throw new UnauthorizedException("cannot update other users pets");
        }

        User founder = userRepository.findById(founderId).orElseThrow();

        if (founder == null) {
            throw new UnauthorizedException("founder not found");
        }
        // Logic when pet is found
        pet.setFounder(founder);
        
        // return petRepository.save(pet).getFounderId() != 0;
        return true;


    }

    public Pet updatePetStatus(int petId, Status status, OAuth2User oAuth2User) {
        
        String email = oAuth2User.getAttribute("email");
        Pet pet = petRepository.findById(petId).orElseThrow();

        if (!pet.getOwner().getEmail().equals(email)) {
            throw new UnauthorizedException("cannot update other users pets");
        }

        // Logic when pet is found
        if (status == Status.FOUND && pet.getFounder() != null) {
            System.out.println("Pet already found!");
            boolean rewardSent = rewardPlugin.sendReward(pet.getOwner(), pet.getFounder(), pet.getRewardAmount());
            if (!rewardSent) {
                throw new RuntimeException("Failed to send reward payment");
            }
        }
        pet.setStatus(status);
        
        return petRepository.save(pet);
    }
}
