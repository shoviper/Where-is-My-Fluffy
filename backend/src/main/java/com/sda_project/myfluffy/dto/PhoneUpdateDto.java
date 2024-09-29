package com.sda_project.myfluffy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "Phone",
        description = "Schema to update User's phone number"
)
public class PhoneUpdateDto {
    private String phone;
}