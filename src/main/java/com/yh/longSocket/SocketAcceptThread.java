package com.yh.longSocket;

import   java.io.IOException;   
import   java.net.Socket;   
import   java.net.ServerSocket;   

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public   class   SocketAcceptThread   extends   Thread{   
    
//          /**   
//            *   默认的监听端口   
//            */   
//          public   static   final   int   DEFAULT_PORT   =   5222;   
//          /**   
//            *   代表监听端口   
//            */   
//          private   int   serverPort;   
//          /**   
//            *   true表示线程没有停止，继续运行   
//            */   
//          private   boolean   notTerminated=true;   
//          /**   
//            *记录对象   
//            */   
//          private   Logger   log;   
//          /**   
//            *   监听服务   
//            */   
//          private   ServerSocket   serverSocket;   
//          public   SocketAcceptThread(LoggerFactory   l,int   port){   
//init(l,port);   
//          }   
//          public   SocketAcceptThread(Logger   l){   
//          init(l,SocketAcceptThread.DEFAULT_PORT);   
//          }   
//          /**   
//            *   初始化   
//            *   @param   l   
//            *   @param   port   
//            */   
//          public   void   init(Logger   l,int   port){   
//          this.log=l;   
//          this.serverPort=port;   
//          try   {   
//this.serverSocket=new   ServerSocket(serverPort);   
//}   catch   (IOException   e)   {   
//log.fatalError("SocketAcceptThread:serversocket初始化失败!!");   
//}   
//          }   
//          /**   
//            *   运行端口监听   一旦监听到进一步处理后继续去监听   
//            */   
//          public   void   run()   {   
//                  try   {   
//          while   (notTerminated)   {   
//      
//                                  Socket   sock   =   serverSocket.accept();   
//                                  if   (sock   !=   null)   {   
//                                          //这里添加处理   
//                                          try{   
//                                          System.out.println("SocketAcceptThread:创建了一个connection");   
//                                      new   Connection(sock);//创建了connection   
//                                          
//                                          }catch(IOException   e){   
//                                          log.error("SocketAcceptThread:建立连接错误"+e.getMessage());   
//                                          }   
//                                  }   
//                          }   
//    
//                  }   
//                  catch   (IOException   ie)   {   
//                          if   (notTerminated)   {   
//                                  log.error("SocketAcceptThread:socket建立错误"+ie.getMessage());   
//                          }   
//                  }   
//                  catch   (Exception   e)   {   
//                          log.error("SocketAcceptThread:socket监听错误"+e.getStackTrace());   
//                  }   
//                  try   {//无论如何也要关闭   
//                          ServerSocket   sSock   =   serverSocket;   
//                          serverSocket   =   null;   
//                          if   (sSock   !=   null)   {   
//                                  sSock.close();   
//                          }   
//                  }   
//                  catch   (IOException   e)   {   
//                          //   关闭不上就不管了   
//                  }   
//          }   
//          /**   
//            *   关闭监听.   
//            */   
//          public   void   shutdown()   {   
//                  notTerminated   =   false;   
//    
//                  try   {   
//                          ServerSocket   sSock   =   serverSocket;   
//                          serverSocket   =   null;   
//                          if   (sSock   !=   null)   {   
//                                  sSock.close();   
//                          }   
//                  }   
//                  catch   (IOException   e)   {   
//                          //   在关闭不了就不管了   
//                  }   
//    
//          }   
}   


