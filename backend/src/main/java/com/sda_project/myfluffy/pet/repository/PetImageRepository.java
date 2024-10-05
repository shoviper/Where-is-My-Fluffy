package com.sda_project.myfluffy.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sda_project.myfluffy.pet.model.PetImage;

@Repository
public interface PetImageRepository extends JpaRepository<PetImage, Integer> {
}
