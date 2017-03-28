package chapter4_Thread_Executors;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class TaskRandomImage implements Callable<ArrayList<Integer>> {

	private int MaxX = 500;
	private int MaxY;
	private int tiles;
	private Integer sleep_time;

	public TaskRandomImage(int MaxX, int seqments, int sleep_time) {
		this.MaxX = MaxX;
		this.MaxY = this.MaxX;
		this.tiles = seqments;
		this.sleep_time = sleep_time;
	}

	@Override
	public ArrayList<Integer> call() throws Exception {

		Random random = new Random();

		ArrayList<Integer> result = new ArrayList<Integer>();
		
		//produce RGB random values for each tile
		for (int j = 0; j < MaxY; j += (MaxY / tiles)) {
			int red, green, blue;
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);

			result.add(red);
			result.add(green);
			result.add(blue);

		}
		//so-called sleep after hard work
		TimeUnit.MILLISECONDS.sleep(sleep_time);
		return result;
	}

}
