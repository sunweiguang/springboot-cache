package com.tianqiauto.threadTest;/*
 * @Author sunweiguang
 * @Description: 测试volatile保证变量的可见性
 */

public class Test {
    private static volatile boolean isOver = false;

    public static void main(String[] args) {
        Runnable run  = new Runnable() {
            @Override
            public void run() {
                while (!isOver);
                /*{
                    System.out.println("子线程在执行");
                   };
                    加上这句print后会volatile的作用丢失？？ 是因为println的源码中加的有synchronized代码块，
                    每次执行都会吧共享内存中的数据同步到工作内存中，
                    那么死循环在睡眠过后执行的第一次打印就会把isOver = true
                    同步到当前线程堆内存中，所以第二次循环的时候就停止了；
                */
            }
        };
        Thread thread1 = new Thread(run);
        Thread thread2 = new Thread(run);
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isOver = true;
    }
}
