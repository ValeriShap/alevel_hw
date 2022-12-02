package com.shapran.util;

import java.util.Random;

public class RandomGenerator {
    private Random random = new Random();
    public int randomNumberCar(){
         return random.nextInt(0,11);
    }
}
