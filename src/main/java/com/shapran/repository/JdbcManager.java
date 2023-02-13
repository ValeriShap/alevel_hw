package com.shapran.repository;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcManager {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres?currentSchema=public";
    private static final String USER = "postgres";
    private static final String PASS = "6494";
    private static Connection connection;

    private JdbcManager() {
    }

    @SneakyThrows
    public static Connection getConnection(){
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}


