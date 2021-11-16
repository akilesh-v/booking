package com.booking.room.common.exceptions;

public class FailedDependencyException extends RuntimeException{
    public FailedDependencyException(String message) {
        super(message);
    }
}
