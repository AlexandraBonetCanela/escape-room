package com.escmanager.model;

import com.escmanager.enums.Status;

import java.math.BigDecimal;

public class EscapeRoom {

    private int id;
    private String name;
    private BigDecimal price;
    private Status status;

    public EscapeRoom(int id, String name, BigDecimal price, Status status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public EscapeRoom(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EscapeRoom - id: " + id + ", name: '" + name + ", price: " + price + ", status: " + status;
    }
}
