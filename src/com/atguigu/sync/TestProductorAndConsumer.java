package com.atguigu.sync;

/**
 * 生产者消费者案例
 */
public class TestProductorAndConsumer {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);

        new Thread(productor,"生产者").start();
        new Thread(consumer,"消费者").start();

        new Thread(productor,"生产者").start();
        new Thread(consumer,"消费者").start();


    }

}

////店员
//class Clerk{
//    private int product=0;
//    //进货
//    public synchronized void get(){
//        while (product>=1){
//            System.out.println("产品已满！");
//            try {
//                this.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//            product++;
//            System.out.println(Thread.currentThread().getName()+product);
//            this.notifyAll();
//
//    }
//
//    //出售
//    public synchronized void sale() {
//        while (product <= 0) {
//            System.out.println("没有产品！");
//            try {
//                this.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//            product--;
//            System.out.println(Thread.currentThread().getName()+product);
//            this.notifyAll();
//
//    }
//
//
//}
//
//class Productor implements Runnable{
//
//    Clerk clerk;
//    public Productor(Clerk clerk){
//    this.clerk=clerk;
//    }
//
//    @Override
//    public void run() {
//        for (int i = 0; i <20 ; i++) {
//            try {
//               Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            clerk.get();
//        }
//
//    }
//}
//
//
//class Consumer implements Runnable{
//
//    Clerk clerk;
//    public Consumer(Clerk clerk){
//        this.clerk=clerk;
//    }
//
//    @Override
//    public void run() {
//        for (int i = 0; i <20 ; i++) {
//            clerk.sale();
//        }
//
//    }
//}