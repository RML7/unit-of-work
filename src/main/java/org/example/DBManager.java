package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    public static Connection getConnection() throws SQLException {
        Connection connection = connectionHolder.get();
        if (connection == null) {
            System.out.println("Creating new transaction");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydatabase", "postgres", "example");
            connectionHolder.set(connection);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        Connection connection = connectionHolder.get();
        if (connection != null) {
            connection.close();
            connectionHolder.remove();
        }
    }

    public static void inTransaction(Runnable unitOfWork) throws Exception {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            System.out.println("Starting transaction");
            unitOfWork.run();
            connection.commit();
            System.out.println("Committing transaction");
        } catch (Exception e) {
            System.out.println("Rollback transaction");
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
            closeConnection();
        }
    }
}