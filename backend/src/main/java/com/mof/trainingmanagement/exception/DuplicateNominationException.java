package com.mof.trainingmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateNominationException extends RuntimeException {

    public DuplicateNominationException(String message) {
        super(message);
    }
}
