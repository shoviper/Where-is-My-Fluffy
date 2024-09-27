// model/User.java
package com.sda_project.myfluffy.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

// import java.util.Observable;
// import java.util.Observer;

@Entity
@Table(name = "users")
public class User implements Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String phone;

    // Observer Method
    @Override
    public void update(Pet pet) {
        // Implementation of update logic
    }

    // Observer Methods and constructors go here...

    // Getter and Setter methods

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

    @OneToMany(mappedBy = "owner")
    private List<Pet> pets = new ArrayList<>();
}
