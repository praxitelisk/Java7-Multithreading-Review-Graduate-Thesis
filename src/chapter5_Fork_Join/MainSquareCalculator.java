package chapter5_Fork_Join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class MainSquareCalculator {
	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		TaskSquareCalculator task = new TaskSquareCalculator(data, 0,
				data.length);
		pool.execute(task);

		do {
			System.out.printf("------------\n\tMain Report: Thread Count: %d\n"
					+ "\tMain Report: Thread Steal: %d\n"
					+ "\tMain Report: Parallelism: %d\n------------\n"
					, pool.getActiveThreadCount()
					, pool.getStealCount()
					, pool.getParallelism());
			try {
				// sleep for 2 seconds until the new main report
				TimeUnit.MILLISECONDS.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());

		pool.shutdown();
		System.out.println("----Main: The ForkJoinPool"
				+ " has shutted down----");

	}
}
