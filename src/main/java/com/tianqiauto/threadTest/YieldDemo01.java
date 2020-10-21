package com.tianqiauto.threadTest;

public class YieldDemo01 extends Thread {
    public static void main(String[] args) {
        YieldDemo01 demo = new YieldDemo01();
        Thread t = new Thread(demo);
        t.start();
        for (int i = 0; i < 1000; i++) {
            if (i % 20 == 0) {
                //暂停本线程 main
                Thread.yield();
            }
            System.out.println("main...." + i);
        }
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println("yield...." + i);
        }
    }
}
