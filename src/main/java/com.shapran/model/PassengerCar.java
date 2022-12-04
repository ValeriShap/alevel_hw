package com.shapran.model;

public class  PassengerCar extends Car implements CountRestore {
    private int passengerCount;

    public PassengerCar(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public PassengerCar(String manufacturer, Color color, Engine engine, int passengerCount) {
        super(manufacturer, color, engine);
        this.passengerCount = passengerCount;
    }

    public PassengerCar() {

    }

    @Override
    public void restore() {
        this.count = 100;
        System.out.println("Count of passenger car - " + count);
    }
}
