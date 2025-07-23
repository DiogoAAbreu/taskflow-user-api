package dev.diogoalberto.taskflow_user_api.infrastructure.exception;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
    public ConflictException(String message, Throwable cause){super(message, cause);}
}
