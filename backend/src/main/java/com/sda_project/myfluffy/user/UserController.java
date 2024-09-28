package com.sda_project.myfluffy.user;

import com.sda_project.myfluffy.dto.ResponseDto;
import com.sda_project.myfluffy.dto.UserDto;
import com.sda_project.myfluffy.utils.UsersConstants;
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
                .body(new ResponseDto(UsersConstants.STATUS_201, UsersConstants.MESSAGE_201));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> profile(@AuthenticationPrincipal OAuth2User principal) {
        UserDto userDto = iUserService.fetchMe(principal);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDto);
    }

    @GetMapping
    public ResponseEntity<UserDto> fetchUserDetailsById(@RequestBody Integer id) {
        UserDto customerDto = iUserService.fetchAccount(id);
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(customerDto);
    }

    @GetMapping
    public ResponseEntity<UserDto> fetchUserDetailsByEmail(@RequestBody String email) {
        UserDto customerDto = iUserService.fetchAccount(email);
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(customerDto);
    }

}