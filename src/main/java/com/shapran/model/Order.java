package com.shapran.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Order {
    private List<Car> cars;
    private String order_id;
    private Date date;

    public Order() {
        this.order_id = UUID.randomUUID().toString();
        date = Date.valueOf(LocalDate.now());
    }

    public Order(String id, Date date) {
        this.order_id = id;
        this.date = date;
    }

    public void addCarToOrder(Order order, Car car){
        if (car != null){
            order.getCars().add(car);
        }

    }
}
