// model/User.java
package com.sda_project.myfluffy.user;

import com.sda_project.myfluffy.utils.Observer;
import com.sda_project.myfluffy.pet.Pet;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User implements Observer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "location", length = 100)
    private String location;

    // Observer Method
    @Override
    public void update(Pet pet) {
        // Implementation of update logic
    }

    // Observer Methods and constructors go here...

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocationId() {
        return location;
    }

    public void setLocationId(String location) {
        this.location = location;
    }

}
