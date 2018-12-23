package com.jinhigh.java.fun.concurrent.vvolatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Desc
 * Created by argel.
 * Time 2018/11/8 下午8:29
 */
public class Atomicncrease {

    private static AtomicInteger num = new AtomicInteger(0);

    static class LoopIncrease implements Runnable {
        @Override
        public void run() {
            for(int i =0;i<100000;i++){
                num.incrementAndGet();
            }
        }
    }
    public static void main(String[] args) {
        Thread t1 = new Thread(new LoopIncrease());
        t1.start();
        Thread t2 = new Thread(new LoopIncrease());
        t2.start();

        while (t1.isAlive() || t2.isAlive()) {
            System.out.println("num is: " + num.get());
        }
        System.out.println("final num is: " + num.get());
    }
}
