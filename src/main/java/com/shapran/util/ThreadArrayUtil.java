package com.shapran.util;

import lombok.Getter;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Random;

@Getter
public class ThreadArrayUtil {
    private static final Object LOCK = new Object();
    private static final int[] array = new int[100];

    @SneakyThrows
    public int[] fillArray() {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new ArrayThread());
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        return array;
    }

    @SneakyThrows
    public int sumArray() {
        SumThread[] threards = new SumThread[4];
        for (int i = 0; i < threards.length; i++) {
            threards[i] = new SumThread(array, i * (array.length / 4),
                    (i + 1) * (array.length / 4));
            threards[i].start();
        }
        int sum = 0;
        for (SumThread sumThread : threards) {
            sumThread.join();
            sum += sumThread.resultSum();
        }
        return sum;
    }

    private static class ArrayThread extends Thread {
        private Random random = new Random();

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                int count = random.nextInt(1001);
                synchronized (LOCK) {
                    boolean unique = false;
                    while (!unique) {
                        int index = random.nextInt(array.length);
                        if (array[index] == 0) {
                            array[index] = count;
                            unique = true;
                        }
                    }
                }
            }
        }
    }

    private static class SumThread extends Thread {
        private int[] array;
        private int startIndex;
        private int endIndex;
        public int sum;

        private SumThread(int[] array, int startIndex, int endIndex) {
            this.array = array;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public void run() {
            sum = Arrays.stream(array, startIndex, endIndex).sum();
        }

        private int resultSum() {
            System.out.println("First array element: " + startIndex + " , end element: " + endIndex +
                    " , and sum of all: " + sum);
            return sum;
        }
    }
}
