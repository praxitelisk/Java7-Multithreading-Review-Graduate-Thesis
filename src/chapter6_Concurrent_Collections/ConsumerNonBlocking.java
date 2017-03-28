package chapter6_Concurrent_Collections;

import java.util.concurrent.ConcurrentLinkedDeque;

public class ConsumerNonBlocking implements Runnable {
	
	private ConcurrentLinkedDeque<Item> deque;
	
	public ConsumerNonBlocking(ConcurrentLinkedDeque<Item> deque) {
		this.deque = deque;
	}

	@Override
	public void run() {
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		Item item;
		while ((item = deque.pollFirst()) != null) {
			if (item == null) {
			} else {
				generateOrder(item);
			}
		}
	}

	private void generateOrder(Item item) {
		System.out.print("The Consumer bought an item ");
		System.out.print(item.getDescription());
		System.out.println(" with value: "+item.getItemId());
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
