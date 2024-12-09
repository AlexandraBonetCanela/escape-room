package com.escmanager.exceptions;

public class EscapeRoomAlreadyExistException extends Exception {
    public EscapeRoomAlreadyExistException() {
        super("Escape Room already exists");
    }
}
