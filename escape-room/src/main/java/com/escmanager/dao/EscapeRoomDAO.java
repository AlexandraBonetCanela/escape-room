package com.escmanager.dao;

import com.escmanager.model.EscapeRoom;

import java.util.List;

public interface EscapeRoomDAO {

    void createEscapeRoom(EscapeRoom escapeRoom);
    void update(EscapeRoom escapeRoom);
    void delete(int id);

    List<EscapeRoom> getAll();
    EscapeRoom getById(int id);


}
