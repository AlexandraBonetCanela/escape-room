package com.escmanager.model;

import java.math.BigDecimal;
import java.util.Date;

public class Ticket {
    private int id;
    private int user_id;
    private BigDecimal unit_price;
    private int quantity;
    private int escape_room_id;
    private BigDecimal total_price;

    public Ticket(int id, int user_id, BigDecimal unit_price, int quantity, int escape_room_id, BigDecimal total_price) {
        this.id = id;
        this.user_id = user_id;
        this.unit_price = unit_price;
        this.quantity = quantity;
        this.escape_room_id = escape_room_id;
        this.total_price = total_price;
    }

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getEscape_room_id() {
        return escape_room_id;
    }

    public void setEscape_room_id(int escape_room_id) {
        this.escape_room_id = escape_room_id;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "Ticket -" +
                " id: " + id +
                ", user_id: " + user_id +
                ", escape_room_id: " + escape_room_id +
                ", unit_price: " + unit_price +
                ", quantity: " + quantity +
                ", total_price: " + total_price;
    }
}