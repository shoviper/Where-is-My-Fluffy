// model/Pet.java
package com.sda_project.myfluffy.pet.model;

import com.sda_project.myfluffy.common.utils.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private float age;

    @Column(name="description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;

    @Column(name="owner_id")
    private int ownerId;

    @Column(name="location_id")
    private int locationId;

    @Column(name="animal_type_id")
    private int animalTypeId;

    @Column(name="founder_id")
    private int founderId;

    @Column(name="reward_amount")
    private double rewardAmount;

}