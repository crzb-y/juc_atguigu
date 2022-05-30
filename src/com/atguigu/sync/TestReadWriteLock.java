package com.atguigu.sync;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock {
    public static void main(String[] args) {
        ReadWriteLockDemo rw = new ReadWriteLockDemo();

        new Thread(()->{

        });
    }
}
class ReadWriteLockDemo{
    private int number = 0;

    ReadWriteLock lock = new ReentrantReadWriteLock();

    //读
    public void get(){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+":"+number);
        }finally {
            lock.readLock().unlock();
        }
    }
    //写
    public void set(int number){
        lock.writeLock().lock();
        try {


        System.out.println(Thread.currentThread().getName());
        this.number = number;
        }finally {
            lock.writeLock().unlock();
        }
    }
}
