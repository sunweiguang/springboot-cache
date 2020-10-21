package com.tianqiauto.threadTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ZiDingYiThreadPoolExecutor {

    public static class TempThread implements Runnable {
        @Override
        public void run() {
            // 打印正在执行的缓存线程信息
            System.out.println(Thread.currentThread().getName() + "正在被执行");
            try {
                // sleep一秒保证3个任务在分别在3个线程上执行
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        // 创建数组型缓冲等待队列
        BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(10);
        // ThreadPoolExecutor:创建自定义线程池，池中保存的线程数为3，允许最大的线程数为6
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(10, 100, 50, TimeUnit.MILLISECONDS, bq);

        // 创建3个任务
        for (int i = 0; i < 20; i++) {
            Runnable t1 = new TempThread();
            tpe.execute(t1);
        }
        // 关闭自定义线程池
        tpe.shutdown();
    }
}
