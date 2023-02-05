package com.shapran.repository;

import com.shapran.anotations.Singleton;
import com.shapran.model.Car;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiPredicate;

@Singleton
public class CarMapRepository implements Crud<Car> {
    private static final Map<String,Car> CAR_MAP = new LinkedHashMap();
    private static CarMapRepository instance;
    private static final BiPredicate<Car, String> CHECK_ID = ((car, id) -> car.getId().equals(id));

    private CarMapRepository() {

    }

    public static CarMapRepository getInstance(){
        if (instance == null){
            instance = new CarMapRepository();
        }
        return instance;
    }

    @Override
    public void save(final Car car){
        CAR_MAP.entrySet()
                .stream()
                .filter(newCar -> CHECK_ID.test(newCar.getValue(), car.getId()))
                .findAny()
                .ifPresentOrElse(
                        newCar -> newCar.getValue().setCount(newCar.getValue().getCount() + car.getCount()),
                        () -> CAR_MAP.put(car.getId(), car)
                );

    }

    @Override
    public Car[] getAll(){
        return CAR_MAP
                .values().toArray(new Car[0]);
    }

    @Override
    public Optional<Car> getById(final String id){
        return CAR_MAP.values()
                .stream()
                .findAny().filter(newCar -> CHECK_ID.test(newCar, id));
    }

    @Override
    public void delete(final String id){
        CAR_MAP.remove(id);
    }

}
