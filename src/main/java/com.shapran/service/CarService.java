package com.shapran.service;

import com.shapran.exception.UserInputException;
import com.shapran.model.*;
import com.shapran.repository.CarArrayRepository;
import com.shapran.util.RandomGenerator;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class CarService {
    private final CarArrayRepository carArrayRepository;
    private final RandomGenerator randomGenerator = new RandomGenerator();
    private Random random = new Random();

    public CarService(final CarArrayRepository carArrayRepository) {
        this.carArrayRepository = carArrayRepository;
    }

    public Car createCar(Type type) {
        Car car = new PassengerCar();
        car.setManufacturer(randomGenerator.randomString());
        car.setEngine(new Engine(randomGenerator.randomString()));
        car.setColor(randomGenerator.randomColor());
        car.setCount(randomGenerator.randomNumber());
        car.setPrice(randomGenerator.randomNumber());
        carArrayRepository.save(car);
        return car;

    }
    public boolean carEquals(Car bmw, Car audi) {
        if (bmw.getClass().equals(audi.getClass()) && bmw.hashCode() == audi.hashCode()) {
            return bmw.equals(audi);
        } else {
            return false;
        }
    }

    public void printManufacturerAndCount(Car car){
        Optional.ofNullable(car).ifPresent(x -> {
            System.out.printf("Manufaccturer: %s, count: %d%n", x.getManufacturer(), x.getCount());
        });
    }

    public void printColor(Car car){
        Car car1 = Optional.ofNullable(car).orElse(createCar(Type.CAR));
        System.out.printf("Color of: " + car1.getId() + " " + car1.getColor());
    }

    public void checkCount(Car car) throws UserInputException {
        Optional<Car> carOptional = Optional.ofNullable(car);
        Car filterCar = carOptional
                .filter(c-> c.getCount() > 10)
                .orElseThrow(UserInputException:: new);
        System.out.printf("Manufaccturer: %s, count: %d%n" + filterCar.getManufacturer(), filterCar.getCount());
    }

    public void printEngineInfo(Car car){
        Optional<Car> carOptional = Optional.ofNullable(car);
        Car randomCar = carOptional.orElseGet(() -> {
            System.out.print("Create a new random car");
            return createRandomCar();
        });
        carOptional.map(Engine -> {
            return Engine.getEngine().getPower();
        }).ifPresent(power -> System.out.println("Engine power: " + power));
    }

    public void printInfo(Car car){
        Optional<Car> carOptional = Optional.ofNullable(car);
        carOptional.ifPresentOrElse(
                b -> print(b),
                () -> print(createRandomCar()));
    }

    public void createCar(Type type, int count) {
        for (int i = 0; i < count; i++) {
            createCar(type);
        }
    }

    public int create() {
        int count = randomGenerator.randomNumberCar();
        if (count <= 0) {
            return -1;
        } else {
            for (int i = 1; i <= count; i++) {
                print(createCar(Type.CAR));
            }
            return count;
        }
    }

    public void printAll() {
        final Car[] all = carArrayRepository.getAll();
        System.out.println(Arrays.toString(all));
    }

    public Car[] getAll() {
        return carArrayRepository.getAll();
    }

    public Car find(final String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        return carArrayRepository.getById(id);
    }

    public void delete(final String id) {
        if (id == null || id.isEmpty()) {
            return;
        }
        carArrayRepository.delete(id);
    }

    public void print(Car car) {
        System.out.println(car);
    }

    public Car createRandomCar(){
        return createCar(randomGenerator.randomType());
    }

    public static void check(Car car) {
        if (car.getEngine() == null) {
            System.out.println("Engine is null");
            return;
        }
            if (car.getCount() > 0 && car.getEngine().getPower() > 200) {
                System.out.println("The car is ready for sale");
            } else if (car.getCount() == 0 && car.getEngine().getPower() < 200) {
                System.out.println("Car is not available and low power");
            } else if (car.getCount() == 0) {
                System.out.println("Car is not available");
            } else if
                (car.getEngine().getPower() < 200) {
                    System.out.println("Low power");
                }else {
                System.out.println("The engine is 0");
            }
            }

            public PassengerCar  createPassengerCar(Type car){
        PassengerCar passengerCar = new PassengerCar();
        Color color = randomGenerator.randomColor();
        return passengerCar;
            }
//            public Truck createTruck(){
//        Truck truck = new Truck();
//        Color color = randomColor();
//        return truck;}

        }


