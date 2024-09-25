// model/User.java
package com.sda_project.myfluffy.model;

// import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GenerationType;
// import java.util.Observable;
// import java.util.Observer;
@Entity
public class User implements Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    // Other fields and methods...

    // Observer Method
    @Override
    public void update(Pet pet) {
        // Implementation of update logic
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

    @OneToMany(mappedBy = "owner")
    private List<Pet> pets = new ArrayList<>();

    // Observer Methods and constructors go here...

    // Getter and Setter methods
}
