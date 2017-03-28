package chapter5_Fork_Join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class MainListForkJoin {

	public static void main(String[] args) {

		// create a list
		ListCreator test = new ListCreator(23);

		// create a task that will divide and separate.
		TaskListSeparator task = new TaskListSeparator(test.getList());

		// Create a ForkJoinPool object using the constructor without
		// parameters. Thus the number of threads area equal to the PC's processors.
		ForkJoinPool pool = new ForkJoinPool();

		// Execute the task in the pool using the execute() method.
		pool.execute(task);

		/*
		 * a block of code that shows information about the evolution of the
		 * pool every 3 seconds writing to the console the value of some
		 * parameters of the pool until the task finishes its execution.
		 */
		do {
			System.out.printf("Main: Thread Count: %d\n", pool
					.getActiveThreadCount());
			System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());
			System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
			try {
				//sleep for 6 seconds
				TimeUnit.MILLISECONDS.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());

		//Shut down the pool using the shutdown() method.
		pool.shutdown();
		System.out.println("----Main: The ForkJoinPool" +
				" has shutted down----");

	}

}
