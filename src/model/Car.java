package model;

import java.util.Random;

public class Car {
    private String manufacturer;
    private Color color;
    private int count;
    private int price;
    private Engine engine;
    private Random random = new Random();

    public Car() {
    }

    public Car(String manufacturer, Color color, Engine engine) {
        this.manufacturer = manufacturer;
        this.color = color;
        this.count = 1;
        this.price = random.nextInt(20500, 73000);
        this.engine = engine;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}

