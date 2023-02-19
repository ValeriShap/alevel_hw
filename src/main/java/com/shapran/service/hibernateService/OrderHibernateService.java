package com.shapran.service.hibernateService;

import com.shapran.model.Car;
import com.shapran.model.Order;
import com.shapran.repository.hibernate.CrudHibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class OrderHibernateService {
    private final CrudHibernate<Order> repositoty;
    private final CarHibernateService carHibernateService;

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
        return order;
    }

    public Order createAndSave(){
        Order order = createOrder();
        repositoty.save(order);
        return order;
    }

    public List<Order> getAllOrders(){
        return repositoty.getAll();
    }

    public Optional<Order> getById(String id) {
        return repositoty.getById(id);
    }

    public void delete (String id) {
        repositoty.delete(id);
    }
}
