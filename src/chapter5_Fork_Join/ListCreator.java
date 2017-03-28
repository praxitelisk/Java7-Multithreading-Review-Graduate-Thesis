package chapter5_Fork_Join;

import java.util.Random;

/*
 * This class defines and prints a list of integers
 */

public class ListCreator {
	private final int[] list;

	public ListCreator(int n) {
		list = new int[n];
		Random generator = new Random(System.currentTimeMillis());
		for (int i = 0; i < list.length; i++) {
			list[i] = generator.nextInt(20);
		}
	}

	public int[] getList() {
		
		//print the produced list
		System.out.println("Printing a random list of numbers");
		for (int i = 0; i < list.length; i++) {
			if (i % 5 == 0)
				System.out.println();
			System.out.print(list[i] + "\t");

		}
		System.out.println();
		System.out.println();
		return list;
	}

}
