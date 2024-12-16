package com.escmanager.dao;

import com.escmanager.exceptions.DaoException;
import com.escmanager.model.Newsletter;

import java.util.List;

public interface NewsletterDAO extends DAO<Newsletter> {

    Newsletter create(String name);
    Newsletter update(int newsletterId, String name) throws DaoException;
    Newsletter getById(int id);
    Newsletter getByName(String name);
    List<Newsletter> getAll();

    void addUserToNewsletter(int userId, int newsletter);
}
