package chapter1_Thread_Management;

public class TaylorTrigSeries implements Runnable {

	private double degrees;

	public TaylorTrigSeries(double degrees) {
		this.degrees = degrees;
	}

	public void run() {

		double TanResult = TaylorSine(this.degrees) /
		TaylorCosine(this.degrees);
		
		System.out.println("The tangent of " + degrees + " degrees is "
				+ TanResult);

	}

	public double TaylorSine(double degrees) {
		double sum = 0;
		for (int counter = 0; counter < 100; counter++) {
			sum += ((Math.pow(-1.0, counter) *
					Math.pow(degrees2rad(degrees),
					(2 * counter + 1))) /
					(factorial(2 * counter + 1)));
		}
		return sum;
	}

	public double TaylorCosine(double degrees) {
		double sum = 0;
		for (int counter = 0; counter < 100; counter++) {
			sum += (Math.pow(-1.0, counter) *
					Math.pow(degrees2rad(degrees),
					2.0 * counter) /
					(factorial(2.0 * counter)));
		}
		return sum;
	}

	public double factorial(double n) {
		if (n <= 1)
			return 1;
		else
			return n * factorial(n - 1);
	}

	public double degrees2rad(double degrees) {
		return (degrees * Math.PI / 180.0);
	}

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();

		Thread TrigThread = new Thread(new TaylorTrigSeries(45));
		TrigThread.start();

		// wait for the finalization of this heavy operations
		try {
			TrigThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("\nheavy mathematical" +
				" operations are over\n"
				+ "time elapsed " + estimatedTime / 1000.0 
				+ " seconds");
	}
}