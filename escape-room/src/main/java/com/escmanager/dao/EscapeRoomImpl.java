package com.escmanager.dao;

import com.escmanager.model.EscapeRoom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscapeRoomImpl implements EscapeRoomDAO {

    ConnectionDB dao = ConnectionDB.getInstance();

    @Override
    public EscapeRoom create(EscapeRoom escapeRoom) {
        String query = "INSERT INTO escaperoom (name, price,status) VALUES (?, ?, ?)";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, escapeRoom.getName());
            statement.setDouble(2, escapeRoom.getPrice());
            statement.setString(3, escapeRoom.getStatus());
            statement.executeUpdate();
            
            return escapeRoom;
        } catch (SQLException e) {
            System.out.println("EscapeRoomImpl - create: " + e.getMessage());
        }
        return null;
    }

    @Override
    public EscapeRoom update(EscapeRoom escapeRoom) {
        String query = "UPDATE escaperoom SET name = ?, SET price = ?, status = ? WHERE id = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, escapeRoom.getName());
            statement.setDouble(2, escapeRoom.getPrice());
            statement.setString(3, escapeRoom.getStatus());
            statement.setInt(4, escapeRoom.getId());
            statement.executeUpdate();

            return escapeRoom;
        } catch (SQLException e) {
            System.out.println("EscapeRoomImpl - update: " + e.getMessage());
        }
        return null;
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
        List<EscapeRoom> escapeRooms = new ArrayList<>();
        String query = "SELECT * FROM escaperoom";
        try (Connection connection = dao.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                escapeRooms.add(new EscapeRoom(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("status")
                ));
            }

        } catch (SQLException e) {
            System.out.println("EscapeRoomImpl - getAll: " + e.getMessage());
        }
        return escapeRooms;
    }

}