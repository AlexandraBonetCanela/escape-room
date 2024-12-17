package com.escmanager.service;

import com.escmanager.dao.UserDAO;
import com.escmanager.dao.implementation.UserImpl;
import com.escmanager.exceptions.DaoException;
import com.escmanager.exceptions.user.UserAlreadyExistException;
import com.escmanager.exceptions.user.UserAlreadyRegisteredException;
import com.escmanager.exceptions.user.UserDoesNotExistException;
import com.escmanager.model.User;

import java.util.List;

public class UserService {

    private static UserService instance = new UserService();
    public static UserService getInstance() {
        return instance;
    }
    private UserService() {}

    UserDAO userDAO = new UserImpl();

    public User addUser(String email) throws UserAlreadyExistException, DaoException {

        User user = userDAO.getByEmail(email);

        if(user == null) {
            throw new UserAlreadyExistException();
        }
        user = new User();
        user.setEmail(email);

        return userDAO.create(user);
    }

    public User registerUser(String email, String name) throws DaoException, UserAlreadyRegisteredException {

        User user = userDAO.getByEmail(email);

        if (user != null && user.isRegistered()) {
            throw new UserAlreadyRegisteredException();
        }

        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setRegistered(true);
            userDAO.create(user);
        } else {
            user.setName(name);
            user.setRegistered(true);
            userDAO.update(user);
        }

        return user;
    }

    public User updateUser(String name, String email, boolean registered, boolean notifications) throws DaoException, UserDoesNotExistException {

        User user = userDAO.getByEmail(email);

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
        List<User> userList = userDAO.getAll();
        for (User user : userList){
            System.out.println(user);
        }
        return userList;
    }
}