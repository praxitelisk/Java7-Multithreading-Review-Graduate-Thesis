package chapter4_Thread_Executors;

import java.util.Date;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class NeedlePiPostProcessor implements Runnable {
	// This class will get the results of the NeedlePiGenerator tasks.

	private CompletionService<Double> service;
	private boolean end;

	private double threshold = 1e-2;
	private double best_approx_pi = 0;

	public NeedlePiPostProcessor(CompletionService<Double> service) {
		this.service = service;
		end = false;
	}

	@Override
	/*
	 * While the attribute end is false, call the poll() method of the
	 * CompletionService interface to get the Future object of the next task
	 * executed by the completion service that has finished.
	 */
	public void run() {
		int awaitTime = 20;
		System.out
				.println("The post processor task will wait " +
						"for tasks to finish for "
						+ awaitTime + " seconds");
		while (!end) {
			try {
				Future<Double> result = service.poll(awaitTime,
						TimeUnit.SECONDS);

				if (result != null) {
					/*
					 * get the results of the task using 
					 * the get() method of the
					 * Future object and write those results to the console.
					 */
					double report = result.get();

					System.out.printf(
							"PostProcessor: " +
							"%s A new Needle Pi Received: %s\n",
							new Date(), report);

					if (Math.abs((Math.PI - report)) <= threshold)
						best_approx_pi = report;

				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Best Pi approximation: %.10f\n", best_approx_pi);
		System.out.printf("PostProcessor: End\n");
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

}
