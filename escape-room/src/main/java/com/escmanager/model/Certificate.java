package com.escmanager.model;

public class Certificate {

    private int id;
    private String name;
    private String description;
    private int escape_room_id;

    public Certificate(int id, String name, String description, int escape_room_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.escape_room_id = escape_room_id;
    }

    public Certificate() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEscape_room_id() {
        return escape_room_id;
    }

    public void setEscape_room_id(int escape_room_id) {
        this.escape_room_id = escape_room_id;
    }

    @Override
    public String toString() {
        return "Certificate - id: " + id + ", name: " + name + ", description: " + description + ", escape_room_id: " + escape_room_id;
    }
}