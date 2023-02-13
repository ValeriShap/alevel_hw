package com.shapran.repository;

import com.shapran.model.Order;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderJDBCRepository extends CrudJdbc<Order>{
    private static OrderJDBCRepository instance;


private OrderJDBCRepository(){
    createTablesIfNotExists();
}
    public static OrderJDBCRepository getInstance(){
        if (instance == null){
            instance = new OrderJDBCRepository();
        }
        return instance;
    }

    @SneakyThrows
    private void createTablesIfNotExists(){
        Connection connection = JdbcManager.getConnection();
        Statement statement = connection.createStatement();
        String createOrderTable = "CREATE TABLE IF NOT EXISTS \"order\"(order_id VARCHAR(40) PRIMARY KEY, date TIMESTAMP NOT NULL)";
        statement.execute(createOrderTable);
        statement.close();
        connection.close();
    }

    @SneakyThrows
    @Override
    public void save(Order order) {
        String insertOrder = "INSERT INTO \"order\"(order_id, date) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertOrder);
        preparedStatement.setString(1, order.getOrder_id());
        preparedStatement.setDate(2, order.getDate());
        preparedStatement.executeUpdate();
        close(preparedStatement);
    }

    @SneakyThrows
    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String getAllOrders = "SELECT * FROM \"order\" ORDER BY date";
        PreparedStatement preparedStatement = connection.prepareStatement(getAllOrders);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            orders.add(createOrder(resultSet));
        }
        close(preparedStatement);
        return orders;
    }

    @SneakyThrows
    @Override
    public Optional<Order> getById(String id) {
        String getOrderById = "SELECT * FROM \"order\" WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(getOrderById);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Optional<Order> orders = Optional.empty();
        if (resultSet.next()){
            orders = Optional.of(createOrder(resultSet));
        }
        close(preparedStatement);
        return orders;
    }

    @SneakyThrows
    @Override
    public void delete(String id) {
        String deleteOrder = "DELETE FROM \"order\" WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteOrder);
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
        close(preparedStatement);

    }
    private Order createOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setOrder_id(resultSet.getString("order_id"));
        order.setDate(resultSet.getDate("date"));
        return order;
    }
}
