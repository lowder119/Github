package com.chat;
import java.net.*;
import java.io.*;

//Ŭ���̾�Ʈ�� ���ӵǸ� �޽����� �����ϰ� ��ü �����ϴ� ������ �����ϰ�
//�Ǵµ� ������ ������ �� Ŭ���Ʈ�� ���ӵǾ� �����ϴ� �ڵ带
// �ۼ��� Ŭ�����̴�.

//�޼ҵ带 �����ϴ� ��Ʈ���� �����Ͽ� Ŭ���̾�Ʈ�� ���� �޼�����
//�޼ҵ带 ���� �����ϵ��� ������ Ŭ����
public class ServiceThread extends Thread{
	private ChatServer server; //Ŭ���̾�Ʈ�� ����, ����, ��� �޼��� �����ϴ� ����� ������.
	private Socket socket;
	
	String UserName; //Ŭ���̾�Ʈ �̸�
	
	PrintWriter out;
	BufferedReader in;
	
	public ServiceThread(ChatServer server, Socket socket ){
		this.server = server;
		this.socket = socket;
	}
	
	//Ŭ���̾�Ʈ�� �޽����� �Է��ϸ� out ��ü�� ����ϴ� �޼ҵ�
	public void sendMessage(String msg)throws IOException{
		if(out != null)
			out.println(msg);
	}
	
	@Override
	public void run() { // Ŭ���̾�Ʈ â�� ����ϴ� �ڵ带 �ۼ�
		try{
			System.out.println("Ŭ���̾�Ʈ\n" +socket+"\n���� �����Ͽ����ϴ�.");
			
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			out.println();//�ٹٲ�
			out.println("UserName�Է�");
			UserName = in.readLine();
			sendMessage(UserName);
			server.sendMessageAll("#" + UserName + "���� �����̽��ϴ�.");
			String inputLine;
			while( (inputLine =in.readLine()) != null){
				server.sendMessageAll("["+UserName+"]" +inputLine);
			}
			out.close();
			in.close();
			socket.close();
		}catch(IOException e){
			server.removeClient(this);
			server.sendMessageAll("#" +UserName+"���� �����̽��ϴ�.");
			System.out.println("Ŭ���̾�Ʈ\n" +socket+"\n���� ������ ������ϴ�.");
		}
	}
}
