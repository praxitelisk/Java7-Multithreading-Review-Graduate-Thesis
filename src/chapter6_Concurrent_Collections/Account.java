package chapter6_Concurrent_Collections;

import java.util.concurrent.atomic.AtomicLong;

public class Account {

	/*
	 * Declare a private AtomicLong attribute named balance to store the balance
	 * of the account.
	 */
	private AtomicLong balance;

	// Implement the constructor of the class to initialize its attribute.
	public Account() {
		balance = new AtomicLong();
	}

	/*
	 * Implement a method named getBalance() to return the value of the balance
	 * attribute.
	 */
	public long getBalance() {
		return balance.get();
	}

	/*
	 * Implement a method named setBalance() to establish the value of the
	 * balance attribute.
	 */
	public void setBalance(long balance) {
		this.balance.set(balance);
	}

	/*
	 * Implement a method named addAmount() to increment the value of the
	 * balance attribute.
	 */
	public void addAmount(long amount) {
		this.balance.getAndAdd(amount);
	}

	/*
	 * Implement a method named substractAmount() to decrement the value of the
	 * balance attribute.
	 */
	public void subtractAmount(long amount) {
		this.balance.getAndAdd(-amount);
	}

}
