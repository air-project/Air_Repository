package longSocket;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) throws IOException {
	    ServerSocket serverSocket=new ServerSocket(1111);  
	    Socket socket=serverSocket.accept();  
	    while(true){//保持长连接  
	        try {  
	                Thread.sleep(3000);//等待时间  
	        } catch (InterruptedException e1) {  
	                e1.printStackTrace();  
	        }  
	        if (socket !=null){  
	        	InputStream is = null;
	        	OutputStream os = null;
	            try {  
                    String ip = socket.getInetAddress().toString().replace("/", "");  
                    System.out.println("====socket.getInetAddress()====="+ip);  
                    socket.setKeepAlive(true);  
                    is = socket.getInputStream();  
                    os = socket.getOutputStream();  
                    System.out.println("服务器端接受请求");  
	                byte[] buff = new byte[5];
	                is.read(buff);
	                String tempdata =new String(buff);  // StreamEazyUse.getContent(is);  
	                if (tempdata.equals("quit1")) {
	                	System.out.println("不处理，继续：："+tempdata); 
	                	continue;
	                }
	                System.out.println("接收到的数据为："+tempdata);  
	                
	                os.write("world".getBytes());
	                os.flush();  //发送响应 
//	                is.close();
//	                os.close();
	            }catch(Exception e){  
	                System.out.println("出现了错误,关闭连接");
	                if (is!= null)
	                	is.close();
	                if (os!= null)
	                	os.close();
	                
	                e.printStackTrace();
	                
	            }  
	        }  
	    }  

	}

}
