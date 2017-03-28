package chapter2_Basic_Thread_Synchronization;

public class MainAccountTransactions {

	public static void main(String[] args) {

		//the company1's account object
		AccountLock account1 = new AccountLock();
		account1.setBalance(10000);
		
		Company company1 = new Company(account1);
		Thread companyThread1 = new Thread(company1);
		
		
		//the company2's account object
		AccountLock account2 = new AccountLock();
		account2.setBalance(10000);
		
		Company company2 = new Company(account2);
		Thread companyThread2 = new Thread(company2);
		
		//the thread of the inter-transactions between
		//the two companies
		CompanyTransaction transactions = 
			new CompanyTransaction(account1,account2);
		Thread transactionsThread = new Thread(transactions);

		//the bank object thread
		Bank bank = new Bank(account1, account2);
		Thread bankThread = new Thread(bank);

		
		System.out.printf("Account1 : Initial Balance: %f\n", account1
				.getBalance());
		
		System.out.printf("Account2 : Initial Balance: %f\n", account2
				.getBalance());
		
		//Start the threads.
		companyThread1.start();
		companyThread2.start();
		transactionsThread.start();
		bankThread.start();

		try {
			//waiting for all the threads to finish
			companyThread1.join();
			companyThread2.join();
			bankThread.join();
			transactionsThread.join();
			
			Thread.sleep(2000);
			
			System.out.printf("\n -end of fiscal year- \n");
			System.out.printf("Account1 : Final Balance: %f\n", account1
					.getBalance());
			
			System.out.printf("Account2 : Final Balance: %f\n", account2
					.getBalance());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
