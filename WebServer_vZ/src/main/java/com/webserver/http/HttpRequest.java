package com.webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
	private String method; 

	private String uri;

	private String protocol;
	
	private Map<String, String> headers=new HashMap<String, String>();

	private Socket socket;

	private InputStream in;

	public HttpRequest(Socket socket) {
		try {
			this.socket = socket;
			this.in=socket.getInputStream();
			
			parseRequestLine();
			parseHeaders();
			parseContent();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
	/*
	 * 解析请求行
	 */
	private void parseRequestLine(){
		System.out.println("HttpRequest:正在解析请求行...");
		try {
			String line=readLine();
			System.out.println(line);
			
			String[] data=line.split("\\s");
			method=data[0];
			uri=data[1];
			protocol=data[2];
			
			System.out.println("method:"+method);
			System.out.println("uri:"+uri);
			System.out.println("protocol:"+protocol);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("HTTPRequest:解析请求行完毕!");
	}
	/*
	 * 解析消息头
	 */
	private void parseHeaders(){
		System.out.println("HttpRequest:正在解析消息头...");
		try {
			while (true) {
				String line=readLine();
				if ("".equals(line)) {
					break;
				}
				System.out.println(line);
				
				String[] data=line.split(":\\s");
				headers.put(data[0], data[1]);
			}
			System.out.println("headers:"+headers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("HttpRequest:解析消息正文完毕!");
	}
	/*
	 * 解析消息正文
	 */
	private void parseContent(){
		System.out.println("HttpRequest:正在解析消息正文...");
		System.out.println("HttpRequest:解析消息正文完毕!");
	}
	
	private String readLine() throws IOException{
		int c1=-1;
		int c2=-1;
		StringBuilder builder=new StringBuilder();
		while ((c2=in.read())!=-1) {
			if (c1==13&&c2==10) {
				break;
			}
			builder.append((char)c2);
			c1=c2;
		}
		return builder.toString().trim();
	}
	public String getMethod() {
		return method;
	}
	public String getUri() {
		return uri;
	}
	public String getProtocol() {
		return protocol;
	}
	public String getHeaders(String name) {
		return headers.get(name);
	}
	
}
