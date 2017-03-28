package chapter3_Advanced_Thread_Synchronization;

public class MainProducerConsumer {

	public static void main(String[] args) {

		Shelf shelf = new Shelf(30, "mango");

		Producer producer = new Producer(shelf);
		Thread thread1 = new Thread(producer);

		Consumer consumer = new Consumer(shelf);
		Thread thread2 = new Thread(consumer);

		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join();
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Store is closing");
	}
}