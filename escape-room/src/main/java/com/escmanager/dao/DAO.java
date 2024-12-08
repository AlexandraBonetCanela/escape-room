package com.escmanager.dao;

import com.escmanager.model.EscapeRoom;

import java.util.List;

public interface DAO {

    List<EscapeRoom> getAll();
    EscapeRoom getById(int id);

}