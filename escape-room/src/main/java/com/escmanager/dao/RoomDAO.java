package com.escmanager.dao;

import com.escmanager.model.Room;

import java.util.List;

public interface RoomDAO extends DAO<Room>{

    Room create(Room room);
    Room update(Room room);
    Room findByNameAndEscaperoomId(String name, int escaperoomId);
    List<Room> findAllByEscaperoomId(int escaperoomId);

}
