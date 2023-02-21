package com.shapran.service.hibernateService;

import com.shapran.model.Car;
import com.shapran.model.Order;
import com.shapran.repository.hibernate.CrudHibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class OrderHibernateService {
    private final CrudHibernate<Order> repositoty;
    private final CarHibernateService carHibernateService;
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderHibernateService.class);

    public OrderHibernateService(CrudHibernate<Order> repositoty, CarHibernateService carHibernateService) {
        this.repositoty = repositoty;
        this.carHibernateService = carHibernateService;
    }

    public Order createOrder(){
        List<Car> cars = new ArrayList<>();
        Order order = new Order();
        for (int i = 0; i < 4; i++){
            Car car = carHibernateService.createAndSave();
            car.setOrder(order);
            cars.add(car);
        }
        LOGGER.info("Order was created");
        return order;
    }

    public Order createAndSave(){
        Order order = createOrder();
        repositoty.save(order);
        LOGGER.info("Save order with {} id", order.getOrder_id());
        return order;
    }

    public List<Order> getAllOrders(){
        LOGGER.info("Get all orders from BD");
        return repositoty.getAll();
    }

    public Optional<Order> getById(String id) {
        LOGGER.info("Search order with {} id", id);
        return repositoty.getById(id);
    }

    public void delete (String id) {
        LOGGER.info("Delete order with {} id", id);
        repositoty.delete(id);
    }
}
