package com.escmanager.service;

import com.escmanager.dao.UserDAO;
import com.escmanager.model.User;

public class UserService {

    UserDAO userDAO;

    public User addUser(String email){

        User user = userDAO.findByEmail(email);

        if (user == null){
            user = new User();
            user.setEmail(email);
            user = userDAO.create(user);
        }
        return user;
    }

    public User registerUser(String email, String name) {

        User user = userDAO.findByEmail(email);

        if (user == null){
            user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setRegistered(true);
            user = userDAO.create(user);
        } else {
            user.setName(name);
            user.setRegistered(true);
            user = userDAO.update(user);
        }
        return user;
    }
}
