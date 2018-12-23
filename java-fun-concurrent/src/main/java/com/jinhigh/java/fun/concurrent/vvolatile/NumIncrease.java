package com.jinhigh.java.fun.concurrent.vvolatile;

/**
 * Desc
 * Created by argel.
 * Time 2018/11/8 下午8:29
 */
public class NumIncrease {

    private static volatile int num = 0;

    private static final int LOOP_COUNT = 100000;

    static class LoopVolatile implements Runnable {
        @Override
        public void run() {
            for(int i =0;i<LOOP_COUNT;i++){
                num++;
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new LoopVolatile());
        t1.start();
        Thread t2 = new Thread(new LoopVolatile());
        t2.start();

        while (t1.isAlive() || t2.isAlive()) {
            System.out.println("num is: " + num);
        }
        System.out.println("final num is: " + num);
    }
}
