package com.tianqiauto.threadTest;/*
 * @Author sunweiguang
 * @Description ：测试volatile 不保证原子性
 */

public class ThreadLocalTest {
    public volatile static int x = 0;
    public static void main(String args[]) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    x++;
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            System.out.println("x=" + x);
        } catch (Exception e) {

        }

    }
}
