package com.chat;
import java.net.*;
import java.util.Vector;
import java.io.*;

public class ChatServer {
	Vector<ServiceThread> Clients;
	
	public ChatServer(){
		Clients = new Vector<>();
	}
	
	public void addClient(ServiceThread st){//Ŭ���̾�Ʈ �߰�
		Clients.addElement(st);
	}
	public void removeClient(ServiceThread st){//Ŭ���̾�Ʈ ����
		Clients.removeElement(st);
	}
	public void sendMessageAll(String msg){//��ü �޽��� ���
		try{
			for(int i=0; i<Clients.size() ; i++){
				ServiceThread st =( (ServiceThread)Clients.elementAt(i));
				st.sendMessage(msg);
			}
		}catch(Exception e){
			
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ChatServer server;
		ServerSocket serverSocket = null;
		int port = 9999;
		server = new ChatServer(); 
		
		try{
			serverSocket = new ServerSocket(port); //�� ������
		}catch(Exception e){
			System.err.println("��������Դϴ�.");
			System.exit(1);
		}
		System.out.println("����\n" +serverSocket + "\n���� ������ ��ٸ��ϴ�.");
		try{
			while(true){
				Socket serviceSocket = serverSocket.accept(); //Ŭ���̾�Ʈ ����
				
				//Ŭ���̾�Ʈ �����ϴ� ��ü, Ŭ���̾�Ʈ�� ���� ��ü�� ������ ��
				//Thread�� ���ؼ� �۾��� �а� ���� Run �޼��带 ����� �� �����Ѵ�.
				ServiceThread thread = new ServiceThread(server,serviceSocket);
				thread.start();
				server.addClient(thread);
			}
		}catch(Exception e){
			try{
				serverSocket.close(); //��������
			}catch(IOException e1){
				e1.printStackTrace();
			}
		}
		System.out.println("������ �����մϴ�.");
	}
}
