package com.escmanager.exceptions.user;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException() {
        super("User does Not Exist");
    }
}
