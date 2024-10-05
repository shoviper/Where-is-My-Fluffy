package com.sda_project.myfluffy.pet.mapper;

import com.sda_project.myfluffy.pet.dto.PetCreateResponseDto;
import com.sda_project.myfluffy.pet.model.Pet;

public class PetCreateResponseMapper {

    public static PetCreateResponseDto mapToPetCreateResponseDto(Pet pet,
            PetCreateResponseDto petCreateResponseDto) {
        petCreateResponseDto.setId(pet.getId());
        petCreateResponseDto.setName(pet.getName());
        return petCreateResponseDto;
    }

    public static Pet mapToPet(PetCreateResponseDto petCreateResponseDto, Pet pet) {
        pet.setId(petCreateResponseDto.getId());
        pet.setName(petCreateResponseDto.getName());
        return pet;
    }
}
