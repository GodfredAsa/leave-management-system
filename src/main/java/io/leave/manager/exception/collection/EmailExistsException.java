package io.leave.manager.exception.collection;

public class EmailExistsException extends Exception{
    public EmailExistsException(String message) {
        super(message);
    }
}
