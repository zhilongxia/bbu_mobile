package bbu_mobile.com.concurrent;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {

	private Integer id;
	private CountDownLatch begin;
	private CountDownLatch end;

	public Worker(Integer i, CountDownLatch begin, CountDownLatch end) {

		this.id = i;
		this.begin = begin;
		this.end = end;
	}

	@Override
	public void run() {

		try {
			begin.await();
			Thread.sleep((long) (Math.random() * 100)); // 随机分配时间，即运动员完成时间
			System.out.println("Worker" + id + " arrived.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			end.countDown();
		}
	}
}
