package chapter5_Fork_Join;

import java.util.concurrent.RecursiveAction;

public class TaskSquareCalculator extends RecursiveAction {

	final int CRITICAL_LIMIT = 3;
	int result;
	int start, end;
	int[] data;

	TaskSquareCalculator(int[] data, int start, int end) {
		this.start = start;
		this.end = end;
		this.data = data;
	}

	@Override
	protected void compute() {
		if ((end - start) < CRITICAL_LIMIT) {
			for (int i = start; i < end; i++) {
				result = data[i] * data[i];
				try {
					System.out.println("I am Thread: "
							+ Thread.currentThread().getName()
							+ " and the Power of " + data[i] 
							+ " is " + result);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} else {
			int mid = (start + end) / 2;
			TaskSquareCalculator leftSubTask = new TaskSquareCalculator(
					data, start,mid);
			TaskSquareCalculator rightSubTask = new TaskSquareCalculator(
					data, mid,end);
			leftSubTask.fork();
			rightSubTask.fork();
			leftSubTask.join();
			rightSubTask.join();

		}
	}
}
