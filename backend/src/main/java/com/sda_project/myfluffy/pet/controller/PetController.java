// controller/PetController.java
package com.sda_project.myfluffy.pet.controller;

import com.sda_project.myfluffy.pet.dto.PetCreateDto;
import com.sda_project.myfluffy.pet.dto.PetDto;
import com.sda_project.myfluffy.pet.dto.PetFounderUpdateDto;
import com.sda_project.myfluffy.pet.dto.PetStatusUpdateDto;
import com.sda_project.myfluffy.common.dto.response.ResponseDto;
import com.sda_project.myfluffy.pet.mapper.PetMapper;
import com.sda_project.myfluffy.pet.model.Pet;
import com.sda_project.myfluffy.pet.service.IPetService;
import com.sda_project.myfluffy.common.utils.constants.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    public ResponseEntity<PetDto> createPet(@AuthenticationPrincipal OAuth2User principal, @RequestBody PetCreateDto petCreateDto) {
        PetDto petDto = iPetService.createPet(principal, petCreateDto);
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(petDto);
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
                    .body(ResponseDto.builder()
                            .statusCode(AppConstants.STATUS_200)
                            .statusMsg(AppConstants.MESSAGE_200)
                            .build());
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDto.builder()
                            .statusCode(AppConstants.STATUS_417)
                            .statusMsg(AppConstants.MESSAGE_417_UPDATE)
                            .build());
        }
    }

    @Operation(
            summary = "Delete Pet by Pet Id"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deletePetDetails(@PathVariable int id) {
        boolean isDeleted = iPetService.deletePet(id);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder()
                            .statusCode(AppConstants.STATUS_200)
                            .statusMsg(AppConstants.MESSAGE_200)
                            .build());
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDto.builder()
                            .statusCode(AppConstants.STATUS_417)
                            .statusMsg(AppConstants.MESSAGE_417_DELETE)
                            .build());
        }
    }

    @Operation(
            summary = "Add founder who found the pet: OAuth2User"
    )
    @PutMapping("/update-pet-founder")
    public ResponseEntity<ResponseDto> addFounder(@AuthenticationPrincipal OAuth2User oAuth2User, @RequestBody PetFounderUpdateDto petFounderUpdateDto) {
        boolean isDeleted = iPetService.addFounder(oAuth2User, petFounderUpdateDto);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder()
                            .statusCode(AppConstants.STATUS_200)
                            .statusMsg(AppConstants.MESSAGE_200)
                            .build());
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDto.builder()
                            .statusCode(AppConstants.STATUS_417)
                            .statusMsg(AppConstants.MESSAGE_417_UPDATE)
                            .build());
        }
    }

    @PutMapping("/{petId}/uploadImage")
    public ResponseEntity<ResponseDto> uploadPetImage(@RequestParam("file") MultipartFile file, @PathVariable int petId) {
        try {
            // Save file to local directory
            String fileName = file.getOriginalFilename();
            String uploadDir = "uploads/pet-images/";
            String filePath = Paths.get(uploadDir, fileName).toString();

            // Ensure directory exists
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the file to the local directory
            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

            // Get the pet entity by id and update the image path
            PetDto petDto = iPetService.fetchPetById(petId);
            Pet pet = PetMapper.mapToPet(petDto, new Pet());
            pet.setImagePath(filePath);  // Assuming the Pet entity has a field for imagePath
            iPetService.updatePetImagePath(pet);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder()
                            .statusCode(AppConstants.STATUS_200)
                            .statusMsg(AppConstants.MESSAGE_200)
                            .build());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDto.builder()
                            .statusCode(AppConstants.STATUS_417)
                            .statusMsg(AppConstants.MESSAGE_417_UPDATE)
                            .build());
        }
    }
}
