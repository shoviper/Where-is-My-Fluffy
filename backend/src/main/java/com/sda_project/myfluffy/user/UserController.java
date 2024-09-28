package com.sda_project.myfluffy.user;

import com.sda_project.myfluffy.dto.ResponseDto;
import com.sda_project.myfluffy.dto.UserDto;
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
                .body(new ResponseDto("201", "User created successfully"));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> profile(@AuthenticationPrincipal OAuth2User principal) {
        UserDto userDto = iUserService.fetchMe(principal);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

}