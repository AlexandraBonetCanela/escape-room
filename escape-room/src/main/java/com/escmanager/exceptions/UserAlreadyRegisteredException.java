package com.escmanager.exceptions;

public class UserAlreadyRegisteredException extends Exception {
    public UserAlreadyRegisteredException() {
        super("The user is already registered");
    }
}
