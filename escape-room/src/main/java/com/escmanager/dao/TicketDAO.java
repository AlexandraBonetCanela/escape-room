package com.escmanager.dao;

import com.escmanager.model.Ticket;

import java.math.BigDecimal;

public interface TicketDAO extends DAO{

    Ticket create(Ticket ticket);
    Ticket update(Ticket ticket);
    Ticket getByName(String name);
    BigDecimal getTotalEarned();
  
}
