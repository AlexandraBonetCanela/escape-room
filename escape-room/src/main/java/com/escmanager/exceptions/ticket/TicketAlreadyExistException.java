package com.escmanager.exceptions.ticket;

public class TicketAlreadyExistException extends Exception{
    public TicketAlreadyExistException(String message){
        super(message);
    }
}
