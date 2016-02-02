package bbu_mobile.com.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

	private static final Integer workerCount = 5;

	public CountDownLatchDemo() {

	}

	public static void main(String args[]) {

		CountDownLatch begin = new CountDownLatch(1);
		CountDownLatch end = new CountDownLatch(workerCount);

		Worker[] workerList = new Worker[workerCount];
		for (int i = 0; i < workerCount; i++) {
			workerList[i] = new Worker(i, begin, end);
		}
		// 设置特定的线程池，大小为5
		ExecutorService exe = Executors.newFixedThreadPool(workerCount);
		for (Worker p : workerList)
			exe.execute(p); // 分配线程
		System.out.println("Race begins!");
		begin.countDown();
		try {
			end.await(); // 等待end状态变为0，即为比赛结束
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			System.out.println("Race ends!");
		}
		exe.shutdown();
	}
}
