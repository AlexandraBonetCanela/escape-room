package com.escmanager.dao;

import com.escmanager.model.Ticket;

public interface TicketDAO extends DAO{

    Ticket create(Ticket ticket);
    Ticket update(Ticket ticket);
    Ticket getByName(String name);

}
