package com.escmanager.dao;

import com.escmanager.model.EscapeRoom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscapeRoomImpl implements EscapeRoomDAO {

    ConnectionDB dao = ConnectionDB.getInstance();

    @Override
    public void create(EscapeRoom escapeRoom) {
        String query = "INSERT INTO escaperoom (name, status) VALUES (?, ?)";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, escapeRoom.getName());
            statement.setString(2, escapeRoom.getStatus());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("EscapeRoomImpl - create: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(EscapeRoom escapeRoom) {
        String query = "UPDATE escaperoom SET name = ?, status = ? WHERE id = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, escapeRoom.getName());
            statement.setString(2, escapeRoom.getStatus());
            statement.setInt(3, escapeRoom.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("EscapeRoomImpl - update: " + e.getMessage());
        }
    }

    @Override
    public EscapeRoom getById(int id) {
        String query = "SELECT * FROM escaperoom WHERE id = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new EscapeRoom(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("status")
                );
            }

        } catch (SQLException e) {
            System.out.println("EscapeRoomImpl - getById: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<EscapeRoom> getAll() {
        List<EscapeRoom> users = new ArrayList<>();
        String query = "SELECT * FROM escaperoom";
        try (Connection connection = dao.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                users.add(new EscapeRoom(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("status")
                ));
            }

        } catch (SQLException e) {
            System.out.println("EscapeRoomImpl - getAll: " + e.getMessage());
        }
        return users;
    }

}