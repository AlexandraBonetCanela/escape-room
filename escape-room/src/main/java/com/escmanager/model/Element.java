package com.escmanager.model;

import com.escmanager.enums.ElementType;
import com.escmanager.enums.Status;

import java.math.BigDecimal;
import java.util.Date;

public abstract class Element {
    protected int id;
    protected Integer roomId;
    protected String name;
    protected ElementType type;
    protected BigDecimal price;
    protected Status status;
    protected Date dateCreated;
    protected Date lastUpdated;

    public Element(int id, Integer roomId, String name, ElementType type, BigDecimal price, Status status, Date dateCreated, Date lastUpdated) {
        this.id = id;
        this.roomId = roomId;
        this.name = name;
        this.type = type;
        this.price = price;
        this.status = status;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }

    public Element(Integer roomId, String name, ElementType type, BigDecimal price, Status status) {
        this.roomId = roomId;
        this.name = name;
        this.type = type;
        this.price = price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Element{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", status=" + status +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
