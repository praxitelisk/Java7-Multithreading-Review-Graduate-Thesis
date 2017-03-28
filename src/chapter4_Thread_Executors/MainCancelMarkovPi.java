package chapter4_Thread_Executors;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainCancelMarkovPi {

	public static void main(String[] args) {

		/*
		 * Create a ThreadPoolExecutor object using the newCachedThreadPool()
		 * method of the Executors class.
		 */
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
				.newCachedThreadPool();

		// Create a new Task object.
		TaskMarkovPi taskMarkovPi = new TaskMarkovPi("task");

		int executionTime = 4;
		// Send the task to the executor using the submit() method.
		System.out.printf("Main: Executing the Task for %d seconds\n", executionTime);
		Future<String> result = executor.submit(taskMarkovPi);

		// Put the main task to sleep for 20 seconds.
		try {
			TimeUnit.SECONDS.sleep(executionTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/*
		 * Cancel the execution of the task using the cancel() method of the
		 * Future object named result returned by the submit() method. Pass the
		 * true value as a parameter of the cancel() method.
		 */
		System.out.printf("Main: Canceling the Task\n");
		result.cancel(true);

		System.out.printf("Main: Canceled: %s\n", result.isCancelled());
		System.out.printf("Main: Done: %s\n", result.isDone());

		//Finish the executor with the shutdown() method
		executor.shutdown();
		System.out.printf("Main: The executor has finished\n");

	}
}
