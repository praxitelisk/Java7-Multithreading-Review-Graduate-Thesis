package chapter2_Basic_Thread_Synchronization;

public class Producer implements Runnable {

	private Shelf shelf;

	public Producer(Shelf shelf) {
		this.shelf = shelf;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			shelf.ShelfRefresh();
		}
	}
}