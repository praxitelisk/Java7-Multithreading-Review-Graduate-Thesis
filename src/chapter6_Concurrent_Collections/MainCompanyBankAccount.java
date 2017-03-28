package chapter6_Concurrent_Collections;

public class MainCompanyBankAccount {

	public static void main(String[] args) {

		// Create an Account object and set its balance to 1000.
		Account account = new Account();
		account.setBalance(1000);

		// Create a new Company task and a thread to execute it.
		Company company = new Company(account);
		Thread companyThread = new Thread(company);

		// Create a new Bank task and a thread to execute it.
		Bank bank = new Bank(account);
		Thread bankThread = new Thread(bank);

		System.out.printf("Account : Initial Balance: %d\n", account
				.getBalance());

		// Start the threads.
		companyThread.start();
		bankThread.start();

		/*
		 * Wait for the finalization of the threads using the join() method and
		 * write in the console the final balance of the account.
		 */
		try {
			companyThread.join();
			bankThread.join();
			System.out.printf("Account : Final Balance: %d\n", account
					.getBalance());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
