package chapter1_Thread_Management;

public class DaemonFibonacci extends Thread {

	public DaemonFibonacci() {
		//setDaemon(true);
	}

	public void run() {
		int prev = 0;
		int curr = 1;
		int temp;
		int counter = 0;
		
		System.out.println("Fibonacci Sequence Service has started");

		while (true) {
			//print the 1st Fibonacci number
			if (counter == 0) {
				System.out.println("The Daemon Service "
						+ "got you a new fibonacci number " + prev);
				counter++;
				
				//print the 2nd Fibonacci number	
			} else if (counter == 1) {
				System.out.println("The Daemon Service "
						+ "got you a new fibonacci number " + curr);
				counter++;
				
				//print the rest Fibonacci numbers	
			} else {

				System.out.println("The Daemon Service "
						+ "got you a new fibonacci number "
						+ (prev + curr));

				temp = curr;
				curr = prev + curr;
				prev = temp;
			}
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		Thread FibonService = new Thread(new DaemonFibonacci());
		FibonService.start();

	}
}
