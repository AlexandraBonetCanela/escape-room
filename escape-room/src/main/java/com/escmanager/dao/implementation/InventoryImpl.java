package com.escmanager.dao.implementation;

import com.escmanager.dao.ConnectionDB;
import com.escmanager.dao.InventoryDAO;
import com.escmanager.exceptions.DaoException;
import com.escmanager.model.InventoryItem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InventoryImpl implements InventoryDAO {

    ConnectionDB dao = ConnectionDB.getInstance();

    public List<InventoryItem> getInventory() {
        List<InventoryItem> inventoryList = new ArrayList<>();
        String query = "SELECT * FROM inventory";
        try (Connection connection = dao.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                inventoryList.add(new InventoryItem(
                        resultSet.getString("type"),
                        resultSet.getString("reference"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getString("details")
                ));
            }

        } catch (SQLException e) {
            throw new DaoException("Failed at retrieving inventory in database", e);
        }
        return inventoryList;
    }
}
