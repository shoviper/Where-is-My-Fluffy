package com.sda_project.myfluffy.dto;

import lombok.Data;

@Data
public class PostUpdateDto {

    // private int id; // Existing post ID

    private String title;

    private String content;

    private String type;  // You can use PostType enum if preferred

    private int petId;
}
