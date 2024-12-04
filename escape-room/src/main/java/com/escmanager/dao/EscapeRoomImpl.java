package com.escmanager.dao;

import com.escmanager.model.EscapeRoom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EscapeRoomImpl implements EscapeRoomDAO{

    Properties props = new Properties();
    String url = props.getProperty("db.url");
    String username = props.getProperty("db.username");
    String password = props.getProperty("db.password");

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public void save(EscapeRoom escapeRoom) {
        String query = "INSERT INTO escaperoom (name, status) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, escapeRoom.getName());
            statement.setString(2, escapeRoom.getStatus());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("EscapeRoomImpl: There has been a error with the method save");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public EscapeRoom getById(int id) {
        String query = "SELECT * FROM escaperoom WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new EscapeRoom(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("status")
                );
            }

        } catch (SQLException e) {
            System.out.println("EscapeRoomImpl: There has been a error with the method getById");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<EscapeRoom> getAll() {
        List<EscapeRoom> users = new ArrayList<>();
        String query = "SELECT * FROM escaperoom";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                users.add(new EscapeRoom(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("status")
                ));
            }

        } catch (SQLException e) {
            System.out.println("EscapeRoomImpl: There has been a error with the method getAll");
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public void update(EscapeRoom escapeRoom) {
        String query = "UPDATE escaperoom SET name = ?, email = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, escapeRoom.getName());
            statement.setString(2, escapeRoom.getStatus());
            statement.setInt(3, escapeRoom.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("EscapeRoomImpl: There has been a error with the method update");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM escaperoom WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("EscapeRoomImpl: There has been a error with the method delete");
            System.out.println(e.getMessage());
        }
    }
}