package com.escmanager.dao;

import com.escmanager.model.User;

public interface UserDAO extends DAO{

    User create(User user);
    User update(User user);
    User findByEmail(String email);
}
