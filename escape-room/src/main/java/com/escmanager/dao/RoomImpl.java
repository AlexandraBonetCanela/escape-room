package com.escmanager.dao;

import com.escmanager.model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomImpl implements RoomDAO {

    ConnectionDB dao = ConnectionDB.getInstance();

    @Override
    public void create(Room room) {
        String query = "INSERT INTO room (name, theme, difficulty_level, element_quantity, escape_room_id, status) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, room.getName());
            statement.setString(2, room.getTheme());
            statement.setString(3, room.getDifficulty());
            statement.setInt(4, room.getElement_quantity());
            statement.setInt(5, room.getEscape_room_id());
            statement.setString(6, room.getStatus());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("EscapeRoomImpl - create: " + e.getMessage());
        }
    }

    @Override
    public void update(Room room) {
        String query = "UPDATE room SET name = ?, theme = ?, difficulty_level = ?, element_quantity = ?," +
                        " escape_room_id = ?, status = ? WHERE id = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, room.getName());
            statement.setString(2, room.getTheme());
            statement.setString(3, room.getDifficulty());
            statement.setInt(4, room.getElement_quantity());
            statement.setInt(5, room.getEscape_room_id());
            statement.setString(6, room.getStatus());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("RoomImpl - update: " + e.getMessage());
        }
    }

    @Override
    public Room getById(int id) {
        String query = "SELECT * FROM room WHERE id = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Room(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("theme"),
                    resultSet.getString("difficulty_level"),
                    resultSet.getInt("element_quantity"),
                    resultSet.getInt("escape_room_id"),
                    resultSet.getString("status")
                );
            }

        } catch (SQLException e) {
            System.out.println("RoomImpl - getById: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Room> getAll() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM room";
        try (Connection connection = dao.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                rooms.add(new Room(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("theme"),
                        resultSet.getString("difficulty_level"),
                        resultSet.getInt("element_quantity"),
                        resultSet.getInt("escape_room_id"),
                        resultSet.getString("status")
                ));
            }

        } catch (SQLException e) {
            System.out.println("RoomImpl - getAll: " + e.getMessage());
        }
        return rooms;
    }

}