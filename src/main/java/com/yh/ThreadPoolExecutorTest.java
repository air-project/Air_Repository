package com.yh;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ThreadPoolExecutorTest {
	public static void main(String args[]) throws Exception {
		 ProcessBuilder builder = new ProcessBuilder ("java","-help");
//		ProcessBuilder builder = new ProcessBuilder("java", "-version"); 
//		System.out.println(builder.environment().get("Path"));
		Process process = builder.start();
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "GBK");
		BufferedReader br = new BufferedReader(isr);
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
	}
}