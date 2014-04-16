package com.yh.other;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client extends Thread{
	Socket socket; 
	BufferedReader in; 
	OutputStream out; 
	String str;

	public Client(String str){ 
		this.str = str;
	} 
	
	public void run() {
		try {
			socket = new Socket("127.0.0.1", 10000); 
			//客户端发送给服务端消息
			out = socket.getOutputStream();
			out.write(str.getBytes());
			
			//接收到的服务端返回信息
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			int len = 0;
			String msg = "";
			char buf[] = new char[4096];
			while((len = in.read(buf)) > 0){
				msg += new String(buf,0,len);
				System.out.println("------服务端返回信息："+msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args){ 
		new Client("hello_1").start(); 
		new Client("hello_2").start(); 
		new Client("hello_3").start(); 
		Client t =new Client("hello_4");
		t.start(); 
	} 
}