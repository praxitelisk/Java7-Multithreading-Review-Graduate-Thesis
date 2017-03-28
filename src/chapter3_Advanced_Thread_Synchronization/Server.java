package chapter3_Advanced_Thread_Synchronization;

import java.util.concurrent.CountDownLatch;

public class Server implements Runnable {
	
	private final CountDownLatch latch;
	
	public Server() {
		//wait for 3 services to finish
		this.latch = new CountDownLatch(3);
	}
	
	public void ReadyService(String name) {
		System.out.println("Service "+ name +" is ready");
		//Service's operation is finished
		latch.countDown();
	}
	
	public void run() {
		long startTime = System.currentTimeMillis();
		
		System.out.println("Server initialization, awaiting for #"
				+latch.getCount() +" services to finish");
		try {
			//waiting for 3 services to finish
			latch.await();
			
			//when all the other threads have finished their exexutions
			//start this thread execution
			System.out.println("Server: all services are ready");
			
			System.out.println("Server's setting some last parameters");
			Thread.currentThread().sleep(3000);
			
			System.out.printf("Server is UP! ");
			
			long estimatedTime = System.currentTimeMillis() - startTime;
			System.out.println("time elapsed " + estimatedTime / 1000.0 
					+ " seconds");
			
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		//server
		Server server = new Server();
		Thread serverThread = new Thread(server);	
		
		//services
		Service webService = new Service("Web Service",server);
		Service messageService = new Service("MessageService Service",server);
		Service dataBaseService = new Service("Data Base Service",server);
		
		Thread webServiceThread = new Thread(webService);
		Thread messageServiceThread = new Thread(messageService);
		Thread dataBaseServiceThread = new Thread(dataBaseService);
		
		serverThread.start();
		webServiceThread.start();
		messageServiceThread.start();
		dataBaseServiceThread.start();
	}
}
