package com.escmanager.dao.implementation;

import com.escmanager.dao.ConnectionDB;
import com.escmanager.dao.RoomDAO;
import com.escmanager.enums.DifficultyLevel;
import com.escmanager.enums.Status;
import com.escmanager.exceptions.DaoException;
import com.escmanager.model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomImpl implements RoomDAO {

    ConnectionDB dao = ConnectionDB.getInstance();

    @Override
    public Room create(Room room) throws DaoException, IllegalArgumentException {
        String query = "INSERT INTO room (name, theme, difficulty_level, element_quantity, escape_room_id, status) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            if (room == null) {
                throw new IllegalArgumentException("Room object cannot be null");
            }

            statement.setString(1, room.getName());
            statement.setString(2, room.getTheme());
            statement.setString(3, String.valueOf(room.getDifficulty()));
            statement.setInt(4, room.getElementQuantity());
            statement.setInt(5, room.getEscaperoomId());
            statement.setString(6, String.valueOf(room.getStatus()));

            statement.executeUpdate();

            return getByNameAndEscapeRoomId(room.getName(), room.getEscaperoomId());
        } catch (SQLException e) {
            throw new DaoException("Failed to create room in database", e);
        }
    }

    @Override
    public Room update(Room room) throws DaoException {
        String query = "UPDATE room SET name = ?, theme = ?, difficulty_level = ?, element_quantity = ?," +
                        " escape_room_id = ?, status = ? WHERE id = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, room.getName());
            statement.setString(2, room.getTheme());
            statement.setString(3, String.valueOf(room.getDifficulty()));
            statement.setInt(4, room.getElementQuantity());
            statement.setInt(5, room.getEscaperoomId());
            statement.setString(6, String.valueOf(room.getStatus()));
            statement.setInt(7, room.getId());

            statement.executeUpdate();

            return getByNameAndEscapeRoomId(room.getName(), room.getEscaperoomId());
        } catch (SQLException e) {
            throw new DaoException("Failed at updating room in database", e);
        }
    }

    @Override
    public Room getByNameAndEscapeRoomId(String name, int escaperoomId) {
        String query = "SELECT * FROM room WHERE name = ? AND escape_room_id = ?";
        try (Connection connection = dao.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, name);
            statement.setInt(2, escaperoomId);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return new Room(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("theme"),
                    DifficultyLevel.valueOf(resultSet.getString("difficulty_level")),
                    resultSet.getInt("element_quantity"),
                    resultSet.getInt("escape_room_id"),
                    Status.valueOf(resultSet.getString("status"))
                );
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find room with name " + name, e);
        }
        return null;
    }

    @Override
    public List<Room> getAllByEscapeRoomId(int escaperoomId) {
        String query = "SELECT * FROM room WHERE escape_room_id = ?";
        List<Room> result = new ArrayList<>();
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, escaperoomId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Room r = new Room(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("theme"),
                        DifficultyLevel.valueOf(resultSet.getString("difficulty_level")),
                        resultSet.getInt("element_quantity"),
                        resultSet.getInt("escape_room_id"),
                        Status.valueOf(resultSet.getString("status"))
                );
                result.add(r);
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find room with escaperoom id " + escaperoomId, e);
        }
        return result;
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
                    DifficultyLevel.valueOf(resultSet.getString("difficulty_level")),
                    resultSet.getInt("element_quantity"),
                    resultSet.getInt("escape_room_id"),
                    Status.valueOf(resultSet.getString("status"))
                );
            }
        } catch (SQLException e) {
            throw new DaoException("Failed at retrieving room in database", e);
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
                        DifficultyLevel.valueOf(resultSet.getString("difficulty_level")),
                        resultSet.getInt("element_quantity"),
                        resultSet.getInt("escape_room_id"),
                        Status.valueOf(resultSet.getString("status"))
                ));
            }

        } catch (SQLException e) {
            throw new DaoException("Failed at retrieving rooms in database", e);
        }
        return rooms;
    }
}