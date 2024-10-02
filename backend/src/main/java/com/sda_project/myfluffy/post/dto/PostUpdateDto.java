package com.sda_project.myfluffy.post.dto;

import com.sda_project.myfluffy.common.utils.enums.PostType;
import lombok.Data;

@Data
public class PostUpdateDto {

    // private int id; // Existing post ID

    private String title;

    private String content;

    private PostType type;  // You can use PostType enum if preferred

    private int petId;
}
