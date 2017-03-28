package chapter6_Concurrent_Collections;

import java.util.concurrent.LinkedBlockingDeque;

public class ConsumerBlocking implements Runnable {

	private LinkedBlockingDeque<Item> deque;

	public ConsumerBlocking(LinkedBlockingDeque<Item> deque) {
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
		try {
			while ((item = deque.take()) != null) {
				generateOrder(item);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void generateOrder(Item item) {
		System.out.println("A Consumer bought an item "
				+ item.getDescription() + " with value: " + item.getItemId());
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

}
