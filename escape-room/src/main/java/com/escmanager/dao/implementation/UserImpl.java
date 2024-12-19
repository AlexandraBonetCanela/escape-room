package com.escmanager.dao.implementation;

import com.escmanager.dao.ConnectionDB;
import com.escmanager.dao.UserDAO;
import com.escmanager.exceptions.DaoException;
import com.escmanager.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements UserDAO {

    ConnectionDB dao = ConnectionDB.getInstance();

    @Override
    public User create(User user) {
        String query = "INSERT INTO user (name, email, is_registered, notifications) VALUES (?, ?, ?, ?)";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setBoolean(3, user.isRegistered());
            statement.setBoolean(4, user.isNotifications());
            statement.executeUpdate();

            return user;
        } catch (SQLException e) {
            throw new DaoException("Failed at retrieving rooms in database", e);
        }
    }

    @Override
    public User update(User user) {
        String query = "UPDATE user SET name = ?, email = ?, is_registered = ?, notifications = ? WHERE id = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setBoolean(3, user.isRegistered());
            statement.setBoolean(4, user.isNotifications());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
            return user;

        } catch (SQLException e) {
            throw new DaoException("Failed at retrieving rooms in database", e);
        }
    }

    @Override
    public User getByEmail(String email) {
        String query = "SELECT id, name, email, is_registered, notifications FROM user WHERE email = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getBoolean("is_registered"),
                        resultSet.getBoolean("notifications")
                );
            }

        } catch (SQLException e) {
            throw new DaoException("Failed at retrieving rooms in database", e);
        }
        return null;
    }

    @Override
    public User getById(int id) {
        String query = "SELECT * FROM user WHERE id = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getBoolean("is_registered"),
                        resultSet.getBoolean("notifications")
                );
            }

        } catch (SQLException e) {
            throw new DaoException("Failed at retrieving rooms in database", e);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> user = new ArrayList<>();
        String query = "SELECT * FROM user";
        try (Connection connection = dao.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                user.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getBoolean("is_registered"),
                        resultSet.getBoolean("notifications")
                ));
            }

        } catch (SQLException e) {
            throw new DaoException("Failed at retrieving rooms in database", e);
        }
        return user;
    }

    public List<User> getAllSubscribers(){
        List<User> user = new ArrayList<>();
        String query = "SELECT * FROM user WHERE notifications = 1";
        try (Connection connection = dao.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                user.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getBoolean("is_registered"),
                        resultSet.getBoolean("notifications")
                ));
            }
        } catch (SQLException e) {
            throw new DaoException("Failed at retrieving rooms in database", e);
        }
        return user;
    }
}

