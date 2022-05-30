package com.atguigu.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestThreadPool {
    public static void main(String[] args) {

        //1.创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        List<Future<Integer>> list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            Future<Integer> future = pool.submit(new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    int sum = 0;
                    for (int i = 0; i < 100; i++) {
                        sum += i;
                    }
                    return sum;
                }
            });
            list.add(future);
        }

        pool.shutdown();
        for (Future<Integer> i:
             list) {
            try {
                System.out.println(i.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }


}
class ThreadPoolDemo implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}