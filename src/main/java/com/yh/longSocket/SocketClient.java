package com.yh.longSocket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost",1111);
		if (socket.isConnected()){
			OutputStream os =null;
			InputStream is = null;
			while (true) {
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					String in = br.readLine();
					os = socket.getOutputStream();
					os.write(in.getBytes());//"hello".getBytes());
					os.flush();
					
					is = socket.getInputStream();
					byte[] resp = new byte[5];
					is.read(resp);
					System.out.println("response:" + new String(resp));
				} catch (Exception e) {
					e.printStackTrace();
					if(os != null)
						os.close(); //由于是长连接，抛异常时要关闭os，否则会broken pipe
					if(is != null)
						is.close();
				} finally {
					System.out.println("do nothing");
				}
				
			}
		}

	}

}
