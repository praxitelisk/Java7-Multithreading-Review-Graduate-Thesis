package chapter3_Advanced_Thread_Synchronization;

import java.util.ArrayList;
import java.util.concurrent.Exchanger;

public class SinTaylorExchanger implements Runnable {

	private int[] degrees = { 30, 45, 60 };
	private double[] sinArray = new double[degrees.length];
	
	private ArrayList<Double> buffer;
	private final Exchanger<ArrayList<Double>> exchanger;

	//constructor
	public SinTaylorExchanger(ArrayList<Double> buffer,
			Exchanger<ArrayList<Double>> exchanger) {
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	public void run() {
		System.out.println("-"
				+Thread.currentThread().getName()
				+" starts Sine Taylor Series Computations-");
		
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//compute your sine part,
		for (int i = 0; i < degrees.length; i++) {
			sinArray[i] = TaylorSine(degrees[i]);
			
			//add your sine computations to your buffer
			buffer.add(sinArray[i]);
			
			System.out.println(Thread.currentThread().getName()
					+" degrees:" + degrees[i] +
					" computes sin:" + sinArray[i]);
		}
		
		//exchange your sine buffer to the cosine Thread
		//and get the cosine buffer results
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
			double cos = buffer.get(i);
			
			System.out.println(Thread.currentThread().getName()
					+" degrees: "+degrees[i]+" got cos:"+cos
					+", so tan is:" + (sinArray[i]/cos));
		}
	}

	public double TaylorSine(int degrees) {
		double sum = 0;
		for (int counter = 0; counter < 100; counter++) {
			sum += ((Math.pow(-1.0, counter) * Math.pow(degrees2rad(degrees),
					(2 * counter + 1))) / (factorial(2 * counter + 1)));
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