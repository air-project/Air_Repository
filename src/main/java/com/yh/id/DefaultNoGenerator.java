package com.yh.id;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DefaultNoGenerator {
	private String prePattern;
	private boolean withDate = false;
	private String seqPattern;
	private long seq;

	public DefaultNoGenerator prePattern(String prePattern) {
		this.prePattern = prePattern;
		return this;
	}

	public DefaultNoGenerator withDate(boolean withDate) {
		this.withDate = withDate;
		return this;
	}

	public long getSeq() {
		return seq;
	}

	public DefaultNoGenerator seq(long seq) {
		this.seq = seq;
		return this;
	}

	public DefaultNoGenerator seqPattern(String seqPattern) {
		this.seqPattern = seqPattern;
		return this;
	}

	public String create() {
		DecimalFormat df = new DecimalFormat(seqPattern);
		if (withDate) {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(prePattern);
			return sdf.format(d) + df.format(seq);
		} else {
			return (prePattern == null ? "" : prePattern) + df.format(seq);
		}
	}

	public static void main(String[] args) {
		long l = System.currentTimeMillis();
		DefaultNoGenerator d = new DefaultNoGenerator();
		for (int i = 0; i < 200; i++) {
			String pattern = "000";
			java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
			int a = 10, j = 6;
			System.out.println(df.format(j));
			System.out.println(d.withDate(false).seqPattern("Q000").seq(i).create());

		}
		// String str="[yyyy]#MM{dd";
		// for(int i=0;i<20;i++){
		// SimpleDateFormat sdf = new SimpleDateFormat(str);
		// Date d =new Date();
		// sdf.format(d);
		// }
		// System.out.println(System.currentTimeMillis()-l);
		// l=System.currentTimeMillis();
		// for(int i=0;i<20;i++){
		// new T().create(str);
		// }
		// System.out.println(System.currentTimeMillis()-l);
		//
	}
}
