package chapter2_Basic_Thread_Synchronization;

public class ReadWriteSyncMethodRace 
implements Runnable {

	private int aValue;

	public ReadWriteSyncMethodRace(int n) {
		this.aValue = n;
	}

	public synchronized void GetAndIncrementValue() {

		System.out.println("I've got this value " 
				+ this.aValue
				+ " and I am thread " 
				+ Thread.currentThread().getName());

		this.aValue++;

		System.out.println("I've incremented, new value " 
				+ this.aValue
				+ " and I am thread " 
				+ Thread.currentThread().getName()+"\n");
	}

	public void run() {

		GetAndIncrementValue();
	}

	public static void main(String[] args) {

		ReadWriteSyncMethodRace aReadWriteRace = 
			new ReadWriteSyncMethodRace(10);

		Thread t1 = new Thread(aReadWriteRace);
		Thread t2 = new Thread(aReadWriteRace);

		t1.setName("Thread1");
		t2.setName("Thread2");

		t1.start();
		t2.start();
	}
}
