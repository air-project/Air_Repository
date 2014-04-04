package com.yh;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {

	public static void main(String[] args) throws Exception {
		ExecutorService es = Executors.newFixedThreadPool(2);
		List<Future<String>> list = new ArrayList<Future<String>>();
		Future<String> f = null;
		//给每个任务分配一个线程
		for(int i=0; i<5; i++) {
			f = es.submit(new MyTask("name"+i));
			list.add(f);
		}
		
		//所有任务都开始执行后，关闭线程池
		es.shutdown();
		
		//通过循环找到刚刚结束的任务，然后立刻处理该任务的返回值
		int n=5, i;
		while(n>0) {
			for(i=0; i<n; i++) {
				f = list.get(i);
				if(f.isDone()) {
					System.out.println(f.get());
					list.remove(i);
					n--;
					break;
				}
			}
		}
		System.out.println("主线程结束");
	}
}

class MyTask implements Callable<String> {

	private String name;
	public String call() throws Exception {
		//随机休眠几秒钟，最后返回当前线程的名字
		Random rand = new Random();
		int n = rand.nextInt(10);
		TimeUnit.SECONDS.sleep(n);
		return name+Thread.currentThread().getName();
	}
	MyTask(String name){
		this.name=name;
	}
	
}