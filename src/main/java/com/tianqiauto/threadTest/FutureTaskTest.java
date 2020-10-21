package com.tianqiauto.threadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<Integer> task = new FutureTask<>(new FutureThread());
        FutureTask<Integer> task1 = new FutureTask<>(new FutureThread());

        new Thread(task, "AA").start();
        new Thread(task1, "BB").start(); //多个线程抢一个task，只会执行一次,想要执行多次起多个FutureTask


        int result01 = 100;

        int result02 = task.get(); //建议放到最后，否则会造成阻塞
        int result03 = task1.get(); //建议放到最后，否则会造成阻塞

        System.out.println("******result:"+(result01+result02+result03));
    }
}

class FutureThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        if (Thread.currentThread().getName().equals("AA")){
            System.out.println(Thread.currentThread().getName()+"**************come in Callable");
            Thread.sleep(2000);
            return 2000;
        }
        System.out.println(Thread.currentThread().getName()+"**************come in Callable");
        return 1024;
    }

}
