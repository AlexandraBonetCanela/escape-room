package com.escmanager.dao;

import com.escmanager.model.User;

import java.util.List;

public interface UserDAO extends DAO{

    User create(User user);
    User update(User user);
    User getByEmail(String email);
    List<User> getAllSubscribers();
}
