package com.jinhigh.java.fun.base;

public class test {

    public static void main(String[] args) {
        new test();
    }

    /**
     * 静态变量和静态代码块的加载顺序由编写先后决定
     */
    static int num = 4;

    {
        num += 3;
        System.out.println("b");
    }

    int a = 5;

    { // 成员变量第三个
        System.out.println("c");
    }

    test() { // 类的构造函数，第四个加载
        System.out.println("d");
    }

    static {
        System.out.println("a");
    }

    /**
     *  静态方法，调用的时候才加载
     */
    static void run()
    {
        System.out.println("e");
    }
}


 class Print {

     public Print(String s){
         System.out.print(s + " ");
     }
 }

 class Parent{

     public static Print obj1 = new Print("1");

     public Print obj2 = new Print("2");

     public static Print obj3 = new Print("3");

     static{
         new Print("4");
     }

     public static Print obj4 = new Print("5");

     public Print obj5 = new Print("6");

     public Parent(){
         new Print("7");
     }

 }

 class Child extends Parent{

     static{
         new Print("a");
     }

     public static Print obj1 = new Print("b");

     public Print obj2 = new Print("c");

     public Child (){
         new Print("d");
     }

     public static Print obj3 = new Print("e");

     public Print obj4 = new Print("f");

     public static void main(String [] args){
         Parent obj1 = new Child ();
         Parent obj2 = new Child ();
     }
 }