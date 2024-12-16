package com.escmanager.model;

import com.escmanager.enums.ElementType;
import com.escmanager.enums.Status;

import java.math.BigDecimal;
import java.util.Date;

public class Prop extends Element{
    private String materialType;

    public Prop(int id, Integer roomId, String name, ElementType type, BigDecimal price, Status status, Date dateCreated, Date lastUpdated, String materialType) {
        super(id, roomId, name, type, price, status, dateCreated, lastUpdated);
        this.materialType = materialType;
    }

    public Prop(Integer roomId, String name, ElementType type, BigDecimal price, Status status, String materialType) {
        super(roomId, name, type, price, status);
        this.materialType = materialType;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    @Override
    public String toString() {
        return "Prop{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", status=" + status +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                " materialType='" + materialType + '\'' +
                '}';
    }
}
