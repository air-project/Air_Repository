package lightmq;

import java.util.concurrent.ExecutorService;   
import java.util.concurrent.Executors;   
import java.util.concurrent.atomic.AtomicLong;   
  
import lightmq.MQEngine;   
import lightmq.MyMessageHandler;   
  
/**  
 * MQEngine测试类  
 * @author kevin  
 *  
 */  
public class TestMQ {   
    public static void main(String[] args) {       
        final AtomicLong l = new AtomicLong(0);   
        //    
        final MQEngine<String, MyMessageHandler> mq = new MQEngine<String, MyMessageHandler>(10, 50, MyMessageHandler.class);          
        // 模拟客户并发数   
        final int PRODUCER_SIZE = 200000;   
        // 模拟每个客户平均请求次数   
        final int REQUEST_TIME = 10;   
           
        ExecutorService es = Executors.newFixedThreadPool(10);   
        for (int i = 0; i < PRODUCER_SIZE; i++) {   
            es.execute(new Runnable() {   
                public void run() {   
                    for (int i = 0; i < REQUEST_TIME; i++) {                        
                        mq.push(String.valueOf(l.incrementAndGet()));   
                    }   
                }   
            });   
        }   
           
           
        try {   
            Thread.sleep(1000);   
        } catch (InterruptedException e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
        }   
           
//        System.out.println(mq.size());   
           
    }   
}  
