// model/Post.java
package com.sda_project.myfluffy.post;

// import javax.persistence.*;

import com.sda_project.myfluffy.pet.Pet;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;

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

    public static interface PostRepository extends JpaRepository<Post, Integer> {
    }
}

// Define subclasses for MissingPetPost, FoundPetPost, and AlertPost similarly
