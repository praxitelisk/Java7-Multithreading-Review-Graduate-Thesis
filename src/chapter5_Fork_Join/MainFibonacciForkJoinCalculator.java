package chapter5_Fork_Join;

import java.util.concurrent.ForkJoinPool;

public class MainFibonacciForkJoinCalculator {
	public static void main(String[] args) {

		// creating a ForkJoinPool with threads equal to the number
		// of cpu cores (for speed improvement)
		int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
		ForkJoinPool pool = new ForkJoinPool(AVAILABLE_PROCESSORS);

		// generate some fibonacci numbers
		int num_of_fibonacci_numbers = 10;

		for (int i = 1; i <= num_of_fibonacci_numbers; i++) {
			
			TaskFibonacciCalculator fibonacciCal = 
				new TaskFibonacciCalculator(i);

			int fibonacciResult = 0;
			try {
				// execute the RecursiveTask inside the ForkJoinPool
				fibonacciResult = pool.invoke(fibonacciCal);
				System.out.println("The #" + i + " fibonacci number is "
						+ fibonacciResult);
				
				//then sleep the main thread for 1 second
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		//shutting down the ForkJoin pool
		pool.shutdown();
		System.out.println("----Main: The ForkJoinPool"
				+ " has shutted down----");
	} // end of main method
} // end of main class
