package com.escmanager.model;

public class Room {

    private int id;
    private String name;
    private String theme;
    private String difficulty;
    private int element_quantity;
    private int escape_room_id;
    private String status;

    public Room(int id, String name, String theme, String difficulty, int element_quantity, int escape_room_id, String status) {
        this.id = id;
        this.name = name;
        this.theme = theme;
        this.difficulty = difficulty;
        this.element_quantity = element_quantity;
        this.escape_room_id = escape_room_id;
        this.status = status;
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getElement_quantity() {
        return element_quantity;
    }

    public void setElement_quantity(int element_quantity) {
        this.element_quantity = element_quantity;
    }

    public int getEscape_room_id() {
        return escape_room_id;
    }

    public void setEscape_room_id(int escape_room_id) {
        this.escape_room_id = escape_room_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room - id: " + id + ", escape_room_id: " + escape_room_id + ", name: " + name + ", theme: " + theme + ", difficulty: " + difficulty
                + ", status=" + status+ ", element_quantity=" + element_quantity;
    }
}