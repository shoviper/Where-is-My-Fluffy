// model/User.java
package com.sda_project.myfluffy.user;

import com.sda_project.myfluffy.utils.Observer;
import com.sda_project.myfluffy.pet.Pet;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements Observer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "location")
    private String location;

    // Observer Method
    @Override
    public void update(Pet pet) {
        // Implementation of update logic
    }

    // Observer Methods and constructors go here...

}
