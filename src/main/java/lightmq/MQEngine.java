package lightmq;   
  
import java.util.Queue;   
import java.util.concurrent.ConcurrentLinkedQueue;   
import java.util.concurrent.ExecutorService;   
import java.util.concurrent.Executors;   
  
public class MQEngine<E, P extends MessageHandler<E>> {   
       
    /**  
     * 消息队列  
     * TODO 优化改为 LightQueue（内部实现为queue组）  
     */  
    final Queue<E> queue = new ConcurrentLinkedQueue<E>();   
//    final Queue<E> queue = new LightQueue<E>(10);   
       
    /**  
     * handler class  
     */  
    private Class<? extends MessageHandler<E>> handlerClass;   
       
    /**  
     * 消费者线程池  
     */  
    ExecutorService consumerES;       
       
    /**  
     * 消费者数量  
     */  
    private int consumerSize = 1;   
       
    private Runnable[] consumers;   
       
    /**  
     * 构造函数  
     *   
     * @param c 处理器类  
     */  
    public MQEngine(Class<? extends MessageHandler<E>> c){   
        this(1, 1, c);   
    }   
       
    /**  
     * 构�?函数   
     *   
     * @param threadPoolSize 线程池大�?  
     * @param consumerSize 消息者数�?  
     * @param c 处理器类�?  
     */  
    public MQEngine(int threadPoolSize, int consumerSize, Class<? extends MessageHandler<E>> c){   
        consumerES = Executors.newFixedThreadPool(threadPoolSize);   
        this.handlerClass = c;   
        this.consumerSize = consumerSize;           
    }   
       
    /**  
     * 启动消费者线�?  
     * @param consumerSize  
     * @param c  
     */  
    public void start() {   
        final Class<? extends MessageHandler<E>> c = this.handlerClass;   
           
        class ConsumerTask implements Runnable{               
               
            public void run() {   
                MessageHandler<E> p = null;   
                try {   
                    p = c.newInstance();   
                } catch (InstantiationException e1) {   
                    // TODO Auto-generated catch block   
                    e1.printStackTrace();   
                } catch (IllegalAccessException e1) {   
                    // TODO Auto-generated catch block   
                    e1.printStackTrace();   
                }   
                //    
                int i = 0;   
                while(true){   
                    try {   
                        if (!queue.isEmpty()) {   
                            p.consume(queue.poll());   
                        }   
                        i++;   
                    } catch (Exception e) {   
                        e.printStackTrace();   
                    }   
                    // 每执行100次   
                    if(10==i){   
                        synchronized (this) {   
                            try {   
                                i = 0;   
                                wait(100);   
                            } catch (InterruptedException e) {   
                                e.printStackTrace();   
                            }   
                        }                               
                    }   
                }   
            }   
        }   
           
        this.consumers = new Runnable[this.consumerSize];   
        for (int i = 0; i < this.consumers.length; i++) {   
            consumers[i] = new ConsumerTask();   
        }   
           
        for (int i = 0; i < consumers.length; i++) {   
            consumerES.execute(consumers[0]);   
        }   
    }   
  
    /**  
     *   
     * @param e  
     */  
    public void push(E e){   
        queue.add(e);   
        for (int i = 0; i < this.consumers.length; i++) {   
//            synchronized (consumers[i]) {   
//                consumers[i].notify();   
//            }               
        }           
    }   
       
    /**  
     *   
     */  
    public void destory(){   
        this.queue.clear();   
        this.consumerES.shutdown();   
    }   
       
}  
