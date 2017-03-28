package chapter6_Concurrent_Collections;

import java.util.concurrent.LinkedBlockingDeque;

public class MainProducerConsumerBlocking {

	public static void main(String[] args) {

		LinkedBlockingDeque<Item> deque = new LinkedBlockingDeque<Item>(10);

		Thread producerThread = new Thread(new ProducerBlocking(deque));
		Thread consumerThread = new Thread(new ConsumerBlocking(deque));
		producerThread.start();
		consumerThread.start();
		
	} //end of main
}
