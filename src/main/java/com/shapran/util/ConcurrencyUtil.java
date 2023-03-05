package com.shapran.util;

import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class ConcurrencyUtil {
    private final int phasesCount = 3;
    private static final Random random = new Random();

    public void createThreads() {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i = 0; i <= 3; i++) {
            Thread t = new Thread(new MyBarrier(cyclicBarrier));
            t.start();
        }
    }

    public void createThreadsAndWait() {
        final CyclicBarrier cb = new CyclicBarrier(4);

        for (int i = 0; i < phasesCount; i++) {
            Thread t = new Thread(new MyRunnable(cb, phasesCount));
            t.start();
        }
        for (int i = 0; i <= 3; i++) {
            cb.getNumberWaiting();
        }

    }

    class MyBarrier implements Runnable {
        private final CyclicBarrier cyclicBarrier;

        MyBarrier(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ", id: " +
                        Thread.currentThread().getId() + ": iteration " + i + " is waiting on barrier");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + ", id: " +
                        Thread.currentThread().getId() + " has crossed the barrier");
            }
        }
    }

    class MyRunnable implements Runnable {
        private final CyclicBarrier cb;
        private final int phasesCount;

        MyRunnable(CyclicBarrier cb, int phasesCount) {
            this.cb = cb;
            this.phasesCount = phasesCount;
        }

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < phasesCount; i++) {
                System.out.println(Thread.currentThread().getId() +
                        ", time: " + LocalDateTime.now() + " seconds in phase " + phasesCount);
                TimeUnit.SECONDS.sleep(random.nextInt(11));
                System.out.println(Thread.currentThread().getId() + " completed phase " + phasesCount);
                cb.await();
            }
            cb.reset();
        }
    }

}