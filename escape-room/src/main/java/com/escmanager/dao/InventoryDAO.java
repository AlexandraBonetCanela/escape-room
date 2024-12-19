package com.escmanager.dao;

import com.escmanager.model.InventoryItem;

import java.util.List;

public interface InventoryDAO {
    List<InventoryItem> getInventory();
}
