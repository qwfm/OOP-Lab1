package database;

import java.sql.*;

public class ConnectDB {
    private Connection connection;

    public ConnectDB() {
        initiateConnection();
    }

    public void initiateConnection() {
        String url = "jdbc:sqlite:music.db";

        try {
            // create a connection to the database
            connection = DriverManager.getConnection(url);

            System.out.println("Connection to local DB established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeDB() {
        try {
            if (connection != null) {
                System.out.print("Connection to local DB closed");
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
