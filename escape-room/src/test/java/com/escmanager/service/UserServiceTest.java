package com.escmanager.service;

import com.escmanager.dao.UserDAO;
import com.escmanager.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @Test
    void addUser_UserDoesNotExists() {

        String email = "jose@gmail.com";
        when(userDAO.findByEmail(email)).thenReturn(null);

        User createUser = new User();
        createUser.setEmail(email);
        createUser.setId(1);
        when(userDAO.create(any(User.class))).thenReturn(createUser);

        User result = userService.addUser(email);

        assertNotNull(result);
        assertEquals(result.getEmail(), email);
        assertEquals(result.getId(), 1);
        verify(userDAO).findByEmail(email);
        verify(userDAO).create(any(User.class));
    }
}