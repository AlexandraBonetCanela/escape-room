package com.escmanager.exceptions;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String name) {
        super(name);
    }
}
