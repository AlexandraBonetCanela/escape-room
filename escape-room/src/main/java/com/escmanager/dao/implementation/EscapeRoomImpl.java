package com.escmanager.dao.implementation;

import com.escmanager.dao.ConnectionDB;
import com.escmanager.dao.EscapeRoomDAO;
import com.escmanager.enums.Status;
import com.escmanager.exceptions.DaoException;
import com.escmanager.model.EscapeRoom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscapeRoomImpl implements EscapeRoomDAO {

    ConnectionDB dao = ConnectionDB.getInstance();

    @Override
    public EscapeRoom create(EscapeRoom escapeRoom) {
        String query = "INSERT INTO escaperoom (name, price, status) VALUES (?, ?, ?)";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, escapeRoom.getName());
            statement.setBigDecimal(2, escapeRoom.getPrice());
            statement.setString(3, String.valueOf(escapeRoom.getStatus()));
            statement.executeUpdate();
            
            return findByName(escapeRoom.getName());
        } catch (SQLException e) {
            throw new DaoException("Failed to create Escaperoom in database", e);
        }
    }

    @Override
    public EscapeRoom update(EscapeRoom escapeRoom) {
        String query = "UPDATE escaperoom SET name = ?, price = ?, status = ? WHERE id = ?";

        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, escapeRoom.getName());
            statement.setBigDecimal(2, escapeRoom.getPrice());
            statement.setString(3, String.valueOf(escapeRoom.getStatus()));
            statement.setInt(4, escapeRoom.getId());
            statement.executeUpdate();

            return findByName(escapeRoom.getName());
        } catch (SQLException e) {
            throw new DaoException("Failed at updating Escaperoom in database", e);
        }
    }

    @Override
    public EscapeRoom  getByName(String name) {
        String query = "SELECT * FROM escaperoom WHERE name = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new EscapeRoom(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getBigDecimal("price"),
                    Status.valueOf(resultSet.getString("status"))
                );
            }

        } catch (SQLException e) {
            System.out.println("EscapeRoomImpl - getById: " + e.getMessage());
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
                    resultSet.getBigDecimal("price"),
                    Status.valueOf(resultSet.getString("status"))
                );
            }

        } catch (SQLException e) {
            throw new DaoException("Failed at retrieving Escaperoom in database", e);
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
                        resultSet.getBigDecimal("price"),
                        Status.valueOf(resultSet.getString("status"))
                ));
            }

        } catch (SQLException e) {
            throw new DaoException("Failed at retrieving Escaperooms in database", e);
        }
        return escapeRooms;
    }

}