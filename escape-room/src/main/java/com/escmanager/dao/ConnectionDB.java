package com.escmanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnectionDB {

    private static ConnectionDB instance;

    private String dbUrl;
    private String dbUsername;
    private String dbPassword;

    private Connection connection;

    public ConnectionDB() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("escape-room/src/main/resources/database.properties")) {
            properties.load(fis);
            this.dbUrl = properties.getProperty("db.url");
            this.dbUsername = properties.getProperty("db.username");
            this.dbPassword = properties.getProperty("db.password");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static synchronized ConnectionDB getInstance() {
        if (instance == null) {
            instance = new ConnectionDB();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(this.dbUrl, this.dbUsername, this.dbPassword);
                System.out.println("Connection established.");
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}