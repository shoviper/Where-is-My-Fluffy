// model/Pet.java
package com.sda_project.myfluffy.pet;

import java.util.ArrayList;
import java.util.List;

import com.sda_project.myfluffy.enums.Status;
import com.sda_project.myfluffy.utils.Observable;
import com.sda_project.myfluffy.utils.Observer;
import com.sda_project.myfluffy.post.Post;
import com.sda_project.myfluffy.user.User;
import jakarta.persistence.*;
// import java.util.Observable;
// import java.util.Observer;

@Entity
@Table(name = "pets")
public class Pet implements Observable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private float age;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

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

    // Getter and Setter methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}