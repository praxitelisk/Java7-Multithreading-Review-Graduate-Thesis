package chapter1_Thread_Management;

public class Factorial implements Runnable {

	public void run() {
		long startTime = System.currentTimeMillis();

		double i = 1;
		while (true) {
			double temp = 1;

			for (int j = 1; j <= i; j++) {
				temp *= j;
			}

			System.out.println("the factorial of " + i + " is " + temp);
			i++;

			if (Thread.currentThread().isInterrupted()) {
				System.out.println("The Factorial Procedure is"
						+ " Interrupted");

				long estimatedTime = System.currentTimeMillis() - startTime;
				System.out.println("time elapsed " + estimatedTime
						+ " milliseconds");

				return;
			}
		}
	}

	public static void main(String args[]) {

		Thread fact1 = new Thread(new Factorial());
		fact1.start();

		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		fact1.interrupt();
	}
}