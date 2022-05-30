package com.atguigu.sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者案例
 */
public class TestProductorAndConsumerForLock {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);

        new Thread(productor, "生产者").start();
        new Thread(consumer, "消费者").start();

        new Thread(productor, "生产者").start();
        new Thread(consumer, "消费者").start();


    }

}

//店员
class Clerk {
    private int product = 0;
    Lock lock = new ReentrantLock();

    Condition condition = lock.newCondition();

    //进货
    public void get() {

        lock.lock();
        try {
            while (product >= 1) {
                System.out.println("产品已满！");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            product++;
            System.out.println(Thread.currentThread().getName() + product);
            condition.signalAll();

        } finally {
            lock.unlock();
        }
    }

    //出售
    public void sale() {
        lock.lock();
        try {
            while (product <= 0) {
                System.out.println("没有产品！");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            product--;
            System.out.println(Thread.currentThread().getName() + product);
            condition.signalAll();


        } finally {
            lock.unlock();
        }
    }


}

class Productor implements Runnable {

    Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.get();
        }

    }
}


class Consumer implements Runnable {

    Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }

    }
}