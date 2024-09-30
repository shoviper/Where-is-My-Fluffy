// controller/PetController.java
package com.sda_project.myfluffy.pet;

import com.sda_project.myfluffy.dto.PetCreateDto;
import com.sda_project.myfluffy.dto.PetDto;
import com.sda_project.myfluffy.dto.ResponseDto;
import com.sda_project.myfluffy.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/pets", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class PetController {

    private IPetService iPetService;

//    @GetMapping("/all")
//    public Iterable<Pet> getPets() {
//        return petService.getPets();
//    }

    @PostMapping
    public ResponseEntity<ResponseDto> createPet(@AuthenticationPrincipal OAuth2User principal, @RequestBody PetCreateDto petCreateDto) {
        iPetService.createPet(principal, petCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(Constants.STATUS_201, Constants.MESSAGE_201));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PetDto> fetchUserDetailsById(@PathVariable int id) {
        PetDto petDto = iPetService.fetchPetById(id);
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(petDto);
    }


//    @PostMapping
//    public ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
//        Pet savedPet = petService.savePet(pet);
//        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);
//    }
//
//
//    @PutMapping("/{id}/status")
//    public Pet updatePetStatus(@PathVariable int id, @RequestParam String status, OAuth2User oAuth2User) {
//        return petService.updatePetStatus(id, Status.valueOf(status.toUpperCase()), oAuth2User);
//    }
}
