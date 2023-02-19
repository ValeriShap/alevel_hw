package com.shapran.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class CrudJdbc<T> implements Crud<T> {
    protected Connection connection;

    public void getConnection(Connection connection) {
        this.connection = connection;
    }

    public void close(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }
}
