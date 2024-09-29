package com.sda_project.myfluffy.user;

import com.sda_project.myfluffy.dto.PhoneUpdateDto;
import com.sda_project.myfluffy.dto.ResponseDto;
import com.sda_project.myfluffy.dto.UserDto;
import com.sda_project.myfluffy.utils.Constants;
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

    @PostMapping
    public ResponseEntity<ResponseDto> createAccount(@AuthenticationPrincipal OAuth2User principal) {
        iUserService.createUser(principal);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(Constants.STATUS_201, Constants.MESSAGE_201));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> profile(@AuthenticationPrincipal OAuth2User principal) {
        UserDto userDto = iUserService.fetchMe(principal);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDto);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> fetchUserDetailsByEmail(@PathVariable String email) {
        UserDto userDto = iUserService.fetchUserByEmail(email);
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(userDto);
    }

    @PutMapping("/update-phone-number")
    public ResponseEntity<ResponseDto> updatePhoneNumber(@AuthenticationPrincipal OAuth2User principal,
                                                         @RequestBody PhoneUpdateDto phoneUpdateDto) {
        boolean isUpdated = iUserService.updatePhoneNumber(principal, phoneUpdateDto);
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