package com.escmanager.dao;

import com.escmanager.model.EscapeRoom;

public interface EscapeRoomDAO extends DAO<EscapeRoom>{

    EscapeRoom create(EscapeRoom escapeRoom);
    EscapeRoom update(EscapeRoom escapeRoom);
    EscapeRoom getByName(String name);

}