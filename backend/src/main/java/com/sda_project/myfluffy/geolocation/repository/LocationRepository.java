package com.sda_project.myfluffy.geolocation.repository;

import com.sda_project.myfluffy.geolocation.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}
