package chapter4_Thread_Executors;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainRandomImageCreator{
	
	//Max height and width of the random image
	private static final int MaxX = 500;
	
	//create a tiles x tiles random tiled image
	private static final int tiles = 4;

	public static void main(String[] args) {

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(5);

		ArrayList<Future<ArrayList<Integer>>> resultList = 
			new ArrayList<Future<ArrayList<Integer>>>();

		Random random = new Random();

		for (int i = 0; i < MaxX; i+=MaxX/tiles) {
			int sleep_time = random.nextInt(10);
			//create a callable thread task that will
			//compute MaxX/tiles part of image
			TaskRandomImage task = new TaskRandomImage(MaxX, 
					tiles ,sleep_time);

			//submit it to the thread pool executor
			Future<ArrayList<Integer>> result = executor.submit(task);
			resultList.add(result);
		}
		
		//wait until all the tasks are finished
		do {
			for (int i = 0; i < resultList.size(); i++) {
				Future<ArrayList<Integer>> result = resultList.get(i);
				System.out.printf("is Task %d ready?: %s\n"
						, i, result.isDone());
			}
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (executor.getCompletedTaskCount() < resultList.size());

		
		//get all the results from the Callable objects
		System.out.printf("\n--All Tasks are done! Main process" +
				" the image results--\n\n");
		for (int i = 0; i < resultList.size(); i++) {
			try {
				Future<ArrayList<Integer>> result = resultList.get(i);
				ArrayList<Integer> aux_table = new ArrayList<Integer>();
				
				aux_table = result.get();
				
				System.out.println("RGB values created by thread "+ (i+1));
				//get the 3 RGB colors per tile per callable thread
				for(int j=0; j<aux_table.size(); j++) {
					if (j%3==0)
						System.out.println("red:\tgreen:\tblue: values " +
								"for tile "+i+","+ (j/3));
					int aux = j%3;
					switch(aux) {
					case 0:
						System.out.print(""+ aux_table.get(j));
						break;
					case 1:
						System.out.print("\t"+ aux_table.get(j));
						break;
					case 2:
						System.out.println("\t"+ aux_table.get(j));
						break;
					}
				}
				System.out.println();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();			
			}
		}
		
		//produce the random image in a Java Frame
		new RandomImageExecutorCreatorFrame(resultList, MaxX, tiles);
		
		//shut down the executor
		executor.shutdown();
	} // end of main
} // end of class
