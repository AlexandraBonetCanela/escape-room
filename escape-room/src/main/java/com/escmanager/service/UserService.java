package com.escmanager.service;

import com.escmanager.dao.UserDAO;
import com.escmanager.dao.UserImpl;
import com.escmanager.exceptions.DaoException;
import com.escmanager.exceptions.UserAlreadyExistException;
import com.escmanager.model.User;

public class UserService {

    UserDAO userDAO = new UserImpl();

    public User addUser(String email) throws UserAlreadyExistException, DaoException {

        User user = userDAO.findByEmail(email);

        if(user != null) {
            throw new UserAlreadyExistException("User: " + user.getName() + " already exist");
        }

        user = new User();
        user.setEmail(email);

        return userDAO.create(user);
    }

    public User registerUser(String email, String name) throws UserAlreadyExistException, DaoException {

        User user = userDAO.findByEmail(email);

        if(user.isRegistered()) {
            throw new UserAlreadyExistException("");
        }

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
