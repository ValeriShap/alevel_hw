package com.shapran.container;

import com.shapran.model.Car;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Setter
@Getter

public class GenericContainer<T extends Car> {
    private final Random random = new Random();
    private T car;

    public GenericContainer(T car) {
        this.car = car;
    }

    public void print(){
        System.out.println(car);
    }

    public void increaseCount(){
        car.setCount(random.nextInt(100,300));
    }

    public  <N extends Number> void increaseCount(N number){
        car.setCount(car.getCount() + number.intValue());
    }
}
