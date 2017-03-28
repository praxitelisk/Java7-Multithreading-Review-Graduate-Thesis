package chapter4_Thread_Executors;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RandomImageExecutorCreatorFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public RandomImageExecutorCreatorFrame(
			ArrayList<Future<ArrayList<Integer>>> resultList, int MaxX,
			int tiles) {

		super("Random Image Executor Creator");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(MaxX, MaxX);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);

		//Square class the prints the tiles
		ExecutorSquares squares = new ExecutorSquares(resultList, MaxX, tiles);
		getContentPane().add(squares);

		pack();
	}
}

class ExecutorSquares extends JPanel {

	
	private ArrayList<Future<ArrayList<Integer>>> resultList;
	private int tiles;
	private int MaxX;
	private int MaxY;

	public ExecutorSquares(ArrayList<Future<ArrayList<Integer>>> resultList, int MaxX,
			int segments) {

		this.resultList = resultList;
		this.MaxX = MaxX;
		this.MaxY = this.MaxX;
		this.tiles = segments;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(MaxX, MaxY);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		//traverse the all the results from all the Callable Tasks
		for (int i = 0; i < resultList.size(); i++) {
			try {
				Future<ArrayList<Integer>> result = resultList.get(i);
				
				ArrayList<Integer> aux_table = new ArrayList<Integer>();
				aux_table = result.get();
				
				int[] color_values = new int[aux_table.size()];
				int counter = 0;
				for (int j = 0; j < aux_table.size(); j++) {
					color_values[counter] =(int) aux_table.get(j);
					counter++;
				}
				int step = 0;
				for (int j = 0; j < MaxY; j += (MaxY / tiles)) {
	
					//then start printing the RGB color per tile
					Color color = new Color(
							color_values[step], color_values[(step+1)],
							color_values[(step+2)]);
					g2.setColor(color);
					g2.fillRect(
							i*(MaxX/tiles), j, MaxY / tiles,
							MaxY / tiles);
					step +=3;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}
