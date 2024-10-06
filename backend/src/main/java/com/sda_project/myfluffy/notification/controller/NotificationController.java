package com.sda_project.myfluffy.notification.controller;

import com.sda_project.myfluffy.common.dto.response.ResponseDto;
import com.sda_project.myfluffy.common.exception.ResourceNotFoundException;
import com.sda_project.myfluffy.common.utils.constants.AppConstants;
import com.sda_project.myfluffy.common.utils.enums.NotificationType;
import com.sda_project.myfluffy.notification.dto.NotificationCreateDto;
import com.sda_project.myfluffy.notification.dto.NotificationCreateResponseDto;
import com.sda_project.myfluffy.notification.dto.NotificationDto;
import com.sda_project.myfluffy.notification.service.INotificationService;

import com.sda_project.myfluffy.pet.dto.PetCreateDto;
import com.sda_project.myfluffy.user.dto.UserPhoneUpdateDto;
import com.sda_project.myfluffy.user.model.User;
import com.sda_project.myfluffy.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping(path = "/notifications", produces = { MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
public class NotificationController {

    private INotificationService iNotificationService;
    private UserRepository userRepository;

    @Operation(summary = "Create Notification send to user id")
    @PostMapping("/{userId}/user")
    public ResponseEntity<NotificationCreateResponseDto> createPet(@PathVariable int userId ,
                                                                   @RequestBody NotificationCreateDto notificationCreateDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", Integer.toString(userId)));
        NotificationCreateResponseDto notificationCreateResponseDto = iNotificationService.createNotification(user, notificationCreateDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(notificationCreateResponseDto);
    }

    @Operation(summary = "Get All Notifications")
    @GetMapping
    public ResponseEntity<List<NotificationDto>> getAllNotifications() {
        List<NotificationDto> notificationDtos = iNotificationService.getAllNotifications();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notificationDtos);
    }

    @Operation(summary = "Get Notification by Notification Id")
    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> fetchNotificationDetailsById(@PathVariable int id) {
        NotificationDto notificationDto = iNotificationService.fetchNotificationById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(notificationDto);
    }

    @Operation(summary = "Get My Notification: OAuth2User")
    @GetMapping("/me")
    public ResponseEntity<List<NotificationDto>> fetchMyNotificationDetails(
            @AuthenticationPrincipal OAuth2User principal) {
        List<NotificationDto> notificationDtos = iNotificationService.fetchMyNotification(principal);
        return ResponseEntity.status(HttpStatus.OK)
                .body(notificationDtos);
    }

    @Operation(summary = "Delete Pet by Pet Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteNotificationDetails(@PathVariable int id) {
        boolean isDeleted = iNotificationService.deleteNotification(id);
        if (isDeleted) {
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
                            .statusMsg(AppConstants.MESSAGE_417_DELETE)
                            .build());
        }
    }

    @PutMapping("/{notificationId}/uploadImage")
    public ResponseEntity<ResponseDto> uploadPetImage(@RequestParam("file") MultipartFile file,
            @PathVariable int notificationId) {
        try {
            byte[] fileBytes = file.getBytes();
            String base64Image = Base64.getEncoder().encodeToString(fileBytes);

            iNotificationService.updateNotificationImageBase64(notificationId, base64Image);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder()
                            .statusCode(AppConstants.STATUS_200)
                            .statusMsg(AppConstants.MESSAGE_200)
                            .build());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDto.builder()
                            .statusCode(AppConstants.STATUS_417)
                            .statusMsg(AppConstants.MESSAGE_417_UPDATE)
                            .build());
        }
    }


    @PutMapping("/{notificationId}/update-notification-status/{notificationType}")
    public ResponseEntity<ResponseDto> updateNotificationStatus(@PathVariable int notificationId, @PathVariable NotificationType notificationType) {
        boolean isUpdated = iNotificationService.updateNotificationStatus(notificationId, notificationType);
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
