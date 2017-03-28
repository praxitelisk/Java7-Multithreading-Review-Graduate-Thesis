package chapter4_Thread_Executors;

import java.util.Date;
import java.util.Random;

/*
 * Computing the pi with monte carlo simulation
 * http://www.eveandersson.com/pi/monte-carlo-circle
 */

public class TaskMonteCarloPi implements Runnable {

	private String name;

	public TaskMonteCarloPi(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		double x, y;
		int range = 2;
		int lowerBound = -1;
		Random random = new Random(System.currentTimeMillis());
		int hits = 0, limit = 100000;

		for (int i = 0; i < limit; i++) {
			
			// get a random point and its x,y coordinates
			x = random.nextDouble() * range + lowerBound;
			y = random.nextDouble() * range + lowerBound;

			// calculate the distance between this point and the circle's center
			// if its in the circle increment hits
			if (Math.pow(x, 2) + Math.pow(y, 2) < 1)
				hits++;
		}
		
		System.out.printf("%s: Starting at : %s\n", name, new Date());
		System.out
				.printf("Monte Carlo computed Pi: %f\n", (4.0 * hits / limit));
		System.out.printf("Monte Carlo original Pi: %f\n", Math.PI);
		System.out.printf("Monte Carlo Pi difference: %f\n\n",
				(4.0 * hits / limit) - Math.PI);
	}
}