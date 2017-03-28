package chapter4_Thread_Executors;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MainPeriodicalMonteCarloPi {

	public static void main(String[] args) {

		/*
		 * Create ScheduledThreadPoolExecutor using the newScheduledThreadPool()
		 * method of the Executors class. Pass the number 1 as the parameter to
		 * that method.
		 */
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		System.out.printf("Main: Starting at: %s\n", new Date());

		// Create a new Task object.
		TaskMonteCarloPi taskMonteCarloPi = new TaskMonteCarloPi("Task");

		/*
		 * Send it to the executor using the scheduledAtFixRate() method. Use as
		 * parameters the task created earlier, the number 1 is the initial
		 * waiting period, the number 4 is the extra waiting period for the next
		 * task to start, and the constant TimeUnit.SECONDS. This method returns
		 * a ScheduledFuture object that you can use to control the status of
		 * the task.
		 */
		ScheduledFuture<?> result = executor.scheduleAtFixedRate(
				taskMonteCarloPi, 1, 4, TimeUnit.SECONDS);

		/*
		 * Create a loop to write the time remaining for the next execution of
		 * the task. In the loop, use the getDelay() method of the
		 * ScheduledFuture object to get the number of milliseconds until the
		 * next execution of the task.
		 */
		for (int i = 0; i < 100; i++) {
			System.out.printf("Main: Delay for Task: %d milliseconds left\n", result
					.getDelay(TimeUnit.MILLISECONDS));
			// Sleep the thread during 500 milliseconds.
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Finish the executor using the shutdown() method.
		executor.shutdown();

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Main: Finished at: %s\n", new Date());
	}
}