// repository/UserRepository.java
package com.sda_project.myfluffy.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
    
