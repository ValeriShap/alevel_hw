package com.shapran.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Random;
import java.util.UUID;

@Getter
@Setter
@ToString
public abstract class Car {
    private final String id;
    private String manufacturer;
    private Color color;
    protected int count;
    private int price;
    private Engine engine;
    private final Random random = new Random();

    public Car() {
        this.id = UUID.randomUUID().toString();
    }

    public Car(String manufacturer, Color color, Engine engine) {
        this.id = UUID.randomUUID().toString();
        this.manufacturer = manufacturer;
        this.color = color;
        this.count = 1;
        this.price = random.nextInt(20500, 73000);
        this.engine = engine;
    }
}