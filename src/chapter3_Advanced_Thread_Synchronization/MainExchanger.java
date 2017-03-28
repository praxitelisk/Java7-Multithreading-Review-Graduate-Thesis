package chapter3_Advanced_Thread_Synchronization;

import java.util.ArrayList;
import java.util.concurrent.Exchanger;

public class MainExchanger {

	public static void main(String[] args) {
		
		ArrayList<Double> buffer1=new ArrayList<Double>();
		ArrayList<Double> buffer2=new ArrayList<Double>();

		Exchanger<ArrayList<Double>> exchanger = new Exchanger<ArrayList<Double>>();
		
		SinTaylorExchanger sinCalc = new SinTaylorExchanger(buffer1, exchanger);
		CosTaylorExchanger cosCalc = new CosTaylorExchanger(buffer2, exchanger);
		
		Thread sinThread = new Thread(sinCalc,"Thread1");
		Thread cosThread = new Thread(cosCalc,"Thread2");
		
		sinThread.start();
		cosThread.start();
	}
}
