package com.escmanager.service;

import com.escmanager.dao.implementation.TicketImpl;
import com.escmanager.exceptions.DaoException;
import com.escmanager.exceptions.ticket.TicketAlreadyExistException;
import com.escmanager.exceptions.ticket.TicketDoesNotExistException;
import com.escmanager.model.Ticket;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TicketService {

    TicketImpl service = new TicketImpl();

    public Ticket createTicket(int user_id, int escape_room_id, BigDecimal unit_price, int quantity, BigDecimal total_price, Date date_reservation)
            throws DaoException, TicketAlreadyExistException {

        Ticket ticket = service.getById(user_id);

        if(ticket != null){
            throw new TicketAlreadyExistException("The ticket already exists");
        }

        ticket = new Ticket();
        ticket.setUser_id(user_id);
        ticket.setEscape_room_id(escape_room_id);
        ticket.setUnit_price(unit_price);
        ticket.setQuantity(quantity);
        ticket.setTotal_price(total_price);
        ticket.setReservation_date(date_reservation);
        service.create(ticket);

        return ticket;
    }

    public boolean updateTicket(int id, int user_id, int escape_room_id, int unit_price, int quantity, int total_price, Date date_reservation)
            throws DaoException, TicketDoesNotExistException {

        Ticket ticket = service.getById(id);

        if(ticket == null){
            throw new TicketDoesNotExistException("The ticket doesn't exist");
        }

        ticket.setUser_id(user_id);
        ticket.setEscape_room_id(escape_room_id);
        ticket.setUnit_price(new BigDecimal(unit_price));
        ticket.setQuantity(quantity);
        ticket.setTotal_price(new BigDecimal(total_price));
        ticket.setReservation_date(date_reservation);
        service.update(ticket);

        return true;
    }

    public List<Ticket> getAllTickets() throws DaoException{
        return service.getAll();
    }

}


class test{
    public static void main(String[] args) throws TicketAlreadyExistException {

        TicketService service = new TicketService();
        service.createTicket(1,1,5,20, 10, new Date());

    }
}