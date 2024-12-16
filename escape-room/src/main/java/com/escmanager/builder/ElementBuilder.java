package com.escmanager.builder;

import com.escmanager.enums.ElementType;
import com.escmanager.enums.Status;
import com.escmanager.model.Element;

import java.math.BigDecimal;
import java.util.Date;


public abstract class ElementBuilder {

    protected int id;
    protected Integer roomId;
    protected String name;
    protected BigDecimal price;
    protected ElementType type;
    protected Status status;
    protected Date dateCreated;
    protected Date lastUpdated;

    public ElementBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ElementBuilder setRoomId(Integer roomId) {
        this.roomId = roomId;
        return this;
    }
    public ElementBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public ElementBuilder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
    public ElementBuilder setType(ElementType type) {
        this.type = type;
        return this;
    }
    public ElementBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public ElementBuilder setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public ElementBuilder setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    public abstract Element build();
}
