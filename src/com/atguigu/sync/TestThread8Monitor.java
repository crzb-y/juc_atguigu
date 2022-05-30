package com.atguigu.sync;

public class TestThread8Monitor {
    public static void main(String[] args) {
        Number number = new Number();
        Number number2 = new Number();

        new Thread(()->{
            number.getOne();
        }).start();

        new Thread(()->{
            number.getTow();
//            number2.getTow();
        }).start();
//        new Thread(()->{
//            number.getThree();
//        }).start();
    }
}
class Number{
    public static synchronized void getOne(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("one");
    }

    public static synchronized void getTow(){
        System.out.println("two");
    }

    public  void getThree(){
        System.out.println("Three");
    }
}
