package edu.vdt.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
