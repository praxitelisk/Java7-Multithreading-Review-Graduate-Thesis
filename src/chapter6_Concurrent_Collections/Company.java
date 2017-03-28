package chapter6_Concurrent_Collections;

public class Company implements Runnable {

	// Declare a private Account attribute named account.
	private Account account;	

	// the constructor of the class
	public Company(Account account) {
		this.account = account;
	}

	/*
	 * Implement the run() method of the task. Use the addAmount() method of the
	 * account to make 10 increments of 1,000 in its balance.
	 */
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			account.addAmount(1000);
		}
	}

}
