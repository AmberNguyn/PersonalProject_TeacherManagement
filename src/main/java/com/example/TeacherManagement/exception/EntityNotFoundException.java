package com.example.TeacherManagement.exception;

import lombok.Getter;


// ----- THIS CLASS IS AN EXCEPTION CLASS
@Getter
public class EntityNotFoundException extends Exception {
    private final String errorKey;

    public EntityNotFoundException(String errorKey) {
        this.errorKey = errorKey;
    }

    public EntityNotFoundException(String message, String errorKey) {
        super(message);
        this.errorKey = errorKey;
    }

    public EntityNotFoundException(String message, Throwable cause, String errorKey) {
        super(message, cause);
        this.errorKey = errorKey;
    }

    public EntityNotFoundException(Throwable cause, String errorKey) {
        super(cause);
        this.errorKey = errorKey;
    }
}
