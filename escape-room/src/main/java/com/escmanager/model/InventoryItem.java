package com.escmanager.model;

import java.math.BigDecimal;

public class InventoryItem {
    private String type;
    private String reference;
    private String name;
    private BigDecimal price;
    private String details;

    public InventoryItem(String type, String reference, String name, BigDecimal price, String details) {
        this.type = type;
        this.reference = reference;
        this.name = name;
        this.price = price;
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return String.format(
                "%-26s%-26s%-26s%-26s%-26s",
                type != null ? type : "N/A",
                reference != null ? reference : "N/A",
                name != null ? name : "N/A",
                price != null ? price : "N/A",
                details != null ? details : "N/A"
        );
    }
}

