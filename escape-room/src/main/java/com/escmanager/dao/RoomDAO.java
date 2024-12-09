package com.escmanager.dao;

import com.escmanager.model.EscapeRoom;
import com.escmanager.model.Room;

public interface RoomDAO extends DAO{

    void createRoom(Room room);
    void updateRoom(Room room);
    void updateRoomStatus(Room room);

}