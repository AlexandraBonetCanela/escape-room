package com.escmanager.exceptions.user;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException() {
        super("The user already exist");
    }
}
