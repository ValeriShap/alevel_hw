package com.shapran.repository;

import com.shapran.model.*;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarJdbcRepository extends CrudJdbc<Car> {
    private static CarJdbcRepository instance;
    private Connection connection = JdbcManager.getConnection();

    public CarJdbcRepository(){
        createTablesIfNeeded();
    }
    public static CarJdbcRepository getInstance(){
        if (instance == null){
            instance = new CarJdbcRepository();
        }
        return instance;
    }

    @SneakyThrows
    private void createTablesIfNeeded(){
        Connection connection = JdbcManager.getConnection();
        Statement statement = connection.createStatement();
        String createCarTable = "CREATE TABLE IF NOT EXISTS \"cars\"(car_id VARCHAR(40) PRIMARY KEY," +
                "manufacturer VARCHAR(25) NOT NULL, color VARCHAR(25) NOT NULL, count INT NOT NULL," +
                "price INT NOT NULL, type VARCHAR(25) NOT NULL, engine_id VARCHAR(40) NOT NULL," +
                "FOREIGN KEY (engine_id)  REFERENCES engine (car_id) ON DELETE CASCADE, " +
                "FOREIGN KEY (order_id) REFERENCES order (car_id) ON DELETE CASCADE)";
        String createTruckTable = "CREATE TABLE IF NOT EXISTS \"truck\"(car_id VARCHAR(40) NOT NULL PRIMARY KEY, " +
                "loadCapacity INT NOT NULL, FOREIGN KEY (car_id) REFERENCES cars (car_id) ON DELETE CASCADE)";
        String createPcTable = "CREATE TABLE IF NOT EXISTS \"passengerCars\"(car_id VARCHAR(40) NOT NULL PRIMARY KEY, " +
                "passengerCount INT NOT NULL, FOREIGN KEY (car_id) REFERENCES cars (car_id) ON DELETE CASCADE) ";
        statement.execute(createCarTable);
        statement.execute(createTruckTable);
        statement.execute(createPcTable);
        statement.close();
        connection.close();
    }
    @SneakyThrows
    @Override
    public void save(Car car) {
        connection = JdbcManager.getConnection();
        Statement statement = connection.createStatement();
        String insertCars = "INSERT INTO cars(car_id, manufacturer, color, count, price, type, engine_id) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertCars);
        preparedStatement.setString(1, car.getId());
        preparedStatement.setString(2, car.getManufacturer());
        preparedStatement.setString(3, car.getColor().toString());
        preparedStatement.setInt(4, car.getCount());
        preparedStatement.setInt(5, car.getPrice());
        preparedStatement.setString(6, car.getType().toString());
        preparedStatement.setString(7, car.getEngine().getEngine_id());
        preparedStatement.executeUpdate();
        if (car.getType() == Type.CAR){
            String insertTypeCar = "INSERT INTO passengerCar(car_id, passengerCount) VALUES(?,?)";
            PreparedStatement preparedStatementPassCar = connection.prepareStatement(insertCars);
            preparedStatementPassCar.setString(1,car.getId());
            preparedStatementPassCar.setInt(2, ((PassengerCar)car).getPassengerCount());
            preparedStatementPassCar.executeUpdate();
        }else if (car.getType() == Type.TRUCK){
            String insertTypeTruck = "INSERT INTO truck(car_id, loadCapacity) VALUES(?,?)";
            PreparedStatement preparedStatementTruck = connection.prepareStatement(insertCars);
            preparedStatementTruck.setString(1, car.getId());
            preparedStatementTruck.setInt(2, ((Truck)car).getLoadCapacity());
            preparedStatementTruck.executeUpdate();
        }
        statement.execute(insertCars);
        statement.close();
        connection.close();
    }

    @SneakyThrows
    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        String getAllCars = "SELECT * FROM cars LEFT JOIN passengerCars ON cars.car_id = passengerCars.car_id" +
                "LEFT JOIN truck ON cars.car_id = truck.car_id" +
                "LEFT JOIN engine ON cars.engine_id = engine.engine_id";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getAllCars);
        while (resultSet.next()){
            if (resultSet.getString("passengerCount") != null){
                cars.add(createPassengerCar(resultSet));
            }else if (resultSet.getString("loadCapacity") != null){
                cars.add(createTruck(resultSet));
            }
        }
        return cars;
    }

    @SneakyThrows
    @Override
    public Optional<Car> getById(String id) {
        String getCarById = "SELECT * FROM cars LEFT JOIN passengerCars ON cars.car_id = passengerCars.car_id" +
                "LEFT JOIN truck ON cars.car_id = truck.car_id" +
                "LEFT JOIN engine ON cars.engine_id = engine.engine_id " +
                "WHERE cars.car_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(getCarById);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            if (resultSet.getString("passengerCount") != null){
                return Optional.of(createPassengerCar(resultSet));
            } else if (resultSet.getString("loadCapacity") != null) {
                return Optional.of(createTruck(resultSet));
            }
        }
        close(preparedStatement);
        return Optional.empty();
    }

    @SneakyThrows
    @Override
    public void delete(String id) {
        connection.setAutoCommit(false);
        String deleteCar = "DELETE FROM cars WHERE cars.car_id = ?";
        String deleteEngine = "DELETE FROM engine WHERE engine.engine_id = (SELECT engine FROM cars)";
        PreparedStatement deleteCars = connection.prepareStatement(deleteCar);
        PreparedStatement deleteEngines = connection.prepareStatement(deleteEngine);
        deleteCars.setString(1, id);
        deleteCars.execute();
        deleteEngines.setString(2, id);
        deleteEngines.execute();
        connection.commit();
    }

    @SneakyThrows
    private Car createPassengerCar(ResultSet resultSet){
            PassengerCar passengerCar = new PassengerCar();
            passengerCar.setId(resultSet.getString("car_id"));
            passengerCar.setPassengerCount(resultSet.getInt("passengerCount"));
            passengerCar.setManufacturer(resultSet.getString("manufacturer"));
            passengerCar.setColor(Color.valueOf(resultSet.getString("color")));
            passengerCar.setCount(resultSet.getInt("count"));
            passengerCar.setPrice(resultSet.getInt("price"));
            passengerCar.setType(Type.CAR);
            passengerCar.setEngine(new Engine(resultSet.getString("type"),
                     resultSet.getInt("power"), resultSet.getString("engine_id")));
            return passengerCar;
    }

    @SneakyThrows
    private Car createTruck(ResultSet resultSet){
        Truck truck = new Truck();
        truck.setId(resultSet.getString("car_id"));
        truck.setLoadCapacity(resultSet.getInt("loadCapacity"));
        truck.setManufacturer(resultSet.getString("manufacturer"));
        truck.setColor(Color.valueOf(resultSet.getString("color")));
        truck.setCount(resultSet.getInt("count"));
        truck.setPrice(resultSet.getInt("price"));
        truck.setType(Type.TRUCK);
        truck.setEngine(new Engine(resultSet.getString("type"),
                resultSet.getInt("power"), resultSet.getString("engine_id")));
        return truck;

    }

}
