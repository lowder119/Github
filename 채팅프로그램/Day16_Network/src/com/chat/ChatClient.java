package com.chat;

import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;

public class ChatClient extends JFrame implements Runnable {
	
	Socket clientSocket = null; //Ŭ���̾�Ʈ ����
	
	PrintWriter out = null;
	BufferedReader in = null;
	
	JTextArea outputArea;
	JTextField inputField;
	JScrollPane js;
	
	public ChatClient(String title){ //������ ����
		super(title);
		setLayout(new BorderLayout());
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		js = new JScrollPane(outputArea);
		
		add(js,"Center");
		inputField = new JTextField();
		add(inputField,"South");
	
		inputField.addActionListener(new InputListener()); //�̺�Ʈ ȣ��
	}
	
	public void addMessage(String msg){
		outputArea.append(msg); //��ü �ְ�޴� �޽����� �Է��� ���� �߰��Ѵ�.
	}
	
	public void connect(String host, int port){
		try{
			clientSocket = new Socket(host,port); //��������
			
			out = new PrintWriter(clientSocket.getOutputStream(),true);
			in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
		}catch(Exception e){
			System.err.println("����¿����Դϴ�.");
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
		ChatClient mf = new ChatClient("ä��");
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
