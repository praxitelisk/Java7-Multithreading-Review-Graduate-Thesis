package chapter4_Thread_Executors;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainClockDelay {

	public static void main(String[] args) {

		int hours = 24;
		int minutes = 60;
		int seconds = 60;

		/*
		 * Create an executor of the ScheduledThreadPoolExecutor class using the
		 * newScheduledThreadPool() method of the Executors utility factory
		 * class passing 1 as a parameter.
		 */
		ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors
				.newScheduledThreadPool(1);

		int duration = hours * minutes * seconds;

		/*
		 * Initialize and start a few tasks with the schedule() method of the
		 * ScheduledThreadPoolExecutor instance.
		 */
		for (int i = 0; i < duration; i++) {
			TaskClockDelay taskClockDelay = new TaskClockDelay();
			executor.schedule(taskClockDelay, i + 1, TimeUnit.SECONDS);
		}

		// executor shutdown
		executor.shutdown();

		/*
		 * Wait for the finalization of all the tasks using the
		 * awaitTermination() method of the executor.
		 */
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Write a message to indicate the time when the program finishes.
		System.out.printf("Main: Clock Ends at: %s\n", new Date());
	
	} //end of main
}