package chapter5_Fork_Join;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * This class separates a list into simply abstract integer structures, then
 * check if each integer element is prime and print a result
 **/

public class TaskListSeparator extends RecursiveAction {

	private int[] list;
	public int result;

	// constructor
	public TaskListSeparator(int[] array) {
		this.list = array;
	}

	@Override
	protected void compute() {
		// if the list is fully separated into abstract integer elements
		// then each thread in the pool print the given thread
		if (list.length == 1) {
			result = list[0];
			System.out.println("I am thread " + Thread.currentThread()
					+ " and I have got the number " + result
					+ " is this a prime number? " + isPrime(result));

			// sleep for 2 seconds
			try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		/*
		 * create two new TaskListSeparator objects, one to process the first
		 * half of the list and the other to process the second half and execute
		 * them in ForkJoinPool using the invokeAll() method.
		 */
		else {
			int midpoint = list.length / 2;
			int[] l1 = Arrays.copyOfRange(list, 0, midpoint);
			int[] l2 = Arrays.copyOfRange(list, midpoint, list.length);
			TaskListSeparator s1 = new TaskListSeparator(l1);
			TaskListSeparator s2 = new TaskListSeparator(l2);
			invokeAll(s1, s2);
		}
	}

	public boolean isPrime(int input) {

		// Check multiples of two
		if (input % 2 == 0) {
			if (input != 2)
				return false;
			else
				return true;
		}

		// otherwise
		for (int i = 3; i <= input - 1; i++) {
			if (input % i == 0) {
				return false;
			}
		}

		return true;

	}
}
