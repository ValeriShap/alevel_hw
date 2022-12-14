package com.shapran;

import com.shapran.container.CarComparator;
import com.shapran.container.CarTree;
import com.shapran.model.Car;
import com.shapran.model.PassengerCar;
import com.shapran.model.Truck;
import com.shapran.model.Type;
import com.shapran.repository.CarArrayRepository;
import com.shapran.service.CarService;
import com.shapran.util.AlgorithmUtil;
import com.shapran.util.RandomGenerator;


import java.util.*;

public class Main {
    public static void main(String[] args) {

        CarService carService = new CarService(new CarArrayRepository());
        RandomGenerator random = new RandomGenerator();
        AlgorithmUtil algorithmUtil = new AlgorithmUtil();

        carService.createCar(Type.CAR, 5);
        carService.printAll();
        Car[] cars = carService.getAll();
//        Car search = cars[0];
//        Car[] sortedCar = AlgorithmUtil.bubbleSort(cars);
//        System.out.println(Arrays.toString(sortedCar));
//        System.out.println(algorithmUtil.binarySearch(sortedCar,search));

        PassengerCar passengerCar1 = new PassengerCar();
        PassengerCar passengerCar2 = new PassengerCar();
        PassengerCar passengerCar3 = new PassengerCar();
        PassengerCar passengerCar4 = new PassengerCar();
        PassengerCar passengerCar5 = new PassengerCar();
        passengerCar1.setCount(3);
        passengerCar2.setCount(8);
        passengerCar3.setCount(5);
        passengerCar4.setCount(1);
        passengerCar5.setCount(9);

        CarTree <PassengerCar> ct = new CarTree<>(passengerCar1);
        ct.add(null,passengerCar1);
        ct.add(null,passengerCar2);
        ct.add(null,passengerCar3);
        ct.add(null,passengerCar4);
        ct.add(null,passengerCar5);

        System.out.println("Sum of all elements: " + ct.sumOfAllElements(ct.getRoot()));

        HashMap<String,Integer> mapManufacturer = carService.getMapManufacturerAndCount(carService.getAll());
        System.out.println(mapManufacturer.toString());

        Map<String, List<Car>> mapPower = carService.getMapEngineAndPower(carService.getAll());
        System.out.println(mapPower.toString());

    }
}
