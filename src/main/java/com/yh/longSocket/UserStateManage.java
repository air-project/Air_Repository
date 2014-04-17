package com.yh.longSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class UserStateManage extends Thread {


	//在线用户状态列表

	static HashMap<Integer, String> userStateList = new HashMap<Integer, String>();

	Object hashLock = new Object();


	//当前的连接数和工作线程数

	static int workThreadNum = 0;

	static int socketConnect = 0;


	private ServerSocket serverSocket;

	//服务器IP

	private String host = "127.0.0.1";


	//服务器端口

	private int stateReportPort = 60001;


	//设置心跳包的结束标记

	String endFlag = "</protocol>";

	CharSequence csEndFlag = endFlag.subSequence(0, 10);


	//扫描间隔

	private int scanTime = 1800; 



	@Override

	public void run() {

	//绑定端口,并开始侦听用户的心跳包

	serverSocket = startListenUserReport(stateReportPort);

	if(serverSocket == null){

	System.out.println("【创建ServerSocket失败！】");

	return;

	}

	//启动扫描线程

	Thread scanThread = new Thread(new scan());

	scanThread.start();

	//等待用户心跳包请求

	while(true){

	Socket socket = null;

	try {

	socketConnect = socketConnect + 1;

	//接收客户端的连接

	socket = serverSocket.accept();   

	//为该连接创建一个工作线程

	Thread workThread = new Thread(new Handler(socket));

	//启动工作线程

	workThread.start();

	} catch (IOException e) {

	e.printStackTrace();

	}

	}

	}


	/**

	 * 创建一个ServerSocket来侦听用户心跳包请求

	 * @param port 指定的服务器端的端口

	 * @return 返回ServerSocket

	 * @author dream

	*/

	public ServerSocket startListenUserReport(int port){

	try {

	ServerSocket serverSocket = new ServerSocket();

	if(!serverSocket.getReuseAddress()){

	serverSocket.setReuseAddress(true);

	}

	serverSocket.bind(new InetSocketAddress(host,port));

	System.out.println("【开始在"+serverSocket.getLocalSocketAddress()+"上侦听用户的心跳包请求！】");

	return serverSocket;

	} catch (IOException e) {

	System.out.println("【端口"+port+"已经被占用！】");

	if (serverSocket != null) {

	if (!serverSocket.isClosed()) {

	try {

	serverSocket.close();

	} catch (IOException e1) {

	e1.printStackTrace();

	}

	}

	}

	}

	return serverSocket;

	}



	//工作线程类

	class Handler implements Runnable{

	private Socket socket;

	String us = null;

	String newUser = null;

	private int userId;

	private int userState;

	/**

	 * 构造函数，从调用者那里取得socket

	 * @param socket 指定的socket

	 * @author dream

	*/

	public Handler(Socket socket){

	this.socket = socket;

	}


	/**

	 * 从指定的socket中得到输入流

	 * @param socket 指定的socket

	 * @return 返回BufferedReader

	 * @author dream

	 */

	private BufferedReader getReader(Socket socket){

	InputStream is = null;

	BufferedReader br = null;

	 

	try {

	is = socket.getInputStream();

	br = new BufferedReader(new InputStreamReader(is));

	} catch (IOException e) {

	e.printStackTrace();

	}

	return br;

	}


	  public void run() {

	try{

	workThreadNum = workThreadNum +1;

	System.out.println("【第"+workThreadNum+"个的连接:"+socket.getInetAddress()+":"+socket.getPort()+"】");

	BufferedReader br = getReader(socket);

	String meg = null;

	StringBuffer report = new StringBuffer();

	while ((meg = br.readLine()) != null) {

	report.append(meg);

	if (meg.contains(csEndFlag)) {

	us = getReporterUserState(meg, socket);

	synchronized (hashLock) {

	userStateList.put(userId, us);

	}

	}

	}

	}catch(IOException e){

	System.out.println("【客户:已经断开连接！】");

	userStateList.remove( userId );

	announceStateChange( userId , -1);

	}finally{

	if(socket != null){

	try {

	//断开连接

	socket.close();

	} catch (IOException e) {

	e.printStackTrace();

	}

	}

	}

	}

	  private String getReporterUserState(String meg , Socket socket){


	return "bbbb";

	  }

	}


	//扫描线程

	class scan implements Runnable{

	public void run() {

	while (true) {


	synchronized (hashLock) {

	if(!userStateList.isEmpty()){

	//遍历在线用户列表

	for (Map.Entry<Integer, String> entry : userStateList.entrySet()) {

	System.out.println(entry.getKey() + "-->" + entry.getValue().toString());

	}

	}else{

	System.out.println("现在还没有在线用户！"); 

	}

	}

	//实现定时扫描

	try {

	sleep(scanTime);

	} catch (InterruptedException e) {

	e.printStackTrace();

	}

	}

	}

	}


	private void announceStateChange(int userId , int state){

	System.out.println("通知其好友!");

	}


	/**

	 * 查询一个用户是否在线

	 * @param userId 指定要查询状态的用户的ID

	 * @return true 在线； false 不在线；

	 * @author dream

	*/

	public boolean isAlive(int userId){

	synchronized (hashLock) {

	return userStateList.containsKey(userId);

	}

	}


	/**

	 * 返回指定用户ID的状态

	 * @param userId 指定要查询状态的用户的ID

	 * @return >0 该用户在线;  -1 该用户离线

	 * @author dream

	*/



	public Object getHashLock() {

	return hashLock;

	}

	 

	public void setHashLock(Object hashLock) {

	this.hashLock = hashLock;

	}

	 

	public String getHost() {

	return host;

	}

	 

	public void setHost(String host) {

	this.host = host;

	}

	 

	public int getStateReportPort() {

	return stateReportPort;

	}

	 

	public void setStateReportPort(int stateReportPort) {

	this.stateReportPort = stateReportPort;

	}

	 

	public String getEndFlag() {

	return endFlag;

	}

	 

	public void setEndFlag(String endFlag) {

	this.endFlag = endFlag;

	}

	 

	public int getScanTime() {

	return scanTime;

	}

	 

	public void setScanTime(int scanTime) {

	this.scanTime = scanTime;

	}

	 

	public static HashMap<Integer, String> getUserStateList() {

	return userStateList;

	}

	 

	public static int getWorkThreadNum() {

	return workThreadNum;

	}

	 

	public static int getSocketConnect() {

	return socketConnect;

	}

	//测试本函数的main函数

	public static void main(String arg[]){

	UserStateManage usm = new UserStateManage();

	usm.start();

	}

	}

