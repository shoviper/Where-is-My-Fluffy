package com.sda_project.myfluffy.common.dto.response;

import lombok.Data;

@Data
public class PostCreationDto {

    private String title;

    private String content;

    private String type;  // You can also use PostType enum if preferred

    private int petId;

    // No need to include ownerId, it will be fetched from the authenticated user
}