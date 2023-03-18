package com.shapran.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarFactory {
    private final static Logger LOGGER = LoggerFactory.getLogger(CarFactory.class);

//    public Car.CarBuilder createCar(Type type){
//        Car.CarBuilder carBuilder = null;
//        if (type == Type.CAR){
//            carBuilder = new PassengerCar.PassengerCarBuilder();
//        }else if (type == Type.TRUCK){
//            carBuilder = new Truck.TruckBuilder();
//        }else{
//            LOGGER.warn("Another {} type of car", type);
//        }
//        return carBuilder;
//    }
}
