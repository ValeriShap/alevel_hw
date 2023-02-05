package com.shapran.repository;

import com.shapran.model.Car;
import com.shapran.model.Color;

public interface Repository <T extends Car> {

    void save(Car car);

    Car[] getAll();

    Car getById(final String id);

    void delete(final String id);

    void insert(int index,final Car car);

    int findEmptyIndex(Car[] cars);

    void putCarByIndex(int index, final Car car, Car[] cars);

    void updateColor(final String id, final Color color);

    int foundLength();

    int putCar(final Car car);

    void increaseArray();

}
