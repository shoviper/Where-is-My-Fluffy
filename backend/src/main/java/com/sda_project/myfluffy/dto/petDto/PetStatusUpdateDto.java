package com.sda_project.myfluffy.dto.petDto;

import com.sda_project.myfluffy.enums.Status;
import lombok.Data;

@Data
public class PetStatusUpdateDto {

    private int id;

    private Status status;
}
