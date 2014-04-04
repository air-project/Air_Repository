package lightmq;   
  
import java.util.concurrent.Future;   
import java.util.concurrent.atomic.AtomicLong;   
  
import org.apache.http.HttpResponse;   
import org.apache.http.client.methods.HttpGet;   
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;   
import org.apache.http.impl.nio.client.HttpAsyncClients;   
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;   
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;   
import org.apache.http.nio.conn.NHttpClientConnectionManager;   
  
/**  
 * 业务相关消息处理器  
 * @author kevin  
 *  
 */  
public class MyMessageHandler extends MessageHandler<String>{   
       
    static AtomicLong sentCount = new AtomicLong(0);   
       
    static NHttpClientConnectionManager connMgr;   
       
    public void consume(String e) {   
        sendToTomcat(e);   
    }   
       
    private CloseableHttpAsyncClient httpclient = null;   
       
    public MyMessageHandler(){   
           
        try {   
            connMgr = new PoolingNHttpClientConnectionManager(new DefaultConnectingIOReactor());   
            httpclient = HttpAsyncClients.createMinimal(connMgr);   
            httpclient.start();   
        } catch (Exception e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
        }   
    }   
       
    /**  
     * 发给sc  
     * @param message  
     */  
    private void sendToTomcat(String message){   
        long startTime = System.currentTimeMillis();           
           
        try {   
               
               
            // http[GET]请求,    
            final HttpGet request1 = new HttpGet("http://localhost");   
            Future<HttpResponse> future = httpclient.execute(request1, null);   
               
            // and wait until a response is received   
            HttpResponse response1;   
            response1 = future.get();   
            System.out.println("message " + message + ":" + request1.getRequestLine() + "->" + response1.getStatusLine());   
            System.out.println(message + " Sent; Cost:" + (System.currentTimeMillis() - startTime) + "; Succeed  Sent: " + sentCount.incrementAndGet());   
        } catch (Exception e1) {   
            System.err.println(e1.getMessage());   
        } finally{   
            // 关闭链接   
            if(null!=httpclient){   
                try {   
                    //httpclient.close();   
                } catch (Exception e1) {   
                    //e1.printStackTrace();   
                    System.err.println(e1.getMessage());   
                }   
            }   
        }   
    }   
}  
