package com.shapran.service.hibernateService;

import com.shapran.model.*;
import com.shapran.repository.hibernate.CrudHibernate;
import com.shapran.util.RandomGenerator;

import java.util.List;
import java.util.Optional;

public class CarHibernateService {
    private final CrudHibernate<Car> repository;
    private final EngineHibernateService engineHibernateService;
    private final RandomGenerator randomGenerator = new RandomGenerator();

    public CarHibernateService(CrudHibernate<Car> repository, EngineHibernateService engineHibernateService) {
        this.repository = repository;
        this.engineHibernateService = engineHibernateService;
    }

    public Car createCar(Type type){
        Car car = createTypeCar(type);
        car.setManufacturer(randomGenerator.randomString());
        car.setEngine(engineHibernateService.createEngine());
        car.setColor(randomGenerator.randomColor());
        car.setCount(randomGenerator.randomNumberCar());
        car.setPrice(randomGenerator.randomNumber());
        car.setType(randomGenerator.randomType());
        return car;
    }

    private Car createTypeCar(Type type) {
        if (type.equals(Type.CAR)) {
            PassengerCar passengerCar = new PassengerCar();
            return passengerCar;
        } else if (type.equals(Type.TRUCK)) {
            Truck truck = new Truck();
            return truck;
        }
        return null;
    }
    public Car createAndSave(){
        Car car = createCar(randomGenerator.randomType());
        repository.save(car);
        return car;
    }
    public List<Car> getAllCars(){
        return repository.getAll();
    }

    public Optional<Car> getById(String id) {
        return repository.getById(id);
    }

    public void delete (String id) {
        repository.delete(id);
    }

}
