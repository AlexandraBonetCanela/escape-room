package com.escmanager.exceptions;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException() {
        super("The user already exist");
    }
}
