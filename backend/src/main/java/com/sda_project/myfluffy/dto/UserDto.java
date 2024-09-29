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

    @Schema(
            description = "Name of the user", example = "Roshan"
    )
    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 1, max = 30, message = "The length of the customer name should be between 1 and 30")
    private String name;

    @Schema(
            description = "Email address of the user"
    )
    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(
            description = "Phone Number of the user", example = "0123456789"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String phone;

    @Schema(
            description = "Location details of the User"
    )
    private String location_id;

    @Schema(
            description = "Pets details of the Customer"
    )
    private List<PetDto> petsDto;
}
