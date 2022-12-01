package com.shapran;

import com.shapran.model.Car;
import com.shapran.repository.CarArrayRepository;
import com.shapran.service.CarService;
import com.shapran.util.RandomGenerator;

public class Main {
    public static void main(String[] args) {

        CarService carService = new CarService(new CarArrayRepository());
        RandomGenerator random = new RandomGenerator();
        Car car1 = carService.createCar();
        Car car2 = carService.createCar();
        Car car3 = carService.createCar();

        carService.check(car1);
        carService.check(car2);
        carService.check(car3);
        System.out.println(carService.create(random));

    }
}
