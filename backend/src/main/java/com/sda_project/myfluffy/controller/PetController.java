// controller/PetController.java
package com.sda_project.myfluffy.controller;

import com.sda_project.myfluffy.model.Pet;
import com.sda_project.myfluffy.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sda_project.myfluffy.model.Status;

@RestController
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private PetService petService;

    @PostMapping
    public Pet createPet(@RequestBody Pet pet) {
        return petService.savePet(pet);
    }

    @PutMapping("/{id}/status")
    public Pet updatePetStatus(@PathVariable int id, @RequestParam String status) {
        return petService.updatePetStatus(id, Status.valueOf(status.toUpperCase()));
    }
}
