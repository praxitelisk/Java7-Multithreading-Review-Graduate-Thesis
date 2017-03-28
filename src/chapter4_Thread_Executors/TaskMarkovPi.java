package chapter4_Thread_Executors;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;

/*
 * A class that simulates the Markov Chain Monte Carlo
 * pi calculation
 * for more information
 * http://math.stackexchange.com/questions/210653/confusion-related-to-the-calculation-of-value-of-pi
 */
public class TaskMarkovPi implements Callable<String> {

	private String name;

	public TaskMarkovPi(String name) {
		this.name = name;
	}

	@Override
	public String call() throws Exception {

		while (true) {
			// get a random point's coordinates
			double x = 1.0;
			double y = 1.0;
			double delta_x, delta_y;

			// the step to take its time to a new point
			double delta = 0.3;
			double range = delta * 2;

			Random random = new Random(System.currentTimeMillis());
			double hits = 0;
			int limit = 100000;

			for (int i = 0; i < limit; i++) {
				// get a new displacement for the x,y coordinates of the initial
				// point
				delta_x = random.nextDouble() * range - delta;
				delta_y = random.nextDouble() * range - delta;

				// if the new point is in the boundaries then its acceptable
				if ((Math.abs(x + delta_x) < 1) & (Math.abs(y + delta_y) < 1)) {
					x = x + delta_x;
					y = y + delta_y;
				}

				// if the distance between this point and the center of the
				// circle O(0,0)
				// is smaller than the radius then this point is in the circle,
				// then
				// increment the hits variable
				if (Math.pow(x, 2) + Math.pow(y, 2) < 1)
					hits++;
			}
			System.out.printf("%s: Starting at : %s\n", name, new Date());
			System.out.printf("Markov computed Pi: %f\n", (4.0 * hits / limit));
			System.out.printf("Markov original Pi: %f\n", Math.PI);
			System.out.printf("Markov Pi difference: %f\n",
					(4.0 * hits / limit) - Math.PI);
			System.out.println("_____________________________________");
			System.out.println("Task " + this.getClass().getSimpleName()
					+ " is waiting for 1.5 seconds\n");
			Thread.sleep(1500);

		}
	}

}
