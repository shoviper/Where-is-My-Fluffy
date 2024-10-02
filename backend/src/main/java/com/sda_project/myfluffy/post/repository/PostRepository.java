package com.sda_project.myfluffy.post.repository;

import com.sda_project.myfluffy.post.model.Post;
import com.sda_project.myfluffy.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByPostOwner(User postOwner);
}