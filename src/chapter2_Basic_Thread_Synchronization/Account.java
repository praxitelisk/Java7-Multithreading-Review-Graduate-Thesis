package chapter2_Basic_Thread_Synchronization;

public class Account {

	private double balance;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public synchronized void addAmount(double amount) {
		// acquired lock from Account Object
		double tmp = balance;
		tmp += amount;
		balance = tmp;
	}

	public synchronized void subtractAmount(double amount) {
		// acquired lock from Account Object
		double tmp = balance;
		tmp -= amount;
		balance = tmp;
	}
}
