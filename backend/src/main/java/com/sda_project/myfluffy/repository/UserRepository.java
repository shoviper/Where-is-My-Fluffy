

// repository/UserRepository.java
package com.sda_project.myfluffy.repository;

import com.sda_project.myfluffy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
    
