package com.jinhigh.java.fun.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Desc
 * Created by argel.
 * Time 2018/11/8 下午8:29
 */
public class VolatileTest {

    private static volatile long _longVal = 0;

    private static class LoopVolatile implements Runnable {
        public void run() {
            long val = 0;
            while (val < 10000000L) {
                _longVal++;
                val++;
            }
        }
    }

    private static class LoopVolatile2 implements Runnable {
        public void run() {
            long val = 0;
            while (val < 10000000L) {
                _longVal++;
                val++;
            }
        }
    }


    public static void main(String[] args) {
//        Thread t1 = new Thread(new LoopVolatile());
//        t1.start();
//
////        Thread t2 = new Thread(new LoopVolatile2());
////        t2.start();
//
////        while (t1.isAlive() || t2.isAlive()) {
////            System.out.println("final val is: " + _longVal);
////        }
//
//        while (t1.isAlive()){
//
//        }
//
//        System.out.println("final val is: " + _longVal);


        AtomicInteger ai = new AtomicInteger();
        System.out.println(ai.addAndGet(1));
        System.out.println(ai.getAndAdd(1));
        System.out.println(ai.get());
    }
}
