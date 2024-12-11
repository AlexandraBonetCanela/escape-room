package com.escmanager.service;

import com.escmanager.dao.EscapeRoomDAO;
import com.escmanager.dao.implementation.EscapeRoomImpl;
import com.escmanager.enums.Status;
import com.escmanager.exceptions.EscapeRoomAlreadyExistException;
import com.escmanager.exceptions.EscapeRoomDoesNotExistException;
import com.escmanager.model.EscapeRoom;

import java.math.BigDecimal;
import java.util.List;

public class EscapeRoomService {

    EscapeRoomDAO escapeRoomDAO = new EscapeRoomImpl();

    public EscapeRoom addEscapeRoom(String name, BigDecimal price) throws EscapeRoomAlreadyExistException {

        EscapeRoom escapeRoom = escapeRoomDAO.findByName(name);

        if(escapeRoom != null){
            throw new EscapeRoomAlreadyExistException();
        }

        escapeRoom = new EscapeRoom();
        escapeRoom.setName(name);
        escapeRoom.setPrice(price);

        escapeRoom = escapeRoomDAO.create(escapeRoom);

        return escapeRoom;
    }

    public boolean deleteEscapeRoom(int id) throws EscapeRoomDoesNotExistException {

        EscapeRoom escapeRoom = (EscapeRoom) escapeRoomDAO.getById(id);

        if(escapeRoom == null){
            throw new EscapeRoomDoesNotExistException();
        }
        escapeRoom.setStatus(Status.INACTIVE);
        escapeRoomDAO.update(escapeRoom);

        return true;
    }

    public List<EscapeRoom> getAllEscapeRooms() {

        List<EscapeRoom> escapeRooms = escapeRoomDAO.getAll();

        return escapeRooms;
    }
}