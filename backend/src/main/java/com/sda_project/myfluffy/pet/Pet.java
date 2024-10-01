// model/Pet.java
package com.sda_project.myfluffy.pet;

import java.util.ArrayList;
import java.util.List;

import com.sda_project.myfluffy.enums.Status;
import com.sda_project.myfluffy.utils.Observable;
import com.sda_project.myfluffy.utils.Observer;
import com.sda_project.myfluffy.post.Post;
import com.sda_project.myfluffy.user.User;
import com.sda_project.myfluffy.geolocation.Location;
import com.sda_project.myfluffy.animaltype.Animaltype;
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

    @Column(name="reward_amount")
    private float rewardAmount;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToOne
    @JoinColumn(name = "founder_id", nullable = true)
    private User founder;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = true)
    private Location location;

    @OneToMany
    @JoinColumn(name = "pet_id")
    private List<Post> posts = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "animal_type", nullable = false)
    private Animaltype animaltype;

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