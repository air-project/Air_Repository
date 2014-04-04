package com.spring.maven.test01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Log {
	public static int guess(int maxValue) {
		List record = new ArrayList();
		record.add(5);
		Random random = new Random();
		int value = 0;
		int i=0;
		do {
			value = 4 + 1;
			i++;
			System.out.println(String.format("value %s %s", value,i));
		} while (record.contains(value));
		record.add(value);
		return value;
	}
	public static void main(String[] args) {
		Log.guess(10);
	}
}
