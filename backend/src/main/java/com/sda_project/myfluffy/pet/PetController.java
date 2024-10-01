// controller/PetController.java
package com.sda_project.myfluffy.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import com.sda_project.myfluffy.dto.ResponseDto;
import com.sda_project.myfluffy.enums.Status;
import com.sda_project.myfluffy.utils.Constants;

@RestController
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping("/all")
    public Iterable<Pet> getPets() {
        return petService.getPets();
    }

    @GetMapping("/{id}")
    public Pet getPet(@PathVariable int id) {
        return petService.getPet(id);
    }
    // POST route to add a new Pet
    @PostMapping
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
        Pet savedPet = petService.savePet(pet);
        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/founder")
    public boolean addFounder(@PathVariable int id, @RequestParam int founderId, @AuthenticationPrincipal OAuth2User oAuth2User) {
        return petService.addFounder(id, oAuth2User, founderId);
    }

    @PutMapping("/{id}/status")
    public Pet updatePetStatus(@PathVariable int id, @RequestParam String status, @AuthenticationPrincipal OAuth2User oAuth2User) {
        
        return petService.updatePetStatus(id, Status.valueOf(status.toUpperCase()), oAuth2User);

        // boolean isUpdated = petService.updatePetStatus(id, Status.valueOf(status.toUpperCase()), oAuth2User);
        // if(isUpdated) {
        //     return petService.getPet(id);
        // }else{
        //     return null;
        // }
                
        // if(isUpdated) {
        //     return ResponseEntity
        //             .status(HttpStatus.OK)
        //             .body(new ResponseDto(Constants.STATUS_200, Constants.MESSAGE_200));
        // }else{
        //     return ResponseEntity
        //             .status(HttpStatus.INTERNAL_SERVER_ERROR)
        //             .body(new ResponseDto(Constants.STATUS_417, Constants.MESSAGE_417_UPDATE));
        // }
    }
}
