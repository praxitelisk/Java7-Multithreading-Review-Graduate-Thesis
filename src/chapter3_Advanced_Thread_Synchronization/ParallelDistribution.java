package chapter3_Advanced_Thread_Synchronization;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
import java.util.Date;

public class ParallelDistribution {
	private static final int MAX_THREADS = 4;

	private static class WorkerThread implements Runnable {

		private CyclicBarrier cyclicBarrier;
		private String name;
		private double workingTime;

		//initializing a worker thread to work in parallel with other workers
		public WorkerThread(CyclicBarrier cyclicBarrier, String name,
				double workingTime) {
			this.name = name;
			this.cyclicBarrier = cyclicBarrier;
			this.workingTime = workingTime;
		}

		public void run() {
			try {
				Date startTime = new Date();

				// start executing on your part of a heavy-weight operation
				System.out.println(startTime + " : Thread#" + name
						+ " Executing " 
						+ (Integer.parseInt(name) + 1)
						+ "/" + MAX_THREADS 
						+ " Part of Work");
				
				// processing of Thread's work
				Thread.sleep((long) workingTime * 1000);

				// Thread's work is finished
				System.out.println(new Date() + " : Thread#" + name
						+ " Finished Part of " 
						+ (Integer.parseInt(name) + 1) 
						+ "/" + MAX_THREADS 
						+ " Part of Work");

				//wait for each other thread to finish its work
				cyclicBarrier.await();

			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	//main method
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(MAX_THREADS,
				new Runnable() {

					//this thread executes when all the workers-threads
					//have finished their work
					public void run() {
						System.out.printf("MainThread Report: ");
						System.out.printf("Cyclic Barrier Finished " +
								"processing a heavy-weight " +
								"operation");
					}
				});

		System.out.println("Distributing a Heavy-Weight Job in " 
				+ MAX_THREADS+ " Threads");

		// total process time, if only 1 thread was going to
		// execute a heavy-weight operation
		double totalTime = (double) (Math.random() + 1) * 25;

		System.out.println("Spawning Threads");
		for (int i = 0; i < MAX_THREADS; i++) {
			Thread t = new Thread(new WorkerThread(cyclicBarrier, ("" + i),
					totalTime / MAX_THREADS));
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} //end of try-catch
			
			t.start();
		}
	} //end of main method
}