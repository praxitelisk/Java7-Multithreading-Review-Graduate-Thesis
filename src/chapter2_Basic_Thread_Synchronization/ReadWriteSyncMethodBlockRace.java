package chapter2_Basic_Thread_Synchronization;

public class ReadWriteSyncMethodBlockRace implements Runnable {

	private int aValue;
	private Object SyncControlObj = new Object();

	public ReadWriteSyncMethodBlockRace(int n) {
		this.aValue = n;
	}

	public void GetAndIncrementValue() {
		synchronized (SyncControlObj) {

			System.out.println("I've got this value " 
					+ this.aValue
					+ " and I am thread " 
					+ Thread.currentThread().getName());

			this.aValue++;

			System.out.println("I've incremented, new value " 
					+ this.aValue
					+ " and I am thread " 
					+ Thread.currentThread().getName()
					+ "\n");
		}
	}

	public void run() {

		GetAndIncrementValue();
	}

	public static void main(String[] args) {

		ReadWriteSyncMethodBlockRace aReadWriteRace = 
			new ReadWriteSyncMethodBlockRace(10);

		Thread t1 = new Thread(aReadWriteRace);
		Thread t2 = new Thread(aReadWriteRace);

		t1.setName("Thread1");
		t2.setName("Thread2");

		t1.start();
		t2.start();
	}
}
