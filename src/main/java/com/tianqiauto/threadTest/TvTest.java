package com.tianqiauto.threadTest;
/**
 * 协作模型:生产者消费者实现方式一:管程法
 * 借助缓冲区
 */
public class TvTest {
	public static void main(String[] args) {
		Tv tv = new Tv();
		new Player(tv).start();
		new Watcher(tv).start();
	}
}

class Tv {
	private String voice;
	private boolean flag = true;

	public synchronized void play(String voice){
		if(!flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.voice = voice;
		System.out.println("表演了"+voice);
		this.notifyAll();
		this.flag = !this.flag;
	}

	public synchronized void watch(){
		if(flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("观看了"+voice);
		this.notifyAll();
		this.flag = !this.flag;
	}

}


class Player extends  Thread{
	private Tv tv;

	public Player(Tv tv){
		this.tv = tv;
	}
	public void run(){
		for (int i = 0; i < 20; i++) {
			if (i%2 == 0){
				tv.play("哈哈哈");
			}else{
				tv.play("呵呵呵");
			}
		}
	}


}


class Watcher extends Thread{
	private Tv tv;

	public Watcher(Tv tv){
		this.tv = tv;
	}

	public void run(){
		for (int i = 0; i < 20; i++) {
			this.tv.watch();
		}
	}
}
