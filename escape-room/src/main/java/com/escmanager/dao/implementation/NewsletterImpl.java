package com.escmanager.dao.implementation;

import com.escmanager.dao.ConnectionDB;
import com.escmanager.dao.NewsletterDAO;
import com.escmanager.exceptions.DaoException;
import com.escmanager.model.Newsletter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsletterImpl implements NewsletterDAO {

    ConnectionDB dao = ConnectionDB.getInstance();

    @Override
    public Newsletter create(String name) throws DaoException {
        String query = "INSERT INTO newsletter (name) VALUES (?)";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            statement.executeUpdate();

            return getByName(name);
        } catch (SQLException e) {
            throw new DaoException("Could not create Newsletter in database", e);
        }
    }

    @Override
    public Newsletter update(int newsletterId, String name) throws DaoException {
        String query = "UPDATE newsletter SET name = ? WHERE id = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            statement.setInt(2, newsletterId);
            statement.executeUpdate();

            return getByName(name);
        } catch (SQLException e) {
            throw new DaoException("Could not update Newsletter in database", e);
        }
    }

    @Override
    public Newsletter getById(int id) throws DaoException {
        String query = "SELECT * FROM newsletter WHERE id = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Newsletter(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getTimestamp("date_created")
                );
            }
        } catch (SQLException e) {
            throw new DaoException("Could not get Newsletter from database", e);
        }
        return null;
    }
    public Newsletter getByName(String name) throws DaoException {
        String query = "SELECT * FROM newsletter WHERE name = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Newsletter(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getTimestamp("date_created")
                );
            }
        } catch (SQLException e) {
            throw new DaoException("Could not get Newsletter from database", e);
        }
        return null;
    }

    @Override
    public List<Newsletter> getAll() throws DaoException {
        List<Newsletter> newsletters = new ArrayList<>();
        String query = "SELECT * FROM newsletter";
        try (Connection connection = dao.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                newsletters.add(new Newsletter(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getTimestamp("date_created")
                ));
            }
        } catch (SQLException e) {
            throw new DaoException("Could not get Newsletters from database", e);
        }
        return newsletters;
    }

    @Override
    public void addUserToNewsletter(int userId, int newsletterId) {
        String query = "INSERT INTO newsletter_to_user (user_id, newsletter_id) VALUES (?, ?)";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            statement.setInt(2, newsletterId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Could not create Newsletter in database", e);
        }
    }
}
