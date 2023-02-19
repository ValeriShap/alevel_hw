package com.shapran.repository;

import com.shapran.anotations.Singleton;
import com.shapran.model.Car;
import com.shapran.model.Color;

import java.util.List;
import java.util.Optional;
@Singleton
public class CarArrayRepository implements Crud<Car> {
    private static Car[] cars = new Car[10];
    private static CarArrayRepository instance;

    public CarArrayRepository() {
    }

    public static CarArrayRepository getInstance(){
        if (instance == null){
            instance = new CarArrayRepository();
        }
        return instance;
    }
    @Override
    public void save(final Car car) {
        final int index = putCar(car);
        if (index == cars.length) {
            final int oldLength = cars.length;
            increaseArray();
            cars[oldLength] = car;
        }
    }
    @Override
    public List<Car> getAll() {
        final int newLength = foundLength();
        final List<Car> newCars = List.of(new Car[newLength]);
        System.arraycopy(cars, 0, newCars, 0, newLength);
        return newCars;
    }

    @Override
    public Optional<Car> getById(final String id) {
        for (Car car : cars) {
            if (car.getId().equals(id)) {
                return Optional.of(car);
            }
        }
        return Optional.empty();
    }

    @Override
    public void delete(final String id) {
        int index = 0;
        for (; index < cars.length; index++) {
            if (cars[index].getId().equals(id)) {
                break;
            }
        }
        if (index != cars.length){
            System.arraycopy(cars, index + 1, cars, index,
                cars.length - (index + 1));
    }
}

    public void insert(int index, final Car car) {
        int emptyIndex = findEmptyIndex(cars);
        if (emptyIndex == -1) {
            save(car);
        } else {
            if (index >= emptyIndex) {
                save(car);
            } else {
                putCarByIndex(index, car, cars);
            }
        }
    }

        public int findEmptyIndex(Car[] cars) {
            int emptyIndex = -1;
            for (int i = 0; i < cars.length; i++) {
                if (cars[i] != null) {
                    emptyIndex = i;
                }
            }
            return emptyIndex;
        }

        public void putCarByIndex(int index, final Car car, Car[] cars) {
            System.arraycopy(cars, index, cars, index + 1,
                    cars.length - (index + 1));
            cars[index] = car;
        }

    public void updateColor(final String id, final Color color) {
        getById(id).ifPresent(car -> car.setColor(color));
        }

    private int foundLength() {
        int newLength = 0;
        for (Car car : cars) {
            if (car != null) {
                newLength++;
            } else {
                break;
            }
        }
        return newLength;
    }
    private int putCar(final Car car) {
        int index = 0;
        for (; index < cars.length; index++) {
            if (cars[index] == null) {
                cars[index] = car;
                break;
            }
        }
        return index;
    }

    private void increaseArray() {
        Car[] newCars = new Car[cars.length * 2];
        System.arraycopy(cars, 0, newCars, 0, cars.length);
        cars = newCars;
    }
    }
