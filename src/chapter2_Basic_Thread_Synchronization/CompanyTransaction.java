package chapter2_Basic_Thread_Synchronization;

public class CompanyTransaction implements Runnable {

	private AccountLock Account1, Account2;

	public CompanyTransaction(AccountLock acc1, AccountLock acc2) {
		this.Account1 = acc1;
		this.Account2 = acc2;
	}

	//start a transaction at a time between the two companies
	int turn = 0;
	public void run() {
		for (int i = 0; i < 20; i++) {
			if (turn % 2 == 0) {
				this.Account1.subtractAmount(100 + i * i);
				this.Account2.addAmount(100 + i * i);
			} else {
				this.Account2.subtractAmount(100 + i * i);
				this.Account1.addAmount(100 + i * i);
			}
			turn++;
		}
	}
}
