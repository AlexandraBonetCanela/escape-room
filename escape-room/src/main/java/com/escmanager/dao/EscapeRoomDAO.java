package com.escmanager.dao;

import com.escmanager.model.EscapeRoom;

public interface EscapeRoomDAO extends DAO{

    EscapeRoom create(EscapeRoom escapeRoom);
    EscapeRoom update(EscapeRoom escapeRoom);
    EscapeRoom findByName(String name);

}