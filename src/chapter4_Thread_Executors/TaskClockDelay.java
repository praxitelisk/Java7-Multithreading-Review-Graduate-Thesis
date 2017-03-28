package chapter4_Thread_Executors;

import java.util.Date;
import java.util.concurrent.Callable;

public class TaskClockDelay implements Callable<String> {

	@Override
	public String call() throws Exception {
		System.out.printf("%s\n", new Date());
		return "";
	}
}