package chapter2_Basic_Thread_Synchronization;

public class Consumer implements Runnable {

	private Shelf shelf;

	public Consumer(Shelf shelf) {
		this.shelf = shelf;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			shelf.buy();
		}
		System.out.println("It seems no more Consumers will " +
				"pay the store a visit for today");
	}
}
