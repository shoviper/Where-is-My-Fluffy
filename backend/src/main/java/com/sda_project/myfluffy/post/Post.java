// model/Post.java
package com.sda_project.myfluffy.post;

// import javax.persistence.*;

import com.sda_project.myfluffy.pet.Pet;
import com.sda_project.myfluffy.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    // Abstract methods and constructors go here...

}

// Define subclasses for MissingPetPost, FoundPetPost, and AlertPost similarly
