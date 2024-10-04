package com.sda_project.myfluffy.animal_type.model;

import com.sda_project.myfluffy.pet.model.Pet;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "animal_type")
public class AnimalType {

    @Id
    @Column(name="type")
    private String type;

    @OneToMany(mappedBy="animalType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets;
}
