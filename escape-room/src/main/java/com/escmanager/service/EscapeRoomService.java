package com.escmanager.service;

import com.escmanager.dao.EscapeRoomDAO;
import com.escmanager.dao.implementation.EscapeRoomImpl;
import com.escmanager.enums.Status;
import com.escmanager.exceptions.escaperoom.EscapeRoomAlreadyExistException;
import com.escmanager.exceptions.escaperoom.EscapeRoomDoesNotExistException;
import com.escmanager.exceptions.room.RoomDoesNotExistException;
import com.escmanager.model.EscapeRoom;
import com.escmanager.model.Room;

import java.math.BigDecimal;
import java.util.List;


public class EscapeRoomService {


    RoomService roomService = RoomService.getInstance();

    private static EscapeRoomService instance = new EscapeRoomService();
    public static EscapeRoomService getInstance() {
        return instance;
    }
    private EscapeRoomService() {}

    EscapeRoomDAO escapeRoomDAO = new EscapeRoomImpl();

    public EscapeRoom addEscapeRoom(String name, BigDecimal price) throws EscapeRoomAlreadyExistException {

        EscapeRoom escapeRoom = escapeRoomDAO.getByName(name);

        if(escapeRoom != null){
            throw new EscapeRoomAlreadyExistException("Escaperoom with name " + name + " already exists");
        }

        escapeRoom = new EscapeRoom();
        escapeRoom.setName(name);
        escapeRoom.setStatus(Status.ACTIVE);
        escapeRoom.setPrice(price);

        escapeRoom = escapeRoomDAO.create(escapeRoom);

        return escapeRoom;
    }

    public boolean deleteEscapeRoom(int id) throws EscapeRoomDoesNotExistException {

        EscapeRoom escapeRoom = escapeRoomDAO.getById(id);

        if(escapeRoom == null){
            throw new EscapeRoomDoesNotExistException("Escaperoom with id " + id + " does not exists");
        }

        List<Room> rooms = roomService.findAllByEscaperoomId(id);
            rooms.forEach(a -> {
                try {
                    roomService.deleteRoom(a.getId());
                } catch (RoomDoesNotExistException e) {
                    //
                }
            });

        escapeRoom.setStatus(Status.INACTIVE);
        escapeRoomDAO.update(escapeRoom);

        return true;
    }

    public List<EscapeRoom> getAllEscapeRooms() {
        List<EscapeRoom> escapeRoomList = escapeRoomDAO.getAll();
        for (EscapeRoom escapeRooms : escapeRoomList){
            System.out.println(escapeRooms);
        }
        return escapeRoomList;
    }

    public EscapeRoom getById(int escaperoomId) {
        return escapeRoomDAO.getById(escaperoomId);
    }
}