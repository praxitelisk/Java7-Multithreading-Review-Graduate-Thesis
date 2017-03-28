package chapter3_Advanced_Thread_Synchronization;

import java.util.concurrent.TimeUnit;

public class Service implements Runnable {

	private String serviceName;
	private Server server;

	public Service(String serviceName, Server server) {
		this.serviceName = serviceName;
		this.server = server;
	}

	public void run() {
		
		//do your computations - calculations
		long duration = (long) (Math.random() * 10);
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//when you are ready call this method
		//to decrement the latch
		server.ReadyService(serviceName);
	}
}
