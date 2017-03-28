package chapter6_Concurrent_Collections;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducerBlocking implements Runnable {

	private LinkedBlockingDeque<Item> deque;

	public ProducerBlocking(LinkedBlockingDeque<Item> deque) {
		this.deque = deque;
	}

	@Override
	public void run() {

		Random random = new Random(System.currentTimeMillis());
		String itemName;
		int value;

		try {
			for (int i = 0; i < 8; i++) {
				
				//add a new Item - Product
				itemName = "Item" + random.nextInt(20);
				value = random.nextInt(200);
				
				//then insert it to the list
				deque.put(new Item(itemName, value));
				
				System.out.println("The Producer adds a new item:" + itemName
						+ " with value: " + value);
				Thread.currentThread().sleep(250);
			}
			
			//the producer after some time closes the store
			Thread.currentThread().sleep(10000);
			System.out.println("The Produer closes the Store");
			System.exit(0);
			
			
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

	}

}
