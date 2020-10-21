package com.tianqiauto.threadTest;
/*
 * @Author sunweiguang
 * 多线程死锁问题
 */


public class MackupTest {
    public static void main(String[] args) {
        Makeup g1 = new Makeup("小妹",1);
        Makeup g2 = new Makeup("大姐",2);
        Makeup g3 = new Makeup("老娘",1);
        new Thread(g1).start();
        new Thread(g2).start();
        new Thread(g3).start();
    }



}

class Makeup implements Runnable{

    static Lapstick lapstick = new Lapstick();
    static Mirror mirror = new Mirror();
    String girl;
    int step;

    public Makeup(String girl, int step) {
        this.girl = girl;
        this.step = step;
    }

    @Override
    public void run() {
        if(step ==1 ){
            useSticklap();
            useMirror();
        }else{
            useMirror();
            useSticklap();
        }
    }

    void useSticklap(){
        synchronized (lapstick){
            System.out.println(this.girl +"拿到口红，要用3秒钟");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.girl +"用完口红");
        }
    }

    void useMirror(){
        synchronized (mirror){
            System.out.println(this.girl +"拿到镜子，要用3秒钟");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.girl +"用过镜子");
        }
    }
}

class Lapstick{

}

class Mirror{

}
