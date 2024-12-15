package com.escmanager.service;

import com.escmanager.dao.RoomDAO;
import com.escmanager.dao.implementation.RoomImpl;
import com.escmanager.enums.DifficultyLevel;
import com.escmanager.enums.Status;
import com.escmanager.exceptions.DaoException;
import com.escmanager.exceptions.room.RoomAlreadyExistsException;
import com.escmanager.exceptions.room.RoomDoesNotExistException;
import com.escmanager.model.Room;

import java.util.List;

public class RoomService {

    RoomDAO roomDAO = new RoomImpl();

    public Room addRoom(int escapeRoomId, DifficultyLevel difficultyLevel, String name, String theme) throws RoomAlreadyExistsException, DaoException {

        Room room = roomDAO.findByNameAndEscaperoomId(name, escapeRoomId);

        if(room != null){
            throw new RoomAlreadyExistsException("Room with name " + name + " already exists");
        }

        room = new Room();
        room.setEscaperoomId(escapeRoomId);
        room.setName(name);
        room.setDifficulty(difficultyLevel);
        room.setTheme(theme);
        room.setStatus(Status.ACTIVE);
        room.setElementQuantity(0);

        return roomDAO.create(room);
    }

    public boolean deleteRoom(int roomId) throws RoomDoesNotExistException, DaoException {

        Room room = roomDAO.getById(roomId);

        if(room == null){
            throw new RoomDoesNotExistException("Room with id " + roomId + " does not exist");
        }

        room.setStatus(Status.INACTIVE);
        roomDAO.update(room);

        return true;
    }

    public List<Room> getAllRooms() throws DaoException{

            return roomDAO.getAll();
    }
}