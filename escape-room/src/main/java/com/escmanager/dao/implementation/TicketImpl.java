package com.escmanager.dao.implementation;

import com.escmanager.dao.ConnectionDB;
import com.escmanager.dao.TicketDAO;
import com.escmanager.exceptions.DaoException;
import com.escmanager.model.Ticket;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketImpl implements TicketDAO {

    ConnectionDB dao = ConnectionDB.getInstance();

    @Override
    public Ticket create(Ticket ticket) throws DaoException, IllegalArgumentException {
        String query = "INSERT INTO ticket (user_id, unit_price, quantity, escape_room_id, total_price) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            if (ticket == null) {
                throw new IllegalArgumentException("Ticket object cannot be null");
            }

            statement.setInt(1, ticket.getUser_id());
            statement.setBigDecimal(2, ticket.getUnit_price());
            statement.setInt(3, ticket.getQuantity());
            statement.setInt(4, ticket.getEscape_room_id());
            statement.setBigDecimal(5, ticket.getTotal_price());
            statement.executeUpdate();

            return ticket;
        } catch (SQLException e) {
            throw new DaoException("Failed to create ticket in database", e);
        }
    }

    @Override
    public Ticket update(Ticket ticket) throws DaoException, IllegalArgumentException {
        String query = "UPDATE ticket SET user_id = ?, unit_price = ?, quantity = ?, escape_room_id = ?, total_price = ? WHERE id = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            if (ticket == null) {
                throw new IllegalArgumentException("Ticket object cannot be null");
            }

            statement.setInt(1, ticket.getUser_id());
            statement.setBigDecimal(2, ticket.getUnit_price());
            statement.setInt(3, ticket.getQuantity());
            statement.setInt(4, ticket.getEscape_room_id());
            statement.setBigDecimal(5, ticket.getTotal_price());
            statement.setInt(6, ticket.getId());
            statement.executeUpdate();

            return ticket;
        } catch (SQLException e) {
            throw new DaoException("Failed to update ticket in database", e);
        }
    }

    @Override
    public Ticket findByName(String name) {
        String query = "SELECT * FROM ticket WHERE name = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return new Ticket(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getBigDecimal("unit_price"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("escape_room_id"),
                        resultSet.getBigDecimal("total_price")
                );
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find ticket with name " + name, e);
        }
        return null;
    }

    @Override
    public BigDecimal getTotalEarned() throws DaoException {
        BigDecimal totalSum = BigDecimal.ZERO;
        String query = "SELECT total_price FROM ticket";
        try (Connection connection = dao.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                totalSum = totalSum.add(resultSet.getBigDecimal("total_price"));
            }

        } catch (SQLException e) {
            throw new DaoException("Failed at retrieving total price sum from database", e);
        }
        return totalSum;
    }

    @Override
    public Ticket getById(int id) {
        String query = "SELECT * FROM ticket WHERE id = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return new Ticket(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getBigDecimal("unit_price"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("escape_room_id"),
                        resultSet.getBigDecimal("total_price")
                );
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find ticket with id " + id, e);
        }
        return null;
    }

    @Override
    public List<Ticket> getAll() throws DaoException {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM ticket";
        try (Connection connection = dao.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                tickets.add(new Ticket(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getBigDecimal("unit_price"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("escape_room_id"),
                        resultSet.getBigDecimal("total_price")
                ));
            }

        } catch (SQLException e) {
            throw new DaoException("Failed at retrieving tickets from database", e);
        }
        return tickets;
    }

}
