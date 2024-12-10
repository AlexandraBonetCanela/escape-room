package com.escmanager.dao;

import com.escmanager.model.EscapeRoom;

import java.util.List;

public interface EscapeRoomDAO extends DAO{

    EscapeRoom create(EscapeRoom escapeRoom);
    EscapeRoom update(EscapeRoom escapeRoom);
    EscapeRoom findById(int id);
    EscapeRoom findByName(String name);
    List<EscapeRoom> findAll();

}