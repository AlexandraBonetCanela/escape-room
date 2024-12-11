package com.escmanager.dao;

import com.escmanager.model.EscapeRoom;

import java.util.List;

public interface DAO<T> {

    List<T> getAll();
    T getById(int id);

}