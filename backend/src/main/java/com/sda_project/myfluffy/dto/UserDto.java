package com.sda_project.myfluffy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Schema(
        name = "User",
        description = "Schema to hold User information"
)
public class UserDto {

    private String name;

    private String email;

    private String phone;

}
