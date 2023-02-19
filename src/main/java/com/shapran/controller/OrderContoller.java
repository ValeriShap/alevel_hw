package com.shapran.controller;

import com.shapran.model.Order;
import com.shapran.service.hibernateService.OrderHibernateService;

import java.util.List;
import java.util.Optional;

public class OrderContoller {
    private final OrderHibernateService orderHibernateService;

    public OrderContoller(OrderHibernateService orderHibernateService) {
        this.orderHibernateService = orderHibernateService;
    }
    public String create(){
        Order order = orderHibernateService.createAndSave();
        return order.getOrder_id();
    }
    public List<Order> getAll(){
        return orderHibernateService.getAllOrders();
    }

    public Optional<Order> getById(String id){
        return orderHibernateService.getById(id);
    }

    public void delete(String id){
        orderHibernateService.delete(id);
    }
}
