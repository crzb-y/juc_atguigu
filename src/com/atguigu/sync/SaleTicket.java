package com.atguigu.sync;

//第一步 创建资源类，定义属性和操作方法
class Ticket{
    //票数
    private int number=30;
    //操作方法卖票
    public synchronized void sale(){
        //判断是否有票
        if (number>0){
            System.out.println(Thread.currentThread().getName()+":卖出:"+(number--)+"剩下:"+number);
        }
    }

}


public class SaleTicket {
    //第二步 创建多个线程,调用资源类的操作方法
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"AA").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"BB").start();


        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"CC").start();
    }
}
