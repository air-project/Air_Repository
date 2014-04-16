package com.yh.other;
import java.text.SimpleDateFormat;
import java.util.Date;

public class T {
	public static void main(String[] args) {
		long l=System.currentTimeMillis();
		for(int i=0;i<200;i++){
			 String pattern="000";
			  java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
			  int a = 10,j=6;
			  System.out.println(df.format(j));

		}
//		String str="[yyyy]#MM{dd";
//		for(int i=0;i<20;i++){
//		SimpleDateFormat sdf = new SimpleDateFormat(str);
//		Date d =new Date();
//		sdf.format(d);
//		}
//		System.out.println(System.currentTimeMillis()-l);
//		l=System.currentTimeMillis();
//		for(int i=0;i<20;i++){
//			new T().create(str);
//		}
//		System.out.println(System.currentTimeMillis()-l);
//		
	}
}
