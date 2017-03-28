package chapter6_Concurrent_Collections;

import java.util.Date;
import java.util.concurrent.DelayQueue;

public class ApplicationOffice implements Runnable {

	// Declare a private int attribute named id to store a number that
	// identifies this application.
	private int id;

	// Declare a private DelayQueue attribute parameterized with the Event class
	// named queue.
	private DelayQueue<Application> queue;

	// The constructor of the class.
	public ApplicationOffice(int id, DelayQueue<Application> queue) {
		this.id = id;
		this.queue = queue;
	}

	@Override
	/*
	 * Implement the run() method. First, calculate the date of the application
	 * that will be submited. Then calculate the delay of this application
	 */
	public void run() {
		Date now = new Date();
		Date delay = new Date();
		delay.setTime(now.getTime() + (id * 1000));
		System.out.printf("Application Office: " +
				"an Application #%s is applied by Main thread" +
				", It will be ready in %s\n", id, delay);

		Application application = new Application(delay);
		queue.add(application);

	}

}
