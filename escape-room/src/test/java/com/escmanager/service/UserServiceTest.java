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
    void test_addUser_UserDoesNotExists() {

        String email = "jose@gmail.com";
        when(userDAO.getByEmail(email)).thenReturn(null);

        User createUser = new User();
        createUser.setEmail(email);
        createUser.setId(1);
        when(userDAO.create(any(User.class))).thenReturn(createUser);

        User result = null;
        try {
            result = userService.addUser(email);
        } catch (UserAlreadyExistException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(result);
        assertEquals(result.getEmail(), email);
        assertEquals(result.getId(), 1);
        verify(userDAO).getByEmail(email);
        verify(userDAO).create(any(User.class));
    }

    @Test
    void test_addUser_UserAlreadyExists() {

        String email = "jose@gmail.com";
        User existingUser = new User();
        existingUser.setEmail(email);
        when(userDAO.getByEmail(email)).thenReturn(existingUser);

        User result = null;
        try {
            result = userService.addUser(email);
        } catch (UserAlreadyExistException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(result);
        assertEquals(result.getEmail(), email);
        verify(userDAO).getByEmail(email);
        verify(userDAO, never()).create(any(User.class));
    }

    @Test
    void test_registerUser_UserDoesNotExists() {
        String email = "jose@gmail.com";
        String name = "Jose Manuel";
        when(userDAO.getByEmail(email)).thenReturn(null);

        User createUser = new User();
        createUser.setEmail(email);
        createUser.setName(name);
        createUser.setRegistered(true);
        when(userDAO.create(any(User.class))).thenReturn(createUser);

        User result = null;
        try {
            result = userService.registerUser(email, name);
        } catch (UserAlreadyRegisteredException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(result);
        assertEquals(result.getEmail(), email);
        assertEquals(result.getName(), name);
        assertTrue(result.isRegistered());
        verify(userDAO).getByEmail(email);
        verify(userDAO).create(any(User.class));
    }

    @Test
    void test_registerUser_UserAlreadyExists() {
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

        User result = null;
        try {
            result = userService.registerUser(email, name);
        } catch (UserAlreadyRegisteredException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(result);
        assertEquals(result.getEmail(), email);
        assertEquals(result.getName(), name);
        assertTrue(result.isRegistered());
        verify(userDAO).getByEmail(email);
        verify(userDAO).update(any(User.class));
    }
}