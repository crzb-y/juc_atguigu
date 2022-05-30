package com.atguigu.sync;

import java.util.concurrent.CountDownLatch;

/*
 *CountDownLatch：闭锁，在完成某些运算时，只有其他所有线程的运算全部完成，当前运算才继续执行
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        LatchDemo latchDemo = new LatchDemo(countDownLatch);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            new Thread(latchDemo).start();
        }
        long end = System.currentTimeMillis();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("消耗时间：" + (end - start));
    }

}

class LatchDemo implements Runnable {
    private CountDownLatch latch;

    LatchDemo(CountDownLatch latch) {
        this.latch = latch;

    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5000; i++) {
                if (i % 2 == 0) {
                    System.out.println(i);
                }
            }

        } finally {
            latch.countDown();
        }


    }
}
