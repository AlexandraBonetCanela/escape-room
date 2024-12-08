package com.escmanager.dao;

import com.escmanager.model.EscapeRoom;

public interface EscapeRoomDAO extends DAO{

    void create(EscapeRoom escapeRoom);
    void update(EscapeRoom escapeRoom);
    void delete(int id);

}