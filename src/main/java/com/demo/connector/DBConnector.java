package com.demo.connector;

import com.demo.dto.ConnectionConfigDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Pavel Tkachev
 * @version 1.0
 */
public class DBConnector {
    private Connection connection = null;
    private Long n = null;

    public DBConnector(ConnectionConfigDTO connectionConfig) {
        try {
            if (connection == null || n == null) {
                connection = DriverManager.getConnection(connectionConfig.getUrl(), connectionConfig.getUsername(), connectionConfig.getPassword());
                n = connectionConfig.getN();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Long getN() {
        return n;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
