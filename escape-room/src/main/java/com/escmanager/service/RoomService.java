package com.escmanager.service;

import com.escmanager.dao.RoomDAO;
import com.escmanager.enums.DifficultyLevel;
import com.escmanager.enums.Status;
import com.escmanager.exceptions.room.RoomAlreadyExistsException;
import com.escmanager.exceptions.room.RoomDoesNotExistException;
import com.escmanager.model.Room;

import java.util.List;

public class RoomService {

    RoomDAO roomDAO;

    public Room addRoom(int escapeRoomId, DifficultyLevel difficultyLevel, String name, String theme) throws RoomAlreadyExistsException {

        Room room;

        room = roomDAO.findByNameAndEscaperoomId(name, escapeRoomId);

        if(room != null){
            throw new RoomAlreadyExistsException();
        }

        room = new Room();
        room.setEscaperoomId(escapeRoomId);
        room.setName(name);
        room.setDifficulty(difficultyLevel);
        room.setTheme(theme);
        room.setStatus(Status.ACTIVE);
        room.setElementQuantity(0);

        room = roomDAO.create(room);

        return room;
    }

    public boolean deleteRoom(int roomId) throws RoomDoesNotExistException {

        Room room = roomDAO.getById(roomId);

        if(room == null){
            throw new RoomDoesNotExistException();
        }

        room.setStatus(Status.INACTIVE);
        roomDAO.update(room);

        return true;
    }

    public List<Room> getAllRooms(){
        return roomDAO.getAll();
    }
}
