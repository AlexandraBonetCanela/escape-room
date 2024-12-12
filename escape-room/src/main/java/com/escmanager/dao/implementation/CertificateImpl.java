package com.escmanager.dao.implementation;

import com.escmanager.dao.CertificateDAO;
import com.escmanager.dao.ConnectionDB;
import com.escmanager.model.Certificate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CertificateImpl implements CertificateDAO {

    ConnectionDB dao = ConnectionDB.getInstance();

    @Override
    public Certificate create(Certificate certificate) {
        String query = "INSERT INTO certificate (name, description, escape_room_id) VALUES (?, ?, ?)";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, certificate.getName());
            statement.setString(2, certificate.getDescription());
            statement.setInt(3, certificate.getEscape_room_id());
            statement.executeUpdate();

            return certificate;
        } catch (SQLException e) {
            System.out.println("CertificateImpl - create: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Certificate update(Certificate certificate) {
        String query = "UPDATE certificate SET name = ?, description = ?, escape_room_id = ? WHERE id = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, certificate.getName());
            statement.setString(2, certificate.getDescription());
            statement.setInt(3, certificate.getEscape_room_id());
            statement.setInt(4, certificate.getId());
            statement.executeUpdate();

            return certificate;
        } catch (SQLException e) {
            System.out.println("CertificateImpl - update: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Certificate getById(int id) {
        String query = "SELECT * FROM certificate WHERE id = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Certificate(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                        resultSet.getInt("escape_room_id")
                );
            }

        } catch (SQLException e) {
            System.out.println("CertificateImpl - getById: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Certificate> getAll() {
        List<Certificate> certificates = new ArrayList<>();
        String query = "SELECT * FROM certificate";
        try (Connection connection = dao.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                certificates.add(new Certificate(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("escape_room_id")
                ));
            }

        } catch (SQLException e) {
            System.out.println("CertificateImpl - getAll: " + e.getMessage());
        }
        return certificates;
    }
}