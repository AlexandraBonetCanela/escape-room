package com.escmanager.exceptions.user;

public class UserAlreadyRegisteredException extends Exception {
    public UserAlreadyRegisteredException() {
        super("The user is already registered");
    }
}
