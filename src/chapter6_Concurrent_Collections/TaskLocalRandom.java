package chapter6_Concurrent_Collections;

import java.util.concurrent.ThreadLocalRandom;

public class TaskLocalRandom implements Runnable {

	/*
	 * Implement the constructor of the class. Use it to initialize the
	 * random-number generator to the actual thread using the current() method.
	 */
	public TaskLocalRandom() {
		ThreadLocalRandom.current();
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		for (int i = 0; i < 10; i++) {
			System.out.printf(
					"I am Thread %s: and generated a new random: %d\n", name,
					ThreadLocalRandom.current().nextInt(100));
		}
	}

}
