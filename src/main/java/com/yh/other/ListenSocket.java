package com.yh.other;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ListenSocket extends Thread {
	private static final int SERVER_PORT = 10000; //端口号
	private int count = 0;//连接客户端数
	private ServerSocket ss = null;//服务端socket
	
	public ListenSocket(){
		try {
			if(null==ss){
				this.ss = new  ServerSocket(SERVER_PORT);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		System.out.println("-------主监听线程----------start----------");
		try {
			while(true){
				Socket client = ss.accept();
				count += 1;
				Thread c_thread = new CreateServerThread(client,count);
				c_thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	class CreateServerThread extends Thread {
		private Socket client; //当前所连接客户端
		private int index;//当前线程编号
		private BufferedReader in = null;//从客户端接受到的信息
		private OutputStream out = null;//返回给客户端的信息
		
		public CreateServerThread(Socket client,int index) throws IOException {
			this.client = client;
			this.index = index;
		}
		
		public void run(){
			System.out.println("-------当前连接的客户端数为----------" + index + "----------");
			String ms = "Callback accepted " + client.getInetAddress() + ":" + client.getPort();
			System.out.println(ms);
			
			try {
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));//接收请求的流				
				out = client.getOutputStream();//写入缓存
				int len = 0;//监听到的字符串长度
				String str = "";//监听到的字符串
				char buf[] = new char[4096];
				while((len = in.read(buf)) > 0){
					//读取
					str += new String(buf,0,len);
					System.out.println("---------获取到第"+index+"个客户端的报文："+str);
					if(str!=null && !"".equals(str)){
						out.write(("服务端已接受到第"+index+"个客户端发送的报文").getBytes());
					}else{
						System.out.println("---------第"+index+"个客户端所传报文为空");
						out.write("-1".getBytes());
						break;
					}
				}
			} catch (IOException e) {
				System.out.println("---------服务端与第"+index+"个客户端交互时异常："+e.getMessage());
			}finally{
				try {
					if(client!=null){
						client.close();
						count -= 1;
						System.out.println("---------关闭第"+index+"个客户端连接，当前连接的客户端个数为"+count);
					}
				} catch (IOException e) {
					System.out.println("---------第"+index+"个客户端关闭异常："+e.getMessage());
				}
			}
		}
	}
	
	public void closeSocketServer(){
		try{
			if(ss!=null && !ss.isClosed()){
				ss.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ListenSocket socketThread = new ListenSocket();
		socketThread.start();
	}
}