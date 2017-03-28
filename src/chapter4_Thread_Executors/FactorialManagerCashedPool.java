package chapter4_Thread_Executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class FactorialManagerCashedPool {

	private ThreadPoolExecutor executor;
	private long startTime;

	//executor constructor
	public FactorialManagerCashedPool() {
		executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		startTime = System.currentTimeMillis();
	}

	public void executeTasks(TaskFactorial taskFactorial) {
		System.out.printf("Manager: A new task has arrived\n");

		// start executing its threads that is just registered
		executor.execute(taskFactorial);

		System.out.printf("Manager: Pool Size: %d\n", 
				executor.getPoolSize());

		System.out.printf("Manager: Get Task Count: %d\n", 
				executor.getActiveCount());

		System.out.printf("Manager: Completed Tasks: %d\n", 
				executor.getCompletedTaskCount());
	}

	// finalize the executor thread
	public void endServer() {
		executor.shutdown();

		while (executor.isTerminating()) {/* wait until it shutdowns */}

		System.out.println("\nExecutor shuts down, Completed Tasks:"
				+ executor.getCompletedTaskCount()+" time elapsed in seconds "
				+(System.currentTimeMillis()-startTime)/1000.0);
	}
}
