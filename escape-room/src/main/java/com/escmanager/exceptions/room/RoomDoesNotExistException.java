package com.escmanager.exceptions.room;

public class RoomDoesNotExistException extends Exception{
    public RoomDoesNotExistException(){
        super("Room does not exist");
    }
}
