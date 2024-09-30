// model/Pet.java
package com.sda_project.myfluffy.pet;

import java.util.ArrayList;
import java.util.List;

import com.sda_project.myfluffy.enums.Status;
import com.sda_project.myfluffy.utils.Observable;
import com.sda_project.myfluffy.utils.Observer;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pets")
public class Pet implements Observable {

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

//    @OneToMany
//    @JoinColumn(name = "pet_id")
//    private List<Post> posts = new ArrayList<>();

    private transient List<Observer> observers = new ArrayList<>();

    // Observable Methods
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

}