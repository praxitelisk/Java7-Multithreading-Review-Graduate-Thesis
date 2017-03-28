package chapter4_Thread_Executors;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TaskFactorial implements Runnable {

	private Date initDate;
	private String name;

	//thread constructor
	public TaskFactorial(String name) {
		initDate = new Date();
		this.name = name;
	}

	public void run() {
		System.out.printf("%s: Task %s: Created on: %s\n", Thread
				.currentThread().getName(), name, initDate);
		System.out.printf("%s: Task %s: Started on: %s\n", Thread
				.currentThread().getName(), name, new Date());
		try {
			Long number = Math.round(Math.random()*10);
			
			Long duration = (long) (Math.random() * 10);
			TimeUnit.SECONDS.sleep(duration);
			
			System.out.printf("%s: Task %s: has calculated " +
					"the factorial of the number %d which is %d\n",
					Thread.currentThread().getName(), name, number, factorial(number));
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: Task %s: Finished on: %s\n", Thread
				.currentThread().getName(), name, new Date());

	}
	
	public long factorial(long n) {
		if (n <= 1)
			return 1;
		else
			return n * factorial(n - 1);
	}
}
