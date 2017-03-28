package chapter4_Thread_Executors;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class TaskNeedlePiGenerator implements Callable<Double> {

	private String sender;
	private String title;

	// the distance between 2 woods in a parquette floor
	private double parquetteDistance = 1;

	// the niddle length
	private double needleLength = 1;

	private Random myGenerator;
	private int myHits = 0;
	private int myTries = 0;
	private int tries = 10000;
	private double threshold = 1e-2;
	private double best_approx_pi = 0;

	public TaskNeedlePiGenerator(String sender, String title) {
		this.myGenerator = new Random();
		this.sender = sender;
		this.title = title;
	}

	/*
	 * Implement the call() method. First, put the thread to sleep for a random
	 * period of time.
	 */
	@Override
	public Double call() throws Exception {
		Random random = new Random();
		try {
			Long duration = (long) (1 + random.nextInt(8));
			System.out
					.printf(
							"%s_%s: NeedlePiGenerator: Generating" +
							" a report during %d seconds\n",
							this.sender, this.title, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// then generate a needle pi calculation
		return drop();
	}

	/**
	 * Drops a needle between 2 lines distance "parquettedDistance" measures the
	 * distance between the 2 needle's tips measures the distance between the
	 * needle's tip and a parquett line counts whether the needle hits the
	 * parquett line in order to compute pi
	 */
	public double drop() {

		// check the new pi approximation if is better than the previous one
		for (int i = 0; i < tries; i++) {

			// compute a random start point for a needdle
			double x_start_tip = parquetteDistance * myGenerator.nextDouble();

			// compute a random angle between the needle's tips
			double angle = 90 * myGenerator.nextDouble();

			// compute the needle's end tip vertical distance from the other tip
			double x_end_tip_distance = needleLength
					* Math.sin(Math.toRadians(angle));

			// compute the distance of a needle to the parquette's edge
			double distance = parquetteDistance - x_start_tip;

			// if there is a hit then
			if (distance <= x_end_tip_distance)
				myHits++;
			myTries++;

			double new_temp_pi = (2.0 * myTries * needleLength)
					/ (myHits * parquetteDistance);

			if (Math.abs((Math.PI - new_temp_pi)) <= threshold)
				best_approx_pi = new_temp_pi;

		}
		return best_approx_pi;
	}

	/**
	 * Gets the number of times the needle hit a line.
	 */
	public int getHits() {
		return myHits;
	}

	/**
	 * Gets the total number of times the needle was dropped.
	 */
	public int getTries() {
		return myTries;
	}
}
