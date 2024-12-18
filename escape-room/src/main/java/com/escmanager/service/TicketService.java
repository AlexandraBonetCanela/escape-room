package com.escmanager.service;

import com.escmanager.dao.implementation.TicketImpl;
import com.escmanager.exceptions.DaoException;
import com.escmanager.exceptions.UserNotFoundException;
import com.escmanager.exceptions.ticket.TicketAlreadyExistException;
import com.escmanager.exceptions.ticket.TicketDoesNotExistException;
import com.escmanager.model.Ticket;

import java.math.BigDecimal;
import java.util.List;

public class TicketService {

    private static TicketService instance = new TicketService();
    public static TicketService getInstance() {
        return instance;
    }
    protected TicketService() {}

    TicketImpl ticketDao = new TicketImpl();

    public Ticket createTicket(int user_id, int escape_room_id, BigDecimal unit_price, int quantity, BigDecimal total_price)
            throws DaoException, TicketAlreadyExistException, UserNotFoundException {

        Ticket ticket = ticketDao.getById(user_id);

        if(ticket == null){
            throw new UserNotFoundException("The user with the id: " + user_id + " couldn't be found");
        }

        ticket = new Ticket();
        ticket.setUser_id(user_id);
        ticket.setEscape_room_id(escape_room_id);
        ticket.setUnit_price(unit_price);
        ticket.setQuantity(quantity);
        ticket.setTotal_price(total_price);
        ticketDao.create(ticket);

        return ticket;
    }

    public boolean updateTicket(int id, int user_id, int escape_room_id, BigDecimal unit_price, int quantity, BigDecimal total_price)
            throws DaoException, TicketDoesNotExistException {

        Ticket ticket = ticketDao.getById(id);

        if(ticket == null){
            throw new TicketDoesNotExistException("The ticket doesn't exist");
        }

        ticket.setUser_id(user_id);
        ticket.setEscape_room_id(escape_room_id);
        ticket.setUnit_price(unit_price);
        ticket.setQuantity(quantity);
        ticket.setTotal_price(total_price);
        ticketDao.update(ticket);

        return true;
    }

    public BigDecimal getMoneyEarned() throws DaoException {
        return ticketDao.getTotalEarned();
    }

    public List<Ticket> getAllTickets() throws DaoException{
        List<Ticket> ticketList = ticketDao.getAll();
        for (Ticket ticket : ticketList){
            System.out.println(ticket);
        }
        return ticketList;
    }

}