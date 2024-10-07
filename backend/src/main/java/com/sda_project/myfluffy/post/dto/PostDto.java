package com.sda_project.myfluffy.post.dto;

import com.sda_project.myfluffy.common.utils.enums.PostType;
import com.sda_project.myfluffy.pet.dto.PetDto;
import com.sda_project.myfluffy.user.dto.UserDto;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(
        name = "Post",
        description = "Schema to hold Post information"
)
public class PostDto {

    private int id;

    private String title;

    private String content;

    private String timestamp;

    private double rewardAmount;

    private PostType type;

    private UserDto user;

    private PetDto pet;
}
