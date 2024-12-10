package com.escmanager.exceptions.room;

public class RoomAlreadyExistsException extends Exception {
    public RoomAlreadyExistsException() {
        super("Room already exists for this escaperoom");
    }
}
