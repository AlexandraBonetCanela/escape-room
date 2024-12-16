package com.escmanager.exceptions.ticket;

public class TicketDoesNotExistException extends Exception{
    public TicketDoesNotExistException(String message){
        super(message);
    }
}
