package com.escmanager.service;

import com.escmanager.dao.InventoryDAO;
import com.escmanager.dao.implementation.InventoryImpl;
import com.escmanager.model.InventoryItem;

import java.util.List;

public class InventoryService {

    private static InventoryService instance = new InventoryService();
    public static InventoryService getInstance() {
        return instance;
    }
    private InventoryService() {}

    InventoryDAO inventoryDAO = new InventoryImpl();

    public List<InventoryItem> getInventory() {
        return inventoryDAO.getInventory();
    }
}
