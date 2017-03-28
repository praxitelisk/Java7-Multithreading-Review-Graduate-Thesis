package chapter1_Thread_Management;

/************************************************************************* 
 *  Authors: Praxitelis Nikolaos Kouroupetorglou, praxitelisk@gmail.com
 *  Supervisor: Konstantinos Margaritis, kmarg@uom.gr
 *  Resources Used: Java 7.0
 *  Comments 
 *  ---------- 
 *  This class simulates a needle in the Buffon needle experiment.
 *
 *  Known bugs 
 *  ----------
 *************************************************************************/ 

public class HelloThread extends Thread {

	public void run() {
		while (true) {
			System.out.println("Hello world from thread "
					+ Thread.currentThread().getName() + " with Id "
					+ Thread.currentThread().getId());
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		System.out.println("Hello from Thread "
				+ Thread.currentThread().getName() + " with Id "
				+ Thread.currentThread().getId());

		Thread thread1 = new HelloThread();
		thread1.start();
	}
}
