package chapter2_Basic_Thread_Synchronization;

public class ReadWriteRace implements Runnable {

	private int aValue;

	public ReadWriteRace(int n) {
		this.aValue = n;
	}

	public void incrementValue() {
		this.aValue++;
	}

	public int getValue() {
		return this.aValue;
	}

	public void run() {

		System.out.println("I've got this value " 
				+ getValue() + " and I am thread "
				+ Thread.currentThread().getName());

		incrementValue();

		System.out.println("I've incremented, new value " 
				+ getValue() + " and I am thread " 
				+ Thread.currentThread().getName());

	}

	public static void main(String[] args) {

		ReadWriteRace aReadWriteRace = new ReadWriteRace(10);

		Thread t1 = new Thread(aReadWriteRace);
		Thread t2 = new Thread(aReadWriteRace);
		
		t1.setName("Thread1");
		t2.setName("Thread2");

		t1.start();
		t2.start();
	}
}
