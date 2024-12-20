package com.escmanager.model;

import com.escmanager.service.Observer;

import java.util.Date;

public class User implements Observer {
    private int id;
    private String name;
    private String email;
    private boolean isRegistered;
    private Date dateCreated;
    private Date lastUpdated;
    private boolean notifications;

    public User(int id, String name, String email, boolean isRegistered, boolean notifications) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isRegistered = isRegistered;
        this.notifications = notifications;
    }

    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
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

    public boolean isNotifications() {
        return notifications;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isRegistered=" + isRegistered +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                ", notifications=" + notifications +
                '}';
    }

    @Override
    public void update(Newsletter newsletter) {
        System.out.println("User " + this.name + " received the newsletter! Content: " + newsletter.getName());
    }
}
