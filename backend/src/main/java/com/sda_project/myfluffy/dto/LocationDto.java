package com.sda_project.myfluffy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data

@Schema(
        name = "Location",
        description = "Schema to hold Location information"
)
public class LocationDto {

    private String address;

    private String addressUrl;

}
