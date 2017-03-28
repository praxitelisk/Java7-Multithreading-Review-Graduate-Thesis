package chapter3_Advanced_Thread_Synchronization;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Shelf {

	private Semaphore ShelfSemaphore;
	private String productName;
	private int maxSize;
	private LinkedList<Integer> storage;

	public Shelf(int maxSize, String name) {
		this.productName = name;
		this.maxSize = maxSize;
		storage = new LinkedList<Integer>();
		ShelfSemaphore = new Semaphore(1,true);
	}

	public void ShelfRefresh() {

		try {
			ShelfSemaphore.acquire();
			if (storage.size() == this.maxSize)
				System.out.println("Shelf is full of " 
						+ productName + "s");
			else {
				int amount = new Random().nextInt(5)+5;
				int counter=0;
				while(storage.size()<maxSize && counter<amount) {
					int item = storage.size() + 1;
					storage.offer(item);
					counter++;
				}
				System.out.println("The Producer updates the " 
						+ productName
						+ "'s " + "shelf with new items" 
						+ " #of items: "
						+ storage.size());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			ShelfSemaphore.release();
		}
	}

	public void buy() {

		try {
			ShelfSemaphore.acquire();
			if (storage.size() == 0)
				System.out.println(productName 
						+ "'s are out of stock");
			
			else if (storage.size() > 0) {
				int amount = new Random().nextInt(5);
				
				if (amount == 0)
					System.out.println("A Customer just " +
							"browse and buys nothing");
				
				else if (amount > storage.size())
					System.out.println("less than " + amount
							+ " products in shelf");
				else {
					System.out.println("A Customer wants to buy " 
							+ amount
							+ " items");

					for (int i = 0; i < amount; i++) {
						storage.poll();

						System.out.println("A Consumer buys a " 
								+ productName
								+ ", items left " 
								+ storage.size());
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			ShelfSemaphore.release();
		}
	}
}