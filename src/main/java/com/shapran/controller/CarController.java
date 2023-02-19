package com.shapran.controller;

import com.shapran.model.Car;
import com.shapran.service.hibernateService.CarHibernateService;

import java.util.List;
import java.util.Optional;

public class CarController {
    CarHibernateService carHibernateService;

    public CarController(CarHibernateService carHibernateService) {
        this.carHibernateService = carHibernateService;
    }

    public String create(){
        Car car = carHibernateService.createAndSave();
        return car.getId();
    }
    public List<Car> getAll(){
        return carHibernateService.getAllCars();
    }

    public Optional<Car> getById(String id){
        return carHibernateService.getById(id);
    }

    public void delete(String id){
        carHibernateService.delete(id);
    }
}
