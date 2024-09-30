package com.sda_project.myfluffy.post.repository;

import com.sda_project.myfluffy.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface PostRepository extends JpaRepository<Post, Integer> {
}