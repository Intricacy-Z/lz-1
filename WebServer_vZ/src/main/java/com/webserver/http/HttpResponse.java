package com.webserver.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpResponse {
	
	//״̬�������Ϣ
	private int statusCode=200;
	private String statusReason="OK";
	//��Ӧ���������Ϣ
	private File entity;
	//��������صķ���
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

	//����״̬��
	private void sendStatusLine(){
		System.out.println("HttpResponse:��ʼ����״̬��...");
		try {
			//����״̬��
			String line="HTTP/1.1"+" "+statusCode+" "+statusReason;
			out.write(line.getBytes("ISO8859-1"));
			out.write(13);
			out.write(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("HttpResponse:����״̬�����!");
	}
	//������Ӧͷ
	private void sendHeaders(){
		System.out.println("HttpResponse:��ʼ������Ӧͷ...");
		try {
			//������Ӧͷ
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
		System.out.println("HttpResponse:������Ӧͷ���!");
	}
	//������Ӧ����
	private void sendContent(){
		System.out.println("HttpResponse:��ʼ������Ӧ����...");
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
		System.out.println("HttpResponse:������Ӧ�������!");
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
