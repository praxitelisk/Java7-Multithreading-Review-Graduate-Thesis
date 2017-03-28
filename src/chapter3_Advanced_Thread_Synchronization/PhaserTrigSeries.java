package chapter3_Advanced_Thread_Synchronization;

import java.util.concurrent.Phaser;

public class PhaserTrigSeries {

	public static double TaylorSine(int degrees) {
		double sum = 0;
		for (int counter = 0; counter < 100; counter++) {
			sum += ((Math.pow(-1.0, counter) 
					* Math.pow(degrees2rad(degrees),
							(2 * counter + 1))) 
							/ (factorial(2 * counter + 1)));
		}
		return sum;
	}

	public static double TaylorCosine(int degrees) {
		double sum = 0;
		for (int counter = 0; counter < 100; counter++) {
			sum += (Math.pow(-1.0, counter)
					* Math.pow(degrees2rad(degrees), 
							2.0 * counter) 
							/ (factorial(2 * counter)));
		}
		return sum;
	}

	public static double factorial(int n) {
		if (n <= 1)
			return 1;
		else
			return n * factorial(n - 1);
	}

	public static double degrees2rad(double degrees) {
		return (degrees * Math.PI / 180.0);
	}

	public static void main(String[] args) {

		final int[] degreesArray = { 30, 45, 60 };
		final double[] sinArray = new double[3];
		final double[] cscArray = new double[3];
		final double[] cosArray = new double[3];
		final double[] secArray = new double[3];
		final double[] tanArray = new double[3];
		final double[] cotArray = new double[3];

		final int workStep = degreesArray.length;
		int workers = 3;
		
		//create a phaser with 3 workers-threads
		final Phaser phaser = new Phaser(workers);

		new Thread("Worker1") {
			//Thread - Worker to calculate 
			//sine(sin) & cosine(cos)

			public void run() {
				for (int i = 0; i < workStep; i++) {

					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					sinArray[i] = TaylorSine(degreesArray[i]);
					cosArray[i] = TaylorCosine(degreesArray[i]);
					System.out.println("Worker1, Phase: " 
							+ (phaser.getPhase()+1)
							+ " degrees:" + degreesArray[i] + " sin:"
							+ sinArray[i] + " cos:" + cosArray[i]);
					
					//wait for the all the other threads to finish
					//their computations, in order to proceed to the
					//next phase - iteration
					phaser.arriveAndAwaitAdvance();
				}
			}
		}.start();

		new Thread("Worker2") {
			//Thread - Worker to calculate 
			//cosecant(csc) & secant(sec)

			public void run() {
				for (int i = 0; i < workStep; i++) {

					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					cscArray[i] = 1 / sinArray[i];
					secArray[i] = 1 / cosArray[i];

					System.out.println("Worker2, Phase: " 
							+ (phaser.getPhase()+1) 
							+ " degrees:" + degreesArray[i] + " csc:"
							+ cscArray[i] + " sec:" + secArray[i]);
					
					//wait for the all the other threads to finish
					//their computations, in order to proceed to the
					//next phase - iteration
					phaser.arriveAndAwaitAdvance();

				}
			}
		}.start();

		new Thread("Worker3") {
			//Thread - Worker to calculate 
			//tangent(tan) & cotangent(cot)

			public void run() {
				for (int i = 0; i < workStep; i++) {

					try {
						Thread.sleep(60);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					tanArray[i] = sinArray[i]
							/ cosArray[i];
					cotArray[i] = cosArray[i]
							/ sinArray[i];

					System.out.println("Worker3, Phase: " 
							+ (phaser.getPhase()+1) 
							+ " degrees:" + degreesArray[i] + " tan:"
							+ tanArray[i] + " cot:" + cotArray[i]);

					//wait for the all the other threads to finish
					//their computations, in order to proceed to the
					//next phase - iteration
					phaser.arriveAndAwaitAdvance();
				}
			}
		}.start();
	}// end of main
}// end of class