package service;

import model.Car;
import model.Color;
import model.Engine;

import java.util.Random;

public class CarService {
    Random random = new Random();

    public Car create() {
        Car car = new Car();
        car.setManufacturer(randomString());
        car.setEngine(new Engine(random.nextInt(0, 1000), randomString()));
        car.setColor(randomColor());
        car.setCount(1);
        car.setPrice(randomNumber());
        return car;
    }

    public void printf(Car car) {
        System.out.printf("Manufacturer: %s; Power: %o; Type: %s; Color: %s; Count; %d; Price: %d.%n",
                car.getManufacturer(), car.getEngine().getPower(), car.getEngine().getType(), car.getColor(),
                car.getCount(), car.getPrice());
    }

    private String randomString() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(random.nextInt(alphabet.length() - 1)));
        }
        return sb.toString();
    }

    private Color randomColor() {
        Color[] colors = Color.values();
        int randomColor = random.nextInt(colors.length);
        return colors[randomColor];
    }

    private int randomNumber() {
        return random.nextInt(5000, 75000);
    }

    public static void check(Car car) {
        if (car.getCount() > 0 && car.getEngine().getPower() > 200) {
            System.out.println("The car is ready for sale");
        } else if (car.getCount() == 0 && car.getEngine().getPower() < 200) {
            System.out.println("Car is not available and low power");
        } else if (car.getCount() == 0) {
            System.out.println("Car is not available");
        } else if (car.getEngine().getPower() < 200) {
            System.out.println("Low power");
        }
    }
}

