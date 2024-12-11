package com.escmanager.service;

import com.escmanager.dao.EscapeRoomDAO;
import com.escmanager.dao.implementation.EscapeRoomImpl;
import com.escmanager.enums.Status;
import com.escmanager.exceptions.escaperoom.EscapeRoomAlreadyExistException;
import com.escmanager.exceptions.escaperoom.EscapeRoomDoesNotExistException;
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
        escapeRoom.setStatus(Status.ACTIVE);

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

//class TestService {
//    public static void main(String[] args) throws EscapeRoomAlreadyExistException, EscapeRoomDoesNotExistException {
//
//        EscapeRoomService service = new EscapeRoomService();
////        service.addEscapeRoom("Bar manolo", new BigDecimal(10));
////        service.deleteEscapeRoom(6);
//        service.getAllEscapeRooms();
//
//    }
//}