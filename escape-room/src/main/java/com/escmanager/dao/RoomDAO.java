package com.escmanager.dao;

import com.escmanager.model.Room;

import java.util.List;

public interface RoomDAO extends DAO<Room>{

    Room create(Room room);
    Room update(Room room);
    Room getByNameAndEscapeRoomId(String name, int escaperoomId);
    List<Room> getAllByEscapeRoomId(int escaperoomId);

}