package chapter2_Basic_Thread_Synchronization;

public class Company implements Runnable {

	//company's account attribute
	private AccountLock account;

	public Company(AccountLock account) {
		this.account = account;
	}

	//add company's profits
	public void run() {
		for (int i = 0; i < 100; i++) {
			account.addAmount(1000);
		}
	}
}
