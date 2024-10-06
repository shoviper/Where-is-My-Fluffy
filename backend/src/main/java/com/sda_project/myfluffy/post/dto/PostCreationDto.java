package com.sda_project.myfluffy.post.dto;

import com.sda_project.myfluffy.common.utils.enums.PostType;
import lombok.Data;

@Data
public class PostCreationDto {

    private String title;

    private String content;

    private PostType type;  // You can also use PostType enum if preferred

    private double rewardAmount;

    private int petId;

    // No need to include ownerId, it will be fetched from the authenticated user
}
