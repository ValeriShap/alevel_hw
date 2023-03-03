package com.shapran;

import com.shapran.model.*;
import com.shapran.util.ThreadArrayUtil;
import com.shapran.util.RandomGenerator;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        ThreadArrayUtil threadArrayUtil = new ThreadArrayUtil();
        threadArrayUtil.fillArray();
        threadArrayUtil.sumArray();

//        CarService carService = new CarService(new CarJdbcRepository());
//        Car car1 = carService.createCar(Type.CAR);
//        Car car2 = carService.createCar(Type.CAR);
//        Car car3 = carService.createCar(Type.CAR);
//        Car car4 = carService.createCar(Type.TRUCK);
//        Car car5 = carService.createCar(Type.TRUCK);
//
//        Order order = new Order();
//        order.addCarToOrder(order, car1);
//        order.addCarToOrder(order, car2);
//        order.addCarToOrder(order, car3);
//        order.addCarToOrder(order, car4);
//        order.addCarToOrder(order, car5);
//
//        OrderJDBCRepository orderRepository = OrderJDBCRepository.getInstance();
//        orderRepository.save(order);
//
//        List<Car> cars = carService.getAll();
//        System.out.println(cars);
//        String id = order.getCars().get(0).getId();
//        System.out.println(carService.find(id));
//        carService.delete(id);
//        System.out.println(carService.find(id));
//        System.out.println(orderRepository.getAll());

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

//        final CrudHibernate<Car> carCrudHibernate = new CarHibernateRepository();
//        final EngineHibernateService engineHibernateService = new EngineHibernateService();
//        final CarHibernateService carHibernateService = new CarHibernateService(carCrudHibernate, engineHibernateService);
//        final CarController carController = new CarController(carHibernateService);
//        final CrudHibernate<Order> orderCrudHibernate = new OrderHibernateRepository();
//        final OrderHibernateService orderHibernateService = new OrderHibernateService(orderCrudHibernate, carHibernateService);
//        final OrderContoller orderContoller = new OrderContoller(orderHibernateService);
//
//        String orderId = orderContoller.create();
//        List<Order> orders = orderContoller.getAll();
//        System.out.println("All orders: " + orders);
//
//        Optional<Order> order = orderContoller.getById(orderId);
//        System.out.println("Is the id of the BD and the generated order id identical: " +
//                orderId.equals(order.orElseThrow().getOrder_id()));
//
//        List<Car> cars = carController.getAll();
//        System.out.println("All cars: " + cars);
//
//        Optional<Car> carOptional = Optional.ofNullable(cars.get(0));
//        carOptional.ifPresent(car -> carController.delete(car.getId()));
//
//        List<Car> deleteCar = carController.getAll();
//        System.out.println("Delete first car: "+ deleteCar);
//        RandomGenerator randomGenerator = new RandomGenerator();
//        CarFactory carFactory = new CarFactory();
//        Car car = carFactory.createCar(randomGenerator.randomType())
//                .addManufacturer(randomGenerator.randomString())
//                .addColor(randomGenerator.randomColor())
//                .addCount(randomGenerator.randomNumberCar())
//                .addPrice(randomGenerator.randomNumber())
//                .addEngine(new Engine())
//                .build();
//        System.out.println(car);
  }
}
