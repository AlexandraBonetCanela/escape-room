package com.escmanager.service;

import com.escmanager.dao.UserDAO;
import com.escmanager.exceptions.user.UserAlreadyExistException;
import com.escmanager.exceptions.user.UserAlreadyRegisteredException;
import com.escmanager.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @Test
    void test_addUser_UserDoesNotExists() throws UserAlreadyExistException {

        String email = "jose@gmail.com";
        when(userDAO.getByEmail(email)).thenReturn(null);

        User createUser = new User();
        createUser.setEmail(email);
        createUser.setId(1);
        when(userDAO.create(any(User.class))).thenReturn(createUser);

        User result = userService.addUser(email);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
        assertEquals(1, result.getId());
        verify(userDAO).getByEmail(email);
        verify(userDAO).create(any(User.class));
    }

    @Test
    void test_addUser_UserAlreadyExists() throws UserAlreadyExistException {

        String email = "jose@gmail.com";
        User existingUser = new User();
        existingUser.setEmail(email);
        when(userDAO.getByEmail(email)).thenReturn(existingUser);

        User result = userService.addUser(email);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(userDAO).getByEmail(email);
        verify(userDAO, never()).create(any(User.class));
    }

    @Test
    void test_registerUser_UserDoesNotExists() throws UserAlreadyRegisteredException {
        String email = "jose@gmail.com";
        String name = "Jose Manuel";
        when(userDAO.getByEmail(email)).thenReturn(null);

        User createUser = new User();
        createUser.setEmail(email);
        createUser.setName(name);
        createUser.setRegistered(true);
        when(userDAO.create(any(User.class))).thenReturn(createUser);

        User result = userService.registerUser(email, name);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
        assertEquals(name, result.getName());
        assertTrue(result.isRegistered());
        verify(userDAO).getByEmail(email);
        verify(userDAO).create(any(User.class));
    }

    @Test
    void test_registerUser_UserAlreadyExists() throws UserAlreadyRegisteredException {
        String email = "jose@gmail.com";
        String name = "Jose Manuel";

        User existingUser = new User();
        existingUser.setEmail(email);

        when(userDAO.getByEmail(email)).thenReturn(existingUser);

        User registeredUser = new User();
        registeredUser.setEmail(email);
        registeredUser.setName(name);
        registeredUser.setRegistered(true);
        when(userDAO.update(any(User.class))).thenReturn(registeredUser);

        User result = userService.registerUser(email, name);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
        assertEquals(name, result.getName());
        assertTrue(result.isRegistered());
        verify(userDAO).getByEmail(email);
        verify(userDAO).update(any(User.class));
    }
}