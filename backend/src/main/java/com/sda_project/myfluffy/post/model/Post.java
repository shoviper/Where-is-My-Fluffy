// model/Post.java
package com.sda_project.myfluffy.post.model;

import com.sda_project.myfluffy.common.utils.enums.PostType;
import com.sda_project.myfluffy.pet.model.Pet;
import com.sda_project.myfluffy.user.model.User;
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
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp", insertable = false, updatable = false)
    private String timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PostType type;

    @Column(name = "reward_amount")
    private double rewardAmount;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "owner_id")
    private User postOwner;

}
