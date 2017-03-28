package chapter6_Concurrent_Collections;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Application implements Delayed {

	// Declare a private Date attribute named startDate.
	private Date startDate;

	// The constructor of the class.
	public Application(Date startDate) {
		this.startDate = startDate;
	}

	/*
	 * Implement the compareTo() method. It receives a Delayed object as its
	 * parameter. Return the difference between the delay of the current object
	 * and the one passed as parameter.
	 */
	@Override
	public int compareTo(Delayed o) {
		long result = this.getDelay(TimeUnit.NANOSECONDS)
				- o.getDelay(TimeUnit.NANOSECONDS);
		if (result < 0) {
			return -1;
		} else if (result > 0) {
			return 1;
		}
		return 0;
	}

	/*
	 * Implement the getDelay() method. Return the difference between startDate
	 * of the object and the actual Date in TimeUnit received as parameter.
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		Date now = new Date();
		long diff = startDate.getTime() - now.getTime();
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}

}
