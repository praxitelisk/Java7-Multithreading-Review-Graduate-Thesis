package chapter4_Thread_Executors;

import java.util.concurrent.CompletionService;

public class NeedlePiTaskRequest implements Runnable {

	private String name;
	private CompletionService<Double> service;

	// Implement the constructor of the class that initializes the two
	// attributes.
	public NeedlePiTaskRequest(String name, CompletionService<Double> service) {
		this.name = name;
		this.service = service;
	}

	@Override
	public void run() {
		/*
		 * Implement the run() method. Create a needlePiGenerator object and
		 * send them to the CompletionService object using the submit() method.
		 */
		TaskNeedlePiGenerator taskNeedlePiGenerator = new TaskNeedlePiGenerator(name,
				"Report");
		service.submit(taskNeedlePiGenerator);
	}

}
