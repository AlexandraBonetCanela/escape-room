package com.escmanager.model;

import com.escmanager.enums.ElementType;
import com.escmanager.enums.Status;

import java.math.BigDecimal;
import java.util.Date;

public class Hint extends Element{
    private String theme;

    public Hint(int id, int roomId, String name, ElementType type, BigDecimal price, Status status, Date dateCreated, Date lastUpdated, String theme) {
        super(id, roomId, name, type, price, status, dateCreated, lastUpdated);
        this.theme = theme;
    }

    public Hint(int roomId, String name, ElementType type, BigDecimal price, Status status, String theme) {
        super(roomId, name, type, price, status);
        this.theme = theme;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
