package com.shapran.repository;

import com.shapran.model.Engine;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EngineJDBCRepository extends CrudJdbc<Engine> {
    private static EngineJDBCRepository instance;

    public static EngineJDBCRepository getInstance(){
        if (instance == null){
            instance = new EngineJDBCRepository();
        }
        return instance;
    }

    private EngineJDBCRepository(){
        createTablesIfNotExists();
    }

    @SneakyThrows
    private void createTablesIfNotExists(){
        Connection connection = JdbcManager.getConnection();
        Statement statement = connection.createStatement();
        String createEngineTable = "CREATE TABLE IF NOT EXISTS engine(engine_id VARCHAR(40) PRIMARY KEY," +
                " type VARCHAR(35) NOT NUL, power INT NOT NULL";
        statement.execute(createEngineTable);
        statement.close();
        connection.close();
    }
    @SneakyThrows
    @Override
    public void save(Engine engine) {
        connection.setAutoCommit(false);
        String insertEngine = "INSERT INTO engine(type, power, engine_id) VALUES(?, ?, ?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(insertEngine);
        preparedStatement.setString(1, engine.getType());
        preparedStatement.setInt(2, engine.getPower());
        preparedStatement.setString(3, engine.getEngine_id());
        preparedStatement.executeUpdate();
        close(preparedStatement);
    }

    @SneakyThrows
    @Override
    public List<Engine> getAll() {
        List<Engine> engines = new ArrayList<>();
        String getAllEngine = "SELECT * FROM engine";
        PreparedStatement preparedStatement = connection.prepareStatement(getAllEngine);
        ResultSet resultSet = preparedStatement.executeQuery(getAllEngine);
        while (resultSet.next()){
            engines.add(new Engine(resultSet.getString("type"), resultSet.getInt("power"),
                    resultSet.getString("engine_id")));
            close(preparedStatement);
        }
        return engines;
    }

    @SneakyThrows
    @Override
    public Optional<Engine> getById(String id) {
        String getEngineById = "SELECT * FROM engine WHERE engine_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(getEngineById);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Optional<Engine> engine = Optional.empty();
        if (resultSet.next()){
           engine = Optional.of(createEngine(resultSet));
        }
        close(preparedStatement);
        return engine;
    }

    @SneakyThrows
    @Override
    public void delete(String id) {
        String deleteEngine = "DELETE FROM engine WHERE engine_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteEngine);
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
        close(preparedStatement);
    }

    private Engine createEngine(ResultSet resultSet) throws SQLException {
        Engine engine = new Engine();
        engine.setType(resultSet.getString("type"));
        engine.setPower(resultSet.getInt("power"));
        engine.setEngine_id(resultSet.getString("engine_id"));
        return engine;
    }
}
