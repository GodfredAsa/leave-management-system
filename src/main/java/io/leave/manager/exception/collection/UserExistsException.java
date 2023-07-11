package io.leave.manager.exception.collection;

public class UserExistsException extends Exception{
    public UserExistsException(String message) {
        super(message);
    }
}
