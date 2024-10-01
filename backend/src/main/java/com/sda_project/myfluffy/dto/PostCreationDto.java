package com.sda_project.myfluffy.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PostCreationDto {

    private String title;

    private String content;

    private String type;  // You can also use PostType enum if preferred

    private int petId;

    // No need to include ownerId, it will be fetched from the authenticated user
}
