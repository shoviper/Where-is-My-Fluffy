package com.sda_project.myfluffy.notification.controller;

import com.sda_project.myfluffy.common.dto.response.ResponseDto;
import com.sda_project.myfluffy.common.utils.constants.AppConstants;
import com.sda_project.myfluffy.notification.dto.NotificationCreateDto;
import com.sda_project.myfluffy.notification.dto.NotificationDto;
import com.sda_project.myfluffy.notification.service.INotificationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/notifications", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class NotificationController {

    private INotificationService iNotificationService;


    @Operation(
            summary = "Get All Notifications"
    )
    @GetMapping
    public ResponseEntity<List<NotificationDto>> getAllNotifications() {
        List<NotificationDto> notificationDtos = iNotificationService.getAllNotifications();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notificationDtos);
    }

    @Operation(
            summary = "Get Notification by Notification Id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> fetchNotificationDetailsById(@PathVariable int id) {
        NotificationDto notificationDto = iNotificationService.fetchNotificationById(id);
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(notificationDto);
    }

    @Operation(
            summary = "Get My Notification: OAuth2User"
    )
    @GetMapping("/me")
    public ResponseEntity<List<NotificationDto>> fetchMyNotificationDetails(@AuthenticationPrincipal OAuth2User principal) {
        List<NotificationDto> notificationDtos = iNotificationService.fetchMyNotification(principal);
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(notificationDtos);
    }

    @Operation(
            summary = "Delete Pet by Pet Id"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteNotificationDetails(@PathVariable int id) {
        boolean isDeleted = iNotificationService.deleteNotification(id);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder()
                            .statusCode(AppConstants.STATUS_200)
                            .statusMsg(AppConstants.MESSAGE_200)
                            .build());
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDto.builder()
                            .statusCode(AppConstants.STATUS_417)
                            .statusMsg(AppConstants.MESSAGE_417_DELETE)
                            .build());
        }
    }
}
