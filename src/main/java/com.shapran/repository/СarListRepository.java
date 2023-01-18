package com.shapran.repository;

import com.shapran.container.CarList;
import com.shapran.model.Car;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

public class СarListRepository implements Crud<Car> {
    private static final List<Car> CARS = new LinkedList<>();
    private static СarListRepository instance;
    private static final BiPredicate<Car, String> CHECK_ID = (car, id) -> car.getId().equals(id);

    public СarListRepository() {
    }

    public static СarListRepository getInstance() {
        if (instance == null) {
            instance = new СarListRepository();
        }
        return instance;
    }
    @Override
    public void save(final Car car) {
        CARS.stream()
                .filter(carsCar -> CHECK_ID.test(carsCar, car.getId()))
                .findAny()
                .ifPresentOrElse(
                        carsCar -> carsCar.setCount(car.getCount()),
                        () -> CARS.add(car)
                );
    }

    @Override
    public Car[] getAll(){
        return CARS.toArray(new Car[0]);
    }

    @Override
    public Optional<Car> getById(final String id){
        return CARS.stream()
                .filter(carsCar -> CHECK_ID.test(carsCar,id))
                .findAny();
    }

    @Override
    public void delete(final String id){
        CARS.removeIf(carsCar-> CHECK_ID.test(carsCar,id));
    }

}
