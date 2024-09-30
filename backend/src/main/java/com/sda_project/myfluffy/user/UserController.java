package com.sda_project.myfluffy.user;

import com.sda_project.myfluffy.dto.userDto.UserPhoneUpdateDto;
import com.sda_project.myfluffy.dto.responseDto.ResponseDto;
import com.sda_project.myfluffy.dto.userDto.UserDto;
import com.sda_project.myfluffy.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/users", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class UserController {

    private IUserService iUserService;

    @Operation(
            summary = "Create User: OAuth2User"
    )
    @PostMapping
    public ResponseEntity<ResponseDto> createAccount(@AuthenticationPrincipal OAuth2User principal) {
        iUserService.createUser(principal);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(Constants.STATUS_201, Constants.MESSAGE_201));
    }

    @Operation(
            summary = "Get User Profile: OAuth2User"
    )
    @GetMapping("/me")
    public ResponseEntity<UserDto> profile(@AuthenticationPrincipal OAuth2User principal) {
        UserDto userDto = iUserService.fetchMe(principal);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDto);
    }

    @Operation(
            summary = "Get User by User Email"
    )
    @GetMapping("/{email}")
    public ResponseEntity<UserDto> fetchUserDetailsByEmail(@PathVariable String email) {
        UserDto userDto = iUserService.fetchUserByEmail(email);
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(userDto);
    }

    @Operation(
            summary = "Update User Phone Number: OAuth2User"
    )
    @PutMapping("/update-phone-number")
    public ResponseEntity<ResponseDto> updatePhoneNumber(@AuthenticationPrincipal OAuth2User principal,
                                                         @RequestBody UserPhoneUpdateDto userPhoneUpdateDto) {
        boolean isUpdated = iUserService.updatePhoneNumber(principal, userPhoneUpdateDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(Constants.STATUS_200, Constants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(Constants.STATUS_417, Constants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete User by User Email"
    )
    @DeleteMapping("/{email}")
    public ResponseEntity<ResponseDto> deleteUserDetails(@PathVariable String email) {
        boolean isDeleted = iUserService.deleteUser(email);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(Constants.STATUS_200, Constants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(Constants.STATUS_417, Constants.MESSAGE_417_DELETE));
        }
    }

}