package com.chat;

import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;

public class ChatClient extends JFrame implements Runnable {
	
	Socket clientSocket = null; //클라이언트 선언
	
	PrintWriter out = null;
	BufferedReader in = null;
	
	JTextArea outputArea;
	JTextField inputField;
	JScrollPane js;
	
	public ChatClient(String title){ //디자인 구현
		super(title);
		setLayout(new BorderLayout());
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		js = new JScrollPane(outputArea);
		
		add(js,"Center");
		inputField = new JTextField();
		add(inputField,"South");
	
		inputField.addActionListener(new InputListener()); //이벤트 호출
	}
	
	public void addMessage(String msg){
		outputArea.append(msg); //전체 주고받는 메시지에 입력한 값을 추가한다.
	}
	
	public void connect(String host, int port){
		try{
			clientSocket = new Socket(host,port); //서버접속
			
			out = new PrintWriter(clientSocket.getOutputStream(),true);
			in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
		}catch(Exception e){
			System.err.println("입출력에러입니다.");
			System.exit(1);
		}
	}
	
	public void disconnect(){
		try{
			in.close();
			out.close();
			clientSocket.close();
		}catch(IOException e){	}
	}
	
	public void run(){
		try{
			while(true){
				addMessage(in.readLine() +"\n");
			}
		}catch(IOException e){
			disconnect();
		}
	}
	
	public static void main(String[] args) {
		ChatClient mf = new ChatClient("채팅");
		mf.pack();
		mf.setSize(500,300);
		mf.setVisible(true);
		mf.connect("192.168.0.6",9999);
		Thread thread = new Thread(mf);
		thread.start();
	}
	
	class InputListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String input = inputField.getText();
			inputField.setText("");
			try{
				out.println(input);
			}catch(Exception e1){
				e1.printStackTrace();
			}
		}
	}
}
