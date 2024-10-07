package com.sda_project.myfluffy.post.repository;

import com.sda_project.myfluffy.post.model.Post;
import com.sda_project.myfluffy.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findByPostOwner(User postOwner, Pageable pageable);

    Page<Post> findAll(Pageable pageable);
}