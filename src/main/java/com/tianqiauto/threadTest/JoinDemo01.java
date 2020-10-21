package com.tianqiauto.threadTest;

public class JoinDemo01 extends Thread {
    public static void main(String[] args) {
        JoinDemo01 demo = new JoinDemo01();
        Thread t = new Thread(demo);
        t.start();
        for (int i = 0; i < 1000; i++) {
            if (50 == i) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("main...." + i);
        }
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("join...." + i);
        }
    }
}
