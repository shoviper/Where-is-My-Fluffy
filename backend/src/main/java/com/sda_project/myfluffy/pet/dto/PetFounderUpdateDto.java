package com.sda_project.myfluffy.pet.dto;

import com.sda_project.myfluffy.common.utils.enums.Status;
import lombok.Data;

@Data
public class PetFounderUpdateDto {

    private int petId;

    private int founderId;
}
