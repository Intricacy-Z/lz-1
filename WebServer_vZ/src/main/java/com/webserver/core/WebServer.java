package com.webserver.core;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
	private ServerSocket server;
	
	public WebServer() {
		try {
			System.out.println("正在启动服务端..");
			server=new ServerSocket(8088);
			System.out.println("服务端启动完毕啦~");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start(){
		try {
			System.out.println("等待客户端的连接...");
			Socket socket=server.accept();
			System.out.println("客户端连接完毕啦~");
			
			ClientHandler handler=new ClientHandler(socket);
			Thread t=new Thread(handler);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		WebServer server=new WebServer();
		server.start();
	}
}
