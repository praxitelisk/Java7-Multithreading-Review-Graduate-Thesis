package chapter4_Thread_Executors;

import java.util.Date;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainNeedlePi {

	public static void main(String[] args) {

		/*
		 * Create ThreadPoolExecutor using the newCachedThreadPool() method of
		 * the Executors class.
		 */
		ExecutorService executor = (ExecutorService) Executors
				.newCachedThreadPool();

		/*
		 * Create CompletionService using the executor created earlier as a
		 * parameter of the constructor.
		 */
		CompletionService<Double> service = new ExecutorCompletionService<Double>(
				executor);

		/* create needle Pi simulation tasks */
		
		//the number of tasks
		int length = 6;

		//create the needle pi task requests in order to compute the needle pi
		NeedlePiTaskRequest[] NeedleTester = new NeedlePiTaskRequest[length];
		for (int i = 0; i < length; i++) {
			NeedleTester[i] = new NeedlePiTaskRequest("NeedleTester" + i,
					service);
		}

		//create the thread tasks to perform the execution
		Thread[] NeedleTesterThread = new Thread[length];
		for (int i = 0; i < length; i++) {
			NeedleTesterThread[i] = new Thread(NeedleTester[i]);
		}

		// create a task for post process
		NeedlePiPostProcessor PostProcessor = new NeedlePiPostProcessor(service);
		Thread PostProcessorThread = new Thread(PostProcessor);

		// start the task threads
		System.out
				.printf(
						"Main: At %s, Starting the Needle Pi " +
						"simulation tasks Threads\n",
						new Date());

		for (int i = 0; i < length; i++) {
			NeedleTesterThread[i].start();
		}

		PostProcessorThread.start();

		// waiting for the tasks to finish by joining them
		try {
			System.out
					.printf("Main: Waiting for the Needle Pi" +
							" task generators to finish.\n");
			for (int i = 0; i < length; i++) {
				NeedleTesterThread[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// executor shutdown
		int executionTime = 60;
		System.out.printf("Main: Shutting down the " +
				"executor in %d seconds.\n",
				executionTime);

		executor.shutdown();
		try {
			executor.awaitTermination(executionTime, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// send a massage to the post process task to halt its execution
		PostProcessor.setEnd(true);
		System.out.println("Main: Ends");

	}

}
