package com.sda_project.myfluffy.animaltype;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "animal_type")
public class Animaltype {
    
    @Id
    @Column(name = "type")
    private String type;
}
