// model/Post.java
package com.sda_project.myfluffy.model;

// import javax.persistence.*;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;

    @ManyToOne
    private Pet pet;

    // Abstract methods and constructors go here...

    // Getter and Setter methods
}

// Define subclasses for MissingPetPost, FoundPetPost, and AlertPost similarly
