package com.jinhigh.java.fun.base.designpattern;

/**
 * Created by lijin9 on 2018/6/29.
 */
public class TestMain {

    public static void main(String[] args) {
        Thread t1 = new Thread(() ->{
            Singleton singleton = Singleton.getSingleton();
            System.out.println(singleton.hashCode());
            singleton.sout();
        });

        Thread t2 = new Thread(() ->{
            Singleton singleton = Singleton.getSingleton();
            System.out.println(singleton.hashCode());
            singleton.sout();
        });

        Thread t3 = new Thread(() ->{
            Singleton singleton = Singleton.getSingleton();
            System.out.println(singleton.hashCode());
            singleton.sout();
        });

        Thread t4 = new Thread(() ->{
            Singleton singleton = Singleton.getSingleton();
            System.out.println(singleton.hashCode());
            singleton.sout();
        });


        Thread t5 = new Thread(() ->{
            Singleton singleton = Singleton.getSingleton();
            System.out.println(singleton.hashCode());
            singleton.sout();
        });


        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }
}
