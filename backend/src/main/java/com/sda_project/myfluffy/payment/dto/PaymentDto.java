package com.sda_project.myfluffy.payment.dto;

import lombok.Data;

@Data
public class PaymentDto {

    private int senderId;

    private int receiverId;

    private double amount;

}
