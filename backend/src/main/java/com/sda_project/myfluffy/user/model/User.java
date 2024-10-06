// model/User.java
package com.sda_project.myfluffy.user.model;

import com.sda_project.myfluffy.geolocation.model.Location;
import com.sda_project.myfluffy.notification.model.Notification;
import com.sda_project.myfluffy.pet.model.Pet;
import com.sda_project.myfluffy.post.model.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "user_image")
    private String userImage;

    @Column(name = "balance")
    private double balance;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_location_id")
    private Location userLocation;

    @OneToMany(mappedBy = "petOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> myPets;

    @OneToMany(mappedBy = "founder", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> petsFound;

    @OneToMany(mappedBy = "postOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> myPosts;

    @OneToMany(mappedBy = "notificationOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> myNotifications;

}
