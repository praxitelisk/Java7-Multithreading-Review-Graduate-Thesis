package chapter6_Concurrent_Collections;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class MainIncrementerDecrementer {

	public static void main(String[] args) {

		/*
		 * Declare a constant named THREADS and assign to it the value 100.
		 */
		final int THREADS = 10;

		System.out.println("Incrementing and decremeting an atomic array "
				+ THREADS + " times\n");
		waitSomeTime();

		// Create an AtomicIntegerArray object with 10 elements.
		AtomicIntegerArray vector = new AtomicIntegerArray(THREADS);

		// Create an Incrementer task to work with the atomic array
		AtomicArrayIncrementer atomicArrayIncrementer = new AtomicArrayIncrementer(
				vector);

		// Create a Decrementer task to work with the atomic array
		AtomicArrayDecrementer atomicArrayDecrementer = new AtomicArrayDecrementer(
				vector);

		// Create two arrays to store 10 Thread objects.
		Thread threadIncrementer[] = new Thread[THREADS];
		Thread threadDecrementer[] = new Thread[THREADS];

		/*
		 * Create and launch 100 threads to execute the Incrementer task
		 */
		for (int i = 0; i < THREADS; i++) {
			threadIncrementer[i] = new Thread(atomicArrayIncrementer);
			threadIncrementer[i].start();
		}

		// Wait for the finalization of the threads using the join() method.
		for (int i = 0; i < THREADS; i++) {
			try {
				threadIncrementer[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// print the new incremented atomic vector
		for (int i = 0; i < vector.length(); i++) {
			System.out.println("Vector[" + i + "] : " + vector.get(i));
		}

		System.out.println("\nIncrement Thread tasks have stopped" +
				", decrement Thread tasks "
				+ THREADS + " times \nto a atomic array" +
						" will start\n");
		System.out.println("Waiting for the decrement Threads");
		waitSomeTime();

		/*
		 * Create and launch 100 threads to execute the Decrement task
		 */
		for (int i = 0; i < THREADS; i++) {
			threadDecrementer[i] = new Thread(atomicArrayDecrementer);
			threadDecrementer[i].start();
		}

		// Wait for the finalization of the threads using the join() method.
		for (int i = 0; i < THREADS; i++) {
			try {
				threadDecrementer[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// print the new incremented atomic vector
		for (int i = 0; i < vector.length(); i++) {
			System.out.println("Vector[" + i + "] : " + vector.get(i));
		}

		System.out.println("\n----Main: End of the example----");

	} // end of main

	// wait time for the Main thread
	private static void waitSomeTime() {
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
