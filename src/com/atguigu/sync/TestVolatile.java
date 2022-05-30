package com.atguigu.sync;
/*
 *一.volatile关键字
 */
public class TestVolatile {
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo).start();
        while (true){
            if (threadDemo.isflag()){
                System.out.println("-------");
            }
        }
    }
}

class ThreadDemo implements Runnable {

    private volatile boolean flag = false;
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag  = true;
    }

    public boolean isflag() {
        return flag;
    }
}
