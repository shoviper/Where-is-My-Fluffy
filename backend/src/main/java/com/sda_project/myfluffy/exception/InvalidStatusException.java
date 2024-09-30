package com.sda_project.myfluffy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidStatusException  extends RuntimeException {

    public InvalidStatusException(String message) {
        super(message);
    }
}
