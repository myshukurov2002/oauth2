package com.company;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Component
public class DatabaseCreator implements ApplicationRunner {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/";
    private static final String DB_NAME = "kun_uz_v3";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "root";

    @Override
    public void run(ApplicationArguments args) {
        try (Connection conn = DriverManager.getConnection(DB_URL + "postgres", DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            String createDbQuery = "CREATE DATABASE " + DB_NAME;
            stmt.executeUpdate(createDbQuery);
            System.out.println("Database '" + DB_NAME + "' created successfully!");

        } catch (Exception e) {
            System.out.println("Database creation skipped or error occurred: " + e.getMessage());
        }
    }
}
