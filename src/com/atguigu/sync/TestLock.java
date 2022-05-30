package com.atguigu.sync;



import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 一。用户解决多线程安全问题的方式
 * synchronized:隐式锁
 * 1.同步代码块
 *
 * 2.同步方法
 *
 * jdk 1.5后：
 * 3.同步锁Lock
 * 注意：是一个显示锁，需要通过lock()方法上锁，必须通过unlock()方法进行解锁
 */
public class TestLock {
    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Ticket2 ticket2 = new Ticket2(lock);
        new Thread(ticket2,"售票窗口1").start();
        new Thread(ticket2,"售票窗口2").start();
        new Thread(ticket2,"售票窗口3").start();
    }

}
class Ticket2 implements Runnable{

    private static int num = 50;

    private Lock lock;
    public Ticket2(Lock lock) {
        this.lock=lock;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            while (num>0) {
                Thread.sleep(200);
                System.out.println("售出：" + num-- + "剩于票数：" + num);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


}
