package com.escmanager.dao;

import com.escmanager.model.EscapeRoom;

import java.util.List;

public interface EscapeRoomDAO {

    void save(EscapeRoom escapeRoom);
    EscapeRoom getById(int id);
    List<EscapeRoom> getAll();
    void update(EscapeRoom escapeRoom);
    void delete(int id);

}
