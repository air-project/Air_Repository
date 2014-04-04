package com.yh;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Singleton {
	private static Singleton instance = null;
	private static boolean firstThread = true;
	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
					simulateRandomActivity();
				}
			}
		}
		System.out.println("created singleton: " + instance);
		return instance;
	}
	
	private static void simulateRandomActivity() {
		try {
			if (firstThread) {
				firstThread = false;
				System.out.println("sleeping...");

				// This nap should give the second thread enough time
				// to get by the first thread.
				Thread.currentThread().sleep(50);
			}
		} catch (InterruptedException ex) {
			System.out.println("Sleep interrupted");
		}
	}
	
	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i=0;i<2;i++){
		es.execute(new Runnable() {
			@Override
			public void run() {
				Singleton.getInstance();
			}
		});
		}
		es.shutdown();
	}
}
