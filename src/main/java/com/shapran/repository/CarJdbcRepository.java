package com.shapran.repository;

import com.shapran.model.*;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarJdbcRepository extends CrudJdbc<Car> {
    private static CarJdbcRepository instance;

    public CarJdbcRepository() {
        createTablesIfNeeded();
    }

    public static CarJdbcRepository getInstance() {
        if (instance == null) {
            instance = new CarJdbcRepository();
        }
        return instance;
    }

    @SneakyThrows
    public void createTablesIfNeeded() {
        connection = JdbcManager.getConnection();
        Statement statement = connection.createStatement();
        String createOrderTable = """
                CREATE TABLE IF NOT EXISTS orders(order_id VARCHAR(40) PRIMARY KEY,
                date TIMESTAMP NOT NULL);
                """;
        String createEngineTable = """
                CREATE TABLE IF NOT EXISTS engine(engine_id VARCHAR(40) PRIMARY KEY,
                type VARCHAR(35) NOT NULL, power INT NOT NULL);
                """;
        String createCarTable = """
                CREATE TABLE IF NOT EXISTS cars(car_id VARCHAR(40) PRIMARY KEY,
                manufacturer VARCHAR(25) NOT NULL, color VARCHAR(25) NOT NULL, count INT NOT NULL,
                price INT NOT NULL, order_id VARCHAR(40) NULL,
                engine_id VARCHAR(40) NOT NULL,
                CONSTRAINT orders_fkey FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE,
                CONSTRAINT engine_car_fkey FOREIGN KEY (engine_id)  REFERENCES engine (engine_id) ON DELETE CASCADE);
                """;
        String createTruckTable = """
                CREATE TABLE IF NOT EXISTS truck(car_id VARCHAR(40) NOT NULL PRIMARY KEY REFERENCES cars(car_id),
                load_capacity INT NOT NULL);
                """;
        String createPcTable = """
                CREATE TABLE IF NOT EXISTS passenger_cars(car_id VARCHAR(40) NOT NULL PRIMARY KEY REFERENCES cars(car_id),
                passenger_count INT NOT NULL);
                 """;
        statement.execute(createOrderTable);
        statement.execute(createEngineTable);
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
        String insertCars = "INSERT INTO cars(car_id, manufacturer, color, count, price, engine_id) " +
                "VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertCars);
        preparedStatement.setString(1, car.getId());
        preparedStatement.setString(2, car.getManufacturer());
        preparedStatement.setString(3, car.getColor().toString());
        preparedStatement.setInt(4, car.getCount());
        preparedStatement.setInt(5, car.getPrice());
        preparedStatement.setString(6, car.getEngine().getEngine_id());
        preparedStatement.executeUpdate();
        if (car.getType() == Type.CAR) {
            String insertTypeCar = "INSERT INTO passenger_cars(car_id, passenger_count) VALUES(?,?)";
            PreparedStatement preparedStatementPassCar = connection.prepareStatement(insertTypeCar);
            preparedStatementPassCar.setString(1, car.getId());
            preparedStatementPassCar.setInt(2, ((PassengerCar) car).getPassengerCount());
            preparedStatementPassCar.executeUpdate();
        } else if (car.getType() == Type.TRUCK) {
            String insertTypeTruck = "INSERT INTO truck(car_id, load_capacity) VALUES(?,?)";
            PreparedStatement preparedStatementTruck = connection.prepareStatement(insertTypeTruck);
            preparedStatementTruck.setString(1, car.getId());
            preparedStatementTruck.setInt(2, ((Truck) car).getLoadCapacity());
            preparedStatementTruck.executeUpdate();
        }
        close(preparedStatement);
    }

    @SneakyThrows
    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        connection = JdbcManager.getConnection();
        String getAllCars = "SELECT * FROM cars LEFT JOIN passenger_cars ON cars.car_id = passenger_cars.car_id " +
                "LEFT JOIN truck ON cars.car_id = truck.car_id " +
                "LEFT JOIN engine ON cars.engine_id = engine.engine_id ";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getAllCars);
        while (resultSet.next()) {
            if (resultSet.getString("passenger_count") != null) {
                cars.add(createPassengerCar(resultSet));
            } else if (resultSet.getString("load_capacity") != null) {
                cars.add(createTruck(resultSet));
            }
        }
        close(statement);
        return cars;
    }

    @SneakyThrows
    @Override
    public Optional<Car> getById(String id) {
        String getCarById = "SELECT * FROM cars LEFT JOIN engine ON cars.engine_id = engine.engine_id " +
                "LEFT JOIN passenger_cars ON cars.car_id = passenger_cars.car_id " +
                "LEFT JOIN truck ON cars.car_id = truck.car_id " +
                "WHERE cars.car_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(getCarById);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            if (resultSet.getString("passenger_count") != null) {
                return Optional.of(createPassengerCar(resultSet));
            } else if (resultSet.getString("load_capacity") != null) {
                return Optional.of(createTruck(resultSet));
            }
        }
        close(preparedStatement);
        return Optional.empty();
    }

    @SneakyThrows
    @Override
    public void delete(String id) {
        String deleteCar = "DELETE FROM cars WHERE cars.car_id = ?";
        PreparedStatement deleteCars = connection.prepareStatement(deleteCar);
        deleteCars.setString(1, id);
        deleteCars.executeUpdate();
        close(deleteCars);
    }

    @SneakyThrows
    private Car createPassengerCar(ResultSet resultSet) {
        PassengerCar passengerCar = new PassengerCar();
        passengerCar.setId(resultSet.getString("car_id"));
        passengerCar.setPassengerCount(resultSet.getInt("passenger_count"));
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
    private Car createTruck(ResultSet resultSet) {
        Truck truck = new Truck();
        truck.setId(resultSet.getString("car_id"));
        truck.setLoadCapacity(resultSet.getInt("load_capacity"));
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
