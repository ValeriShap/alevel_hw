package com.shapran.repository;

import com.shapran.model.Car;

import java.util.List;
import java.util.Optional;

public interface Crud<T>{
    void save(final T car);

    List<T> getAll();

    Optional<T> getById(final String id);

    void delete(final String id);
}
