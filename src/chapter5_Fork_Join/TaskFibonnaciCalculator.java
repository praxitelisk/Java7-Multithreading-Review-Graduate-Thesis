package chapter5_Fork_Join;

import java.util.concurrent.RecursiveTask;

class TaskFibonacciCalculator extends RecursiveTask<Integer> {

	/*
	 * origins of this example:
	 * http://www.javac.info/jsr166z/jsr166z/forkjoin/RecursiveTask.html
	 * 
	 */

	int input;

	//constructor method
	TaskFibonacciCalculator(int num) {
		this.input = num;
	}

	@Override
	protected Integer compute() {
		
		if (input == 0) {
			return 0;
		} else if (input <= 2) {
			return 1;
		} else {
			TaskFibonacciCalculator fcal1 = new TaskFibonacciCalculator(
					input - 1);
			fcal1.fork();
			TaskFibonacciCalculator fcal2 = new TaskFibonacciCalculator(
					input - 2);
			return fcal2.compute() + fcal1.join();
		}
	}
}
