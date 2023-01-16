package com.shapran.service;

import com.shapran.model.Car;
import com.shapran.model.PassengerCar;
import com.shapran.model.Type;
import com.shapran.repository.CarArrayRepository;
import com.shapran.util.RandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {
    private CarService target;
    private CarArrayRepository repository;
    private RandomGenerator randomGenerator;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(CarArrayRepository.class);
        target = new CarService(repository);
        randomGenerator = Mockito.mock(RandomGenerator.class);

    }

    @org.junit.jupiter.api.Test
    void createCar() {
        final Car car = target.createCar(Type.CAR);
        Assertions.assertNotNull(car);
        Assertions.assertNotNull(car.getId());

    }
    @Test
    void create(){

        int expected = 5;
        Mockito.when(randomGenerator.randomNumberCar()).thenReturn(5);

        int actual = target.create();

        Assertions.assertEquals(expected,actual);
    }

    @org.junit.jupiter.api.Test
    void createCountZero() {
        int expected = -1;

        Mockito.when(randomGenerator.randomNumberCar()).thenReturn(0);
        int actual = target.create();

        Assertions.assertEquals(expected,actual);
    }

    @Test
    void createCountNegative() {
        int expected = -1;

        Mockito.when(randomGenerator.randomNumberCar()).thenReturn(-3);
        int actual = target.create();

        Assertions.assertEquals(expected,actual);
    }

    @org.junit.jupiter.api.Test
    void printAll() {

        Assertions.assertDoesNotThrow(() -> target.printAll());
    }

    @org.junit.jupiter.api.Test
    void getAll() {
        Assertions.assertDoesNotThrow(() -> target.getAll());
    }
    @Test
    void getAllNull(){
        Car[] actual = target.getAll();
        Assertions.assertNull(actual);
    }

//    @Test
//    void find(){
//        Car expected = new PassengerCar();
//        String id = "123";
//        Mockito.when(repository.getById("123")).thenReturn(expected);
//        Car actual = target.find(id);
//        Assertions.assertEquals(expected,actual);
//    }

//    @org.junit.jupiter.api.Test
//    void findNullId() {
//        String id = null;
//        Car car = target.find(id);
//        Assertions.assertNull(car);
//    }
//
//    @Test
//    void findEmptyId(){
//        String id = "";
//        Car car = target.find(id);
//        Assertions.assertNull(car);
//    }

//    @Test
//    void findNotFound(){
//        String id = "123";
//        Mockito.when(repository.getById("123")).thenReturn(null);
//        Car car = target.find(id);
//        Assertions.assertNull(car);
//    }
//
//    @org.junit.jupiter.api.Test
//    void delete() {
//        Car expected = new PassengerCar();
//        String id = "123";
//        Mockito.when(repository.getById("123")).thenReturn(expected);
//        Car actual = target.find(id);
//        Assertions.assertEquals(expected,actual);
//    }

    @Test
    void deleteNullId(){
        Assertions.assertDoesNotThrow(() -> target.delete(null));
    }

    @Test
    void deleteEmptyId(){
        Assertions.assertDoesNotThrow(() -> target.delete(" "));
    }

    @org.junit.jupiter.api.Test
    void print() {
        Assertions.assertDoesNotThrow(() -> target.print(new PassengerCar()));
    }

}