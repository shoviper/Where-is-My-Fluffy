// model/Post.java
package com.sda_project.myfluffy.post;

// import javax.persistence.*;

import com.sda_project.myfluffy.pet.Pet;
import com.sda_project.myfluffy.user.User;
import com.sda_project.myfluffy.enums.PostType;
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

    @Column(name="timestamp", insertable = false, updatable = false)
    private String timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private PostType type;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    // Abstract methods and constructors go here...

}

// Define subclasses for MissingPetPost, FoundPetPost, and AlertPost similarly
