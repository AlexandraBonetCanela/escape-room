package com.escmanager.builder;

import com.escmanager.enums.ElementType;
import com.escmanager.enums.Status;
import com.escmanager.model.Element;

import java.math.BigDecimal;


public abstract class ElementBuilder {

    protected int roomId;
    protected String name;
    protected BigDecimal price;
    protected ElementType type;
    protected Status status;

    public ElementBuilder setRoomId(int roomId) {
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
    public abstract Element build();
}
