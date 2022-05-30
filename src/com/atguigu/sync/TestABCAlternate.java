package com.atguigu.sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 编写一个程序，开启3个线程，这三个线程的id分别是A,B,C。每个线程将自己的id在屏幕上打印10遍，要求输出的结果必须按顺序显示
 * 如：ABCABCABC... 依次递归
 */
public class TestABCAlternate {
    public static void main(String[] args) {
        AlternateDemo alternateDemo = new AlternateDemo();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                alternateDemo.lock.lock();
                while (alternateDemo.currentloop != "" && alternateDemo.currentloop != "C") {

                        alternateDemo.condition.await();

                }

                alternateDemo.loop("第" + i + "次A");
                alternateDemo.currentloop = "A";
                alternateDemo.condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    alternateDemo.lock.unlock();
                }
            }

        }).start();
        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                try {
                    alternateDemo.lock.lock();
                while (alternateDemo.currentloop != "A") {

                        alternateDemo.condition.await();

                }
                alternateDemo.loop("第" + i + "次B");
                alternateDemo.currentloop = "B";
                alternateDemo.condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    alternateDemo.lock.unlock();
                }
            }
        }).start();
        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                try {
                    alternateDemo.lock.lock();
                while (alternateDemo.currentloop != "B") {

                        alternateDemo.condition.await();

                }
                alternateDemo.loop("第" + i + "次C");
                alternateDemo.currentloop = "C";
                alternateDemo.condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    alternateDemo.lock.unlock();
                }
            }

        }).start();


    }

}

class AlternateDemo {

    public String currentloop = "";

    Lock lock = new ReentrantLock();

    Condition condition = lock.newCondition();
    public void loop(String loop) {

        System.out.println(loop);
    }


}
