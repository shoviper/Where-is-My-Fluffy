// repository/PostRepository.java
package com.sda_project.myfluffy.repository;

import com.sda_project.myfluffy.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
