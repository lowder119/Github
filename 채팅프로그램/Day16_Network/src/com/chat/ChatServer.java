package com.chat;
import java.net.*;
import java.util.Vector;
import java.io.*;

public class ChatServer {
	Vector<ServiceThread> Clients;
	
	public ChatServer(){
		Clients = new Vector<>();
	}
	
	public void addClient(ServiceThread st){//클라이언트 추가
		Clients.addElement(st);
	}
	public void removeClient(ServiceThread st){//클라이언트 삭제
		Clients.removeElement(st);
	}
	public void sendMessageAll(String msg){//전체 메시지 출력
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
			serverSocket = new ServerSocket(port); //나 서버야
		}catch(Exception e){
			System.err.println("연결실패입니다.");
			System.exit(1);
		}
		System.out.println("서버\n" +serverSocket + "\n에서 연결을 기다립니다.");
		try{
			while(true){
				Socket serviceSocket = serverSocket.accept(); //클라이언트 접속
				
				//클라이언트 관리하는 객체, 클라이언트를 가진 객체를 생성한 후
				//Thread를 통해서 작업을 읽고 쓰는 Run 메서드를 명시한 후 실행한다.
				ServiceThread thread = new ServiceThread(server,serviceSocket);
				thread.start();
				server.addClient(thread);
			}
		}catch(Exception e){
			try{
				serverSocket.close(); //서버종료
			}catch(IOException e1){
				e1.printStackTrace();
			}
		}
		System.out.println("서버를 종료합니다.");
	}
}
