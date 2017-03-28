package chapter3_Advanced_Thread_Synchronization;

import java.util.ArrayList;
import java.util.concurrent.Exchanger;

public class CosTaylorExchanger implements Runnable {
	
	private int[] degrees = { 30, 45, 60 };
	private double[] cosArray = new double[degrees.length];
	
	private ArrayList<Double> buffer;
	private final Exchanger<ArrayList<Double>> exchanger;

	//constructor
	public CosTaylorExchanger(ArrayList<Double> buffer,
			Exchanger<ArrayList<Double>> exchanger) {
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	public void run() {
		System.out.println("-"
				+Thread.currentThread().getName()
				+" starts Cosine Taylor Series Computations-");
		
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//compute your cosine part,
		for (int i = 0; i < degrees.length; i++) {
			cosArray[i] = TaylorCosine(degrees[i]);
			
			//add your cosine computations to your buffer
			buffer.add(cosArray[i]);
			
			System.out.println(Thread.currentThread().getName()+
					" degrees:" + degrees[i] + 
					" computes cos:" + cosArray[i]);
		}

		//exchange your cosine buffer to the sine Thread
		//and get the sine buffer results
		try {
			Thread.sleep(20);
			System.out.println("-"+Thread.currentThread().getName()
					+" starts to exchange his data-");
			
			
			buffer = exchanger.exchange(buffer);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//compute the rest of the Trigonometric identities
		for (int i = 0; i < degrees.length; i++) {
			double sin = buffer.get(i);
			
			System.out.println(Thread.currentThread().getName()
					+" degrees: " + degrees[i] + " got sin:" + sin 
					+", so tan is:" + (sin/cosArray[i]));
		}
	}

	public double TaylorCosine(int degrees) {
		double sum = 0;
		for (int counter = 0; counter < 100; counter++) {
			sum += (Math.pow(-1.0, counter)
					* Math.pow(degrees2rad(degrees), 2.0 * counter) / (factorial(2 * counter)));
		}
		return sum;
	}

	public double factorial(int n) {
		if (n <= 1)
			return 1;
		else
			return n * factorial(n - 1);
	}

	public double degrees2rad(double degrees) {
		return (degrees * Math.PI / 180.0);
	}
}