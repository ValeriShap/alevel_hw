package com.shapran;

import com.shapran.model.Car;
import com.shapran.model.Order;
import com.shapran.model.Type;
import com.shapran.repository.CarJdbcRepository;
import com.shapran.repository.JdbcManager;
import com.shapran.repository.OrderJDBCRepository;
import com.shapran.util.AlgorithmUtil;
import com.shapran.util.AnnotationProcessor;
import com.shapran.util.RandomGenerator;
import com.shapran.repository.CarArrayRepository;
import com.shapran.service.CarService;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        CarService carService = new CarService(new CarJdbcRepository());
        Car car1 = carService.createCar(Type.CAR);
        Car car2 = carService.createCar(Type.CAR);
        Car car3 = carService.createCar(Type.CAR);
        Car car4 = carService.createCar(Type.TRUCK);
        Car car5 = carService.createCar(Type.TRUCK);

        System.out.println(car2);
//        Order order = new Order();
//        order.addCarToOrder(order,car1);
//        order.addCarToOrder(order,car2);
//        order.addCarToOrder(order,car3);
//        order.addCarToOrder(order,car4);
//        order.addCarToOrder(order,car5);
//
//        OrderJDBCRepository orderRepository = OrderJDBCRepository.getInstance();
//        orderRepository.save(order);
//        System.out.println(order);
//        System.out.println(Arrays.toString(new List[]{carService.getAll()}));
//        String id = carService.getAll().get(0).getId();
//        System.out.println(carService.find(id) +" FOUND CAR");
//        carService.delete(id);
//        System.out.println(carService.find(id) + " AFTER DELETE");
//        System.out.println(Arrays.toString(new List[]{orderRepository.getAll()}));

//        carService.printAll();
//        Car[] cars = carService.getAll();
//        Car search = cars[0];
//        Car[] sortedCar = AlgorithmUtil.bubbleSort(cars);
//        System.out.println(Arrays.toString(sortedCar));
//        System.out.println(algorithmUtil.binarySearch(sortedCar,search));

//        PassengerCar passengerCar1 = new PassengerCar();
//        PassengerCar passengerCar2 = new PassengerCar();
//        PassengerCar passengerCar3 = new PassengerCar();
//        PassengerCar passengerCar4 = new PassengerCar();
//        PassengerCar passengerCar5 = new PassengerCar();
//        passengerCar1.setCount(3);
//        passengerCar2.setCount(8);
//        passengerCar3.setCount(5);
//        passengerCar4.setCount(1);
//        passengerCar5.setCount(9);
//
//        CarTree <PassengerCar> ct = new CarTree<>(passengerCar1);
//        ct.add(null,passengerCar1);
//        ct.add(null,passengerCar2);
//        ct.add(null,passengerCar3);
//        ct.add(null,passengerCar4);
//        ct.add(null,passengerCar5);
//
//        System.out.println("Sum of all elements: " + ct.sumOfAllElements(ct.getRoot()));
//
//        HashMap<String,Integer> mapManufacturer = carService.getMapManufacturerAndCount(carService.getAll());
//        System.out.println(mapManufacturer.toString());
//
//        Map<String, List<Car>> mapPower = carService.getMapEngineAndPower(carService.getAll());
//        System.out.println(mapPower.toString());

//        Map<String,Object> xmlFile = carService.fromFileToCar("car.xml");
//        Map<String,Object> jsonFile = carService.fromFileToCar("car.json");
//
//        System.out.println(xmlFile);
//        System.out.println(jsonFile);

//        AnnotationProcessor annotationProcessor = new AnnotationProcessor();
//        annotationProcessor.getSingleton();
//        System.out.println(AnnotationProcessor.cache.values());
//
//        annotationProcessor.getAutowired();
//        System.out.println(AnnotationProcessor.cache.values());

//        Connection connection = JdbcManager.getConnection();
//        System.out.println(connection.getCatalog());

    }
}
