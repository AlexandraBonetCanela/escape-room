package com.escmanager.exceptions;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException() {
        super("User does Not Exist");
    }
}
