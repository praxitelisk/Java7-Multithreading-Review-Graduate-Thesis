package chapter2_Basic_Thread_Synchronization;

public class Bank implements Runnable {

	//the bank registers two company's accounts
	private AccountLock account1;
	private AccountLock account2;

	public Bank(AccountLock account1, AccountLock account2) {
		this.account1 = account1;
		this.account2 = account2;
	}

	//bank transactions like wages, security etc
	public void run() {
		for (int i = 0; i < 100; i++) {
			account1.subtractAmount(1000);
			account2.subtractAmount(1000);
		}
	}
}
