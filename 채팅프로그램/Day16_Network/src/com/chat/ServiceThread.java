package com.chat;
import java.net.*;
import java.io.*;

//클라이언트가 접속되면 메시지를 관리하고 전체 리턴하는 구조를 연동하게
//되는데 서버를 연동할 때 클라언트가 접속되어 연결하는 코드를
// 작성한 클래스이다.

//메소드를 관리하는 스트림을 지정하여 클라이언트가 보낸 메세지를
//메소드를 통해 전달하도록 지정한 클래스
public class ServiceThread extends Thread{
	private ChatServer server; //클라이언트의 정보, 삭제, 모든 메세지 관리하는 기능을 가진다.
	private Socket socket;
	
	String UserName; //클라이언트 이름
	
	PrintWriter out;
	BufferedReader in;
	
	public ServiceThread(ChatServer server, Socket socket ){
		this.server = server;
		this.socket = socket;
	}
	
	//클라이언트가 메시지를 입력하면 out 객체에 출력하는 메소드
	public void sendMessage(String msg)throws IOException{
		if(out != null)
			out.println(msg);
	}
	
	@Override
	public void run() { // 클라이언트 창에 출력하는 코드를 작성
		try{
			System.out.println("클라이언트\n" +socket+"\n에서 접속하였습니다.");
			
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			out.println();//줄바꿈
			out.println("UserName입력");
			UserName = in.readLine();
			sendMessage(UserName);
			server.sendMessageAll("#" + UserName + "님이 들어오셨습니다.");
			String inputLine;
			while( (inputLine =in.readLine()) != null){
				server.sendMessageAll("["+UserName+"]" +inputLine);
			}
			out.close();
			in.close();
			socket.close();
		}catch(IOException e){
			server.removeClient(this);
			server.sendMessageAll("#" +UserName+"님이 나가셨습니다.");
			System.out.println("클라이언트\n" +socket+"\n에서 접속이 끊겼습니다.");
		}
	}
}
