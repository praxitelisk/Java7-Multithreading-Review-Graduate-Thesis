package chapter6_Concurrent_Collections;

import java.util.concurrent.ConcurrentLinkedDeque;

public class MainProducerConsumerNonBlocking {

	public static void main(String[] args) {

		ConcurrentLinkedDeque<Item> deque = new ConcurrentLinkedDeque<Item>();

		Thread producerThread = new Thread(new ProducerNonBlocking(deque));
		Thread consumerThread = new Thread(new ConsumerNonBlocking(deque));
		producerThread.start();
		consumerThread.start();
	}
}
