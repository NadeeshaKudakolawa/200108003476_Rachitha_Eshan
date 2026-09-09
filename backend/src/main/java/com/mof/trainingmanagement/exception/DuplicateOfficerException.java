package com.mof.trainingmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateOfficerException extends RuntimeException {

    public DuplicateOfficerException(String message) {
        super(message);
    }
}
