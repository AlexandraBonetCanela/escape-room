package com.escmanager.model;

import com.escmanager.enums.DifficultyLevel;
import com.escmanager.enums.Status;

public class Room {

    private int id;
    private String name;
    private String theme;
    private DifficultyLevel difficulty;
    private Integer elementQuantity;
    private int escaperoomId;
    private Status status;

    public Room(int id, String name, String theme, DifficultyLevel difficulty, Integer elementQuantity, int escaperoomId, Status status) {
        this.id = id;
        this.name = name;
        this.theme = theme;
        this.difficulty = difficulty;
        this.elementQuantity = elementQuantity;
        this.escaperoomId = escaperoomId;
        this.status = status;
    }

    public Room(){

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

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getElementQuantity() {
        return elementQuantity;
    }

    public void setElementQuantity(Integer elementQuantity) {
        this.elementQuantity = elementQuantity;
    }

    public int getEscaperoomId() {
        return escaperoomId;
    }

    public void setEscaperoomId(int escaperoomId) {
        this.escaperoomId = escaperoomId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room - id: " + id + ", escape_room_id: " + escaperoomId + ", name: " + name + ", theme: " + theme + ", difficulty: " + difficulty
                + ", status=" + status+ ", element_quantity=" + elementQuantity;
    }
}