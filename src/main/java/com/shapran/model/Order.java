package com.shapran.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String order_id;
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Car> cars = new ArrayList<>();
    private Date date;

    public Order() {
        order_id = UUID.randomUUID().toString();
        date = Date.valueOf(LocalDate.now());

    }

    public void addCarToOrder(Order order, Car car) {
        if (car != null) {
            order.getCars().add(car);
        }

    }
}
