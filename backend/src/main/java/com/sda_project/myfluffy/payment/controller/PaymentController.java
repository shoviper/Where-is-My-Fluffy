package com.sda_project.myfluffy.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sda_project.myfluffy.common.dto.response.ResponseDto;
import com.sda_project.myfluffy.common.utils.constants.AppConstants;
import com.sda_project.myfluffy.notification.service.INotificationService;
import com.sda_project.myfluffy.payment.dto.PaymentDto;
import com.sda_project.myfluffy.user.service.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.http.MediaType;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/payments", produces = { MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
public class PaymentController {

    private IUserService iUserService;

    @Operation(summary = "Update account")
    @PutMapping("/update-balance")
    public ResponseEntity<ResponseDto> updateBalance(@RequestBody PaymentDto paymentDto) {
        boolean isUpdated = iUserService.updateBalance(paymentDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder()
                            .statusCode(AppConstants.STATUS_200)
                            .statusMsg(AppConstants.MESSAGE_200)
                            .build());
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDto.builder()
                            .statusCode(AppConstants.STATUS_417)
                            .statusMsg(AppConstants.MESSAGE_417_UPDATE)
                            .build());
        }
    }

}
