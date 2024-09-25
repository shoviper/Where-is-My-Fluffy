// controller/PetController.java
package com.sda_project.myfluffy.controller;

import com.sda_project.myfluffy.model.Pet;
import com.sda_project.myfluffy.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sda_project.myfluffy.model.Status;

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


    @PutMapping("/{id}/status")
    public Pet updatePetStatus(@PathVariable int id, @RequestParam String status) {
        return petService.updatePetStatus(id, Status.valueOf(status.toUpperCase()));
    }
}
