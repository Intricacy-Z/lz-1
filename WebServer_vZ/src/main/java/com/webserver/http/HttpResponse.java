package com.webserver.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpResponse {
	
	//状态行相关信息
	private int statusCode=200;
	private String statusReason="OK";
	//响应正文相关信息
	private File entity;
	//和连接相关的方法
	private Socket socket;
	private OutputStream out;
	public HttpResponse(Socket socket) {
		try {
			this.socket = socket;
			this.out=socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void flush(){
		sendStatusLine();
		sendHeaders();
		sendContent();
	}

	//发送状态行
	private void sendStatusLine(){
		System.out.println("HttpResponse:开始发送状态行...");
		try {
			//发送状态行
			String line="HTTP/1.1"+" "+statusCode+" "+statusReason;
			out.write(line.getBytes("ISO8859-1"));
			out.write(13);
			out.write(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("HttpResponse:发送状态行完毕!");
	}
	//发送响应头
	private void sendHeaders(){
		System.out.println("HttpResponse:开始发送响应头...");
		try {
			//发送响应头
//			String line="Content-Type: text/html";
//			out.write(line.getBytes("ISO8859-1"));
//			out.write(13);
//			out.write(10);
			
			String line="Content-Length: "+entity.length();
			out.write(line.getBytes("ISO8859-1"));
			out.write(13);
			out.write(10);
			
			out.write(13);
			out.write(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("HttpResponse:发送响应头完毕!");
	}
	//发送响应正文
	private void sendContent(){
		System.out.println("HttpResponse:开始发送响应正文...");
		try(
				FileInputStream fis=new FileInputStream(entity);
				) {
			
			int len=-1;
			byte[] data=new byte[1024*10];
			while ((len=fis.read(data))!=-1) {
				out.write(data,0,len);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("HttpResponse:发送响应正文完毕!");
	}
	public File getEntity() {
		return entity;
	}
	public void setEntity(File entity) {
		this.entity = entity;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusReason() {
		return statusReason;
	}
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}
	
}
