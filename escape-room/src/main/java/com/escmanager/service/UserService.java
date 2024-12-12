package com.escmanager.service;

import com.escmanager.dao.UserDAO;
import com.escmanager.dao.implementation.UserImpl;
import com.escmanager.exceptions.DaoException;
import com.escmanager.exceptions.UserAlreadyExistException;
import com.escmanager.exceptions.UserDoesNotExistException;
import com.escmanager.model.User;

import java.util.List;

public class UserService {

    UserDAO userDAO = new UserImpl();

    public User addUser(String email) throws UserAlreadyExistException, DaoException {

        User user = userDAO.findByEmail(email);

        if(user != null) {
            throw new UserAlreadyExistException();
        }
        user = new User();
        user.setEmail(email);

        return userDAO.create(user);
    }

    public User registerUser(String email, String name) throws UserAlreadyExistException, DaoException {

        User user = userDAO.findByEmail(email);

        if(user.isRegistered()){
            throw new UserAlreadyExistException();
        }

        if (user == null){
            user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setRegistered(true);
            user = userDAO.create(user);
            System.out.println("The user has created and registered");
        } else {
            user.setName(name);
            user.setRegistered(true);
            user = userDAO.update(user);
            System.out.println("The user has been registered");
        }
        return user;
    }

    public User updateUser(String name, String email, boolean registered, boolean notifications) throws DaoException, UserDoesNotExistException {

        User user = userDAO.findByEmail(email);

        if(!user.isRegistered()){
            throw new UserDoesNotExistException();
        }
        user.setName(name);
        user.setEmail(email);
        user.setRegistered(registered);
        user.setNotifications(notifications);
        user = userDAO.update(user);

        System.out.println("The user has been updated");
        return user;
    }

    public List<User> getAllUsers(){
        List user = userDAO.getAll();
        return user;
    }
}