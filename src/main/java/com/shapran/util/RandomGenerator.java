package com.shapran.util;

import com.shapran.model.Type;
import com.shapran.model.Color;

import java.util.Random;

public class RandomGenerator {
    private Random random = new Random();
    public int randomNumberCar(){
         return random.nextInt(0,11);
    }

    public Type randomType(){
        Type[] types = Type.values();
        int randomType = random.nextInt(types.length);
        return types[randomType];
    }
    public int randomNumber() {
        return random.nextInt(5000, 75000);
    }

    public Color randomColor() {
        Color[] colors = Color.values();
        int randomColor = random.nextInt(colors.length);
        return colors[randomColor];
    }
    public String randomString() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(random.nextInt(alphabet.length() - 1)));
        }
        return sb.toString();
    }
}
