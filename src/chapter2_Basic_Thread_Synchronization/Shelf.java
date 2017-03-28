package chapter2_Basic_Thread_Synchronization;

import java.util.LinkedList;
import java.util.Random;

public class Shelf {

	private String productName;
	private int maxSize;
	private LinkedList<Integer> storage;

	public Shelf(int maxSize, String name) {
		this.productName = name;
		this.maxSize = maxSize;
		storage = new LinkedList<Integer>();
	}

	public synchronized void ShelfRefresh() {
		
		while (storage.size() == maxSize) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("The Producer closes his store");
				System.exit(0);
			}
		}
		
		int amount = new Random().nextInt(5)+5;
		int counter=0;
		while(storage.size()<maxSize && counter<amount) {
			int item = storage.size() + 1;
			storage.offer(item);
			counter++;
		}
		
		System.out.println("The Producer updates the " 
				+ productName + "'s "
				+ "shelf with new items" 
				+ " #of items: " + storage.size());
		
		notifyAll();
	}

	public synchronized void buy() {

		int amount = new Random().nextInt(5);
		
		if (amount == 0)
			System.out.println("A Customer just browse and buys nothing");
		else {
			System.out.println("A Customer wants to buy " 
					+ amount + " items");

			while (storage.size() < amount) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i < amount; i++) {
				storage.poll();

				System.out.println("A Consumer buys a " 
						+ productName
						+ ", items left " 
						+ storage.size());
				
				notifyAll();
			}
		}
	}
}
