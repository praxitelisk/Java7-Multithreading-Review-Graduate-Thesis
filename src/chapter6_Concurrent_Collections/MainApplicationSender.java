package chapter6_Concurrent_Collections;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class MainApplicationSender {

	public static void main(String[] args) throws Exception {

		// Create a DelayedQueue object parameterized with the Event class.
		DelayQueue<Application> queue = new DelayQueue<Application>();

		// Create an array of five Thread objects to store the applications
		// you're
		// going to apply.
		Thread applicationThread[] = new Thread[5];

		// Create five ApplicationOffice objects, with different IDs.
		for (int i = 0; i < applicationThread.length; i++) {
			Random random = new Random();
			ApplicationOffice applicationSubmit = new ApplicationOffice(random
					.nextInt(10), queue);
			applicationThread[i] = new Thread(applicationSubmit);
		}

		// Launch all the five tasks created earlier.
		for (int i = 0; i < applicationThread.length; i++) {
			applicationThread[i].start();
		}

		// Wait for the finalization of the threads using the join() method.
		for (int i = 0; i < applicationThread.length; i++) {
			applicationThread[i].join();
		}

		/*
		 * Write to the console the events stored in the queue. While the size
		 * of the queue is bigger than zero, use the poll() method to obtain an
		 * Event class. If it returns null, put the main thread for 500
		 * milliseconds to wait for the activation of more events.
		 */
		int totalApplications = 0;
		do {
			int counter = 0;
			Application application;
			do {
				application = queue.poll();
				if (application != null)
					counter++;
			} while (application != null);
			if (counter != 0) {
				System.out.println("---An application is" +
						" evaluated and is ready---");
				totalApplications++;
			}
			System.out.printf("At %s %d applications are evaluated" +
					" and are ready \n",
					new Date(), totalApplications);
			TimeUnit.MILLISECONDS.sleep(500);
		} while (queue.size() > 0);

	}

}
