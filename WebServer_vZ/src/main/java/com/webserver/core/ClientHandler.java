package com.webserver.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;

public class ClientHandler implements Runnable{
	private Socket socket;

	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			HttpRequest request=new HttpRequest(socket);
			HttpResponse response=new HttpResponse(socket);
			
			String path=request.getUri();
			File file=new File("./webapps"+path);
			if (file.exists()) {
				System.out.println("��Դ���ҵ�");
				
				
				response.setEntity(file);
				response.flush();
				
			} else {
				System.out.println("��Դû�ҵ�");
				//��Ӧ404ҳ��
				File notFoundPage=new File("./webapps/root/404.html");
				//����״̬����
				response.setStatusCode(404);
				response.setStatusReason("NOT FOUND");
				response.setEntity(notFoundPage);
				
			}
			//3.��Ӧ�ͻ���
			response.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	
	
	
}
