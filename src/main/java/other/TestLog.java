package other;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class TestLog  {

	public static void main(String[] args) {
		final List ab= new ArrayList();
		for(int i=0;i<100;i++){
			ab.add(i);
		}
		ExecutorService ex = Executors.newCachedThreadPool();
	       for (int i = 0; i < 4; i++) {
	           final int a = i;
	           //每一次execute方法，都是向池中放入一个对象
	           ex.execute(new Runnable() {
	              public void run() {
	                  while(true){
	                	  synchronized (ab) {
	                		  if(ab.size()==0) return;
	                		  ab.remove(ab.size()-1);
	 	                     System.err.println("测试"+ab.size()+"...."+a+">"
	 	                            +Thread.currentThread().getName()+","
	 	                            +Thread.currentThread().isDaemon());

						}
	                     try{
	                         Thread.sleep(2000);
	                     }catch(Exception e){
	                         e.printStackTrace();
	                     }
	                  }
	              }
	           });
	       }
	       try {
			Thread.sleep(1000*60*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }


}