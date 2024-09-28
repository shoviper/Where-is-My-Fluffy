package com.sda_project.myfluffy.mapper;

import com.sda_project.myfluffy.dto.PetDto;
import com.sda_project.myfluffy.pet.Pet;

public class PetMapper {

    public static PetDto mapToPetDto(Pet pet, PetDto petDto) {
        petDto.setName(pet.getName());
        petDto.setAge(pet.getAge());
        petDto.setDescription(pet.getDescription());
        petDto.setStatus(pet.getStatus());
        return petDto;
    }

    public static Pet mapToPet(PetDto petDto, Pet pet) {
        pet.setName(petDto.getName());
        pet.setAge(petDto.getAge());
        pet.setDescription(petDto.getDescription());
        pet.setStatus(petDto.getStatus());
        return pet;
    }

}
