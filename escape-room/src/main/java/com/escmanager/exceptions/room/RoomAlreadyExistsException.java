package com.escmanager.exceptions.room;

public class RoomAlreadyExistsException extends Exception {
    public RoomAlreadyExistsException(String message) {
        super("Room already exists for this escaperoom");
    }
}
