package chapter6_Concurrent_Collections;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicArrayIncrementer implements Runnable {

	/*
	 * Declare a private AtomicIntegerArray attribute named vector to store an
	 * array of integer numbers.
	 */
	private AtomicIntegerArray vector;

	/*
	 * Implement the constructor of the class to initialize its attribute.
	 */
	public AtomicArrayIncrementer(AtomicIntegerArray vector) {
		this.vector = vector;
	}

	/*
	 * Implement the run() method. Increment all the elements of the array using
	 * the getAndIncrement() method.
	 */
	@Override
	public void run() {
		for (int i = 0; i < vector.length(); i++) {
			vector.getAndIncrement(i);
		}
	}

}
