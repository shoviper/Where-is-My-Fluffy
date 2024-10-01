package com.sda_project.myfluffy.dto;

import lombok.Data;
import com.sda_project.myfluffy.enums.PostType;

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

    private PostType type;

    private UserDto userDto;

    private PetDto petDto;
}
