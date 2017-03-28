package chapter4_Thread_Executors;

public class MainFactorialManager {

	public static void main(String[] args) {
		FactorialManagerFixedPool factorialManagerFixedPool = new FactorialManagerFixedPool();
		
		//register to the executor 4 threads to run
		for (int i = 0; i < 5; i++) {
			TaskFactorial taskFactorial = new TaskFactorial("Task " + i);
			factorialManagerFixedPool.executeTasks(taskFactorial);
		}
		
		//stop executor
		factorialManagerFixedPool.endServer();
	}
}
