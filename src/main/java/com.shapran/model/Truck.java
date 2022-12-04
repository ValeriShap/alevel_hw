package com.shapran.model;

public class Truck extends Car implements CountRestore{
    private int loadCapacity;

    public Truck(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public Truck(String manufacturer, Color color, Engine engine, int loadCapacity) {
        super(manufacturer, color, engine);
        this.loadCapacity = loadCapacity;
    }

    public Truck() {

    }

    @Override
    public void restore() {
        this.count = 50;
        System.out.println("Count of truck - " + count);
    }
}
