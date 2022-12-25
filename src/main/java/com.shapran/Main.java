package com.shapran;

import com.shapran.model.Car;
import com.shapran.model.PassengerCar;
import com.shapran.model.Truck;
import com.shapran.model.Type;
import com.shapran.repository.CarArrayRepository;
import com.shapran.service.CarService;
import com.shapran.util.AlgorithmUtil;
import com.shapran.util.RandomGenerator;

import java.util.Arrays;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        CarService carService = new CarService(new CarArrayRepository());
        RandomGenerator random = new RandomGenerator();
        AlgorithmUtil algorithmUtil = new AlgorithmUtil();

        carService.createCar(Type.CAR, 5);
        carService.printAll();
        Car[] cars = carService.getAll();
        Car search = cars[0];
        Car[] sortedCar = AlgorithmUtil.bubbleSort(cars);
        System.out.println(Arrays.toString(sortedCar));
        System.out.println(algorithmUtil.binarySearch(sortedCar,search));


    }
}
