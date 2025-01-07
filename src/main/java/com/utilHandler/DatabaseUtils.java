package com.utilHandler;

import com.config.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseUtils {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseUtils.class);
    private static Connection connection;

    private DatabaseUtils() {}

    public static Connection getConnection() {
        if (connection == null || isClosed(connection)) {
            synchronized (DatabaseUtils.class) {
                try {
                    connection = DriverManager.getConnection(
                            ConfigManager.getProperty("db.url"),
                            ConfigManager.getProperty("db.username"),
                            ConfigManager.getProperty("db.password")
                    );
                    logger.info("Database connection established successfully.");
                } catch (SQLException e) {
                    logger.error("Failed to establish database connection: {}", e.getMessage());
                    throw new RuntimeException("Database connection error", e);
                }
            }
        }
        return connection;
    }

    public static List<String> executeQuery(String query, String columnLabel) {
        List<String> results = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                results.add(rs.getString(columnLabel));
            }
        } catch (SQLException e) {
            logger.error("Query execution failed for query: {} - Error: {}", query, e.getMessage());
        }
        return results;
    }

    public static List<String> getFilteredResults(String query, String columnLabel, String filterValue) {
        try {
            return executeQuery(query, columnLabel)
                    .stream()
                    .filter(value -> value.contains(filterValue))
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error processing filtered results for query: {} - Error: {}", query, e.getMessage());
            return new ArrayList<>(); // Returning empty list as fallback
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                logger.info("Database connection closed.");
            }
        } catch (SQLException e) {
            logger.error("Failed to close database connection: {}", e.getMessage());
        }
    }

    private static boolean isClosed(Connection conn) {
        try {
            return conn == null || conn.isClosed();
        } catch (SQLException e) {
            logger.error("Error checking connection status: {}", e.getMessage());
            return true;
        }
    }
}
