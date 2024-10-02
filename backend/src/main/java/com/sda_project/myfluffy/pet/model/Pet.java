// model/Pet.java
package com.sda_project.myfluffy.pet.model;

import com.sda_project.myfluffy.animal_type.model.AnimalType;
import com.sda_project.myfluffy.common.utils.enums.Status;
import com.sda_project.myfluffy.geolocation.model.Location;
import com.sda_project.myfluffy.user.model.User;
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

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="owner_id")
    private User petOwner;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="location_id")
    private Location location;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="animal_type")
    private AnimalType animalType;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="founder_id")
    private User founder;

    @Column(name="reward_amount")
    private double rewardAmount;

}