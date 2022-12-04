package com.shapran.service;

import com.shapran.model.*;
import com.shapran.repository.CarArrayRepository;
import com.shapran.util.RandomGenerator;

import java.util.Arrays;
import java.util.Random;

public class CarService {
    private final CarArrayRepository carArrayRepository;
    private Random random = new Random();

    public CarService(final CarArrayRepository carArrayRepository) {
        this.carArrayRepository = carArrayRepository;
    }

    public Car createCar() {
        Car car = new PassengerCar();
        car.setManufacturer(randomString());
        car.setEngine(new Engine(randomString()));
        car.setColor(randomColor());
        car.setCount(randomNumber());
        car.setPrice(randomNumber());
        carArrayRepository.save(car);
        return car;

    }

    public int create(RandomGenerator randomGenerator) {
        int count = randomGenerator.randomNumberCar();
        if (count <= 0) {
            return -1;
        } else {
            for (int i = 1; i <= count; i++) {
                print(createCar());
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

    private String randomString() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(random.nextInt(alphabet.length() - 1)));
        }
        return sb.toString();
    }

    private Color randomColor() {
        Color[] colors = Color.values();
        int randomColor = random.nextInt(colors.length);
        return colors[randomColor];
    }

    private int randomNumber() {
        return random.nextInt(5000, 75000);
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

            public PassengerCar  createPassengerCar(){
        PassengerCar passengerCar = new PassengerCar();
        Color color = randomColor();
        return passengerCar;
            }
            public Truck createTruck(){
        Truck truck = new Truck();
        Color color = randomColor();
        return truck;
            }
        }


