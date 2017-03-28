package chapter6_Concurrent_Collections;

public class Bank implements Runnable {

	// Declare a private Account attribute named account.
	private Account account;

	// Implement the constructor of the class to initialize its attribute.
	public Bank(Account account) {
		this.account = account;
	}

	/*
	 * Implement the run() method of the task. Use the subtractAmount() method
	 * of the account to make 10 decrements of 1,000 in its balance.
	 */
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			account.subtractAmount(1000);
		}
	}

}
