package chapter2_Basic_Thread_Synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountLock {

	private double balance;
	private final Lock addAmountLock = new ReentrantLock();
	private final Lock subtractAmountLock = new ReentrantLock();
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void addAmount(double amount) {
		// acquired lock from Account Object
		addAmountLock.lock();
		try {
			double tmp = balance;
			tmp += amount;
			balance = tmp;
		}
		finally {
			addAmountLock.unlock();
		}
	}

	public void subtractAmount(double amount) {
		// acquired lock from Account Object
		subtractAmountLock.lock();
		try {
			double tmp = balance;
			tmp -= amount;
			balance = tmp;
		}
		finally {
			subtractAmountLock.unlock();
		}
	}
}