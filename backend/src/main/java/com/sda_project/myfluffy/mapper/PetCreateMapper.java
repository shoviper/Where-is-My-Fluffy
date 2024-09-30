package com.sda_project.myfluffy.mapper;

import com.sda_project.myfluffy.dto.PetCreateDto;
import com.sda_project.myfluffy.pet.Pet;

public class PetCreateMapper {

    public static PetCreateDto mapToPetCreateDto(Pet pet, PetCreateDto petCreateDto) {
        petCreateDto.setName(pet.getName());
        petCreateDto.setAge(pet.getAge());
        petCreateDto.setDescription(pet.getDescription());
        petCreateDto.setStatus(pet.getStatus());
        return petCreateDto;
    }

    public static Pet mapToPet(PetCreateDto petCreateDto, Pet pet) {
        pet.setName(petCreateDto.getName());
        pet.setAge(petCreateDto.getAge());
        pet.setDescription(petCreateDto.getDescription());
        pet.setStatus(petCreateDto.getStatus());
        return pet;
    }
}
