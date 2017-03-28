package chapter6_Concurrent_Collections;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ProducerNonBlocking implements Runnable {

	private ConcurrentLinkedDeque<Item> deque;

	public ProducerNonBlocking(ConcurrentLinkedDeque<Item> deque) {
		this.deque = deque;
	}

	@Override
	public void run() {
		Random random = new Random(System.currentTimeMillis());
		String itemName;
		int value;
		try {
			for (int i = 1; i < 8; i++) {
				itemName = "Item" + random.nextInt(20);
				value = random.nextInt(200);
				deque.add(new Item(itemName, value));
				System.out.println("The Producer adds a new item:" + itemName 
						+ " with value: " + value);
				Thread.currentThread().sleep(250);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
