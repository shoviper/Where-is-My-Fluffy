// controller/PetController.java
package com.sda_project.myfluffy.pet;

import com.sda_project.myfluffy.dto.petDto.PetCreateDto;
import com.sda_project.myfluffy.dto.petDto.PetDto;
import com.sda_project.myfluffy.dto.petDto.PetStatusUpdateDto;
import com.sda_project.myfluffy.dto.responseDto.ResponseDto;
import com.sda_project.myfluffy.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/pets", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class PetController {

    private IPetService iPetService;

    @Operation(
            summary = "Get All Pets"
    )
    @GetMapping
    public ResponseEntity<List<PetDto>> getAllPets() {
        List<PetDto> petDtos = iPetService.getAllPets();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(petDtos);
    }

    @Operation(
            summary = "Create My Pet: OAuth2User"
    )
    @PostMapping
    public ResponseEntity<ResponseDto> createPet(@AuthenticationPrincipal OAuth2User principal, @RequestBody PetCreateDto petCreateDto) {
        iPetService.createPet(principal, petCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(Constants.STATUS_201, Constants.MESSAGE_201));
    }

    @Operation(
            summary = "Get Pet by Pet Id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PetDto> fetchPetDetailsById(@PathVariable int id) {
        PetDto petDto = iPetService.fetchPetById(id);
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(petDto);
    }

    @Operation(
            summary = "Get My Pet: OAuth2User"
    )
    @GetMapping("/me")
    public ResponseEntity<List<PetDto>> fetchMyPetDetails(@AuthenticationPrincipal OAuth2User principal) {
        List<PetDto> petDtos = iPetService.fetchMyPet(principal);
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(petDtos);
    }

    @Operation(
            summary = "Update Pet Status: OAuth2User"
    )
    @PutMapping("/update-pet-status")
    public ResponseEntity<ResponseDto> updatePetStatus(@AuthenticationPrincipal OAuth2User principal, @RequestBody PetStatusUpdateDto petStatusUpdateDto) {
        boolean isUpdated = iPetService.updatePetStatus(principal, petStatusUpdateDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(Constants.STATUS_200, Constants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(Constants.STATUS_417, Constants.MESSAGE_417_UPDATE));
        }
    }
}
