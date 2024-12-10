package com.escmanager.dao;

import com.escmanager.model.EscapeRoom;
import com.escmanager.model.Room;

public interface RoomDAO extends DAO{

    void create(Room room);
    void update(Room room);

}