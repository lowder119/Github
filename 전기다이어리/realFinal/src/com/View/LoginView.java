package com.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.biz.LoginBiz;
import com.entity.LoginVO;

public class LoginView extends JFrame /*implements ActionListener*/  {
	
	public static Scanner sc = new Scanner(System.in);

	private JLabel lID, lPW;
	private JTextField tfID;
	private JPasswordField tfPW;	
	private JButton btLogin, btJoin;
	private JPanel pCenter, pSouth;
	private LoginEvent le;
	
	public LoginView() {		
		this.setLayout(new BorderLayout());
		setIconImage(new ImageIcon("Icon.png").getImage());
		pCenter = new JPanel(new GridLayout(2,2));
		pSouth = new JPanel(new GridLayout(1,2));
		
		lID = new JLabel("아이디 : ");
		lPW = new JLabel("패스워드 : ");
		
		tfID = new JTextField("");
		tfPW = new JPasswordField("");
		
		btLogin = new JButton("로그인");
		btJoin = new JButton("회원가입");
		
		InitMember();
		
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}			
		});
	}
	public void InitMember() {
		btLogin.addActionListener(new LoginEvent());
		btJoin.addActionListener(new LoginEvent());
		tfPW.addKeyListener(new LoginEvent());
		
		pCenter.add(lID);			pCenter.add(tfID);			
		pCenter.add(lPW);			pCenter.add(tfPW);
		pSouth.add(btLogin);		pSouth.add(btJoin);
		
		add(pCenter, BorderLayout.CENTER);
		add(pSouth, BorderLayout.SOUTH);
		
		setTitle("로그인");
		
		// 로그인창
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width / 4;		//	- 모니터의 가로 길이 / 4
		int height = screenSize.height / 6;		// - 모니터의 세로 길이  / 6
		
		// 모니터의 가운데 창이 위치
		setBounds((screenSize.width / 2) - (width / 2), (screenSize.height / 2) - (height / 2), width, height);
		setVisible(true);		
	}
	
	public boolean isPossibleLogin(String id, String pw) {
		LoginBiz lb = new LoginBiz();
		List<LoginVO> res = lb.getSelectAllUser();
		
		for ( LoginVO r : res ) {		
			if ( r.getId().equals(id) ) {
				if ( r.getPassWord().equals(pw) ) {				
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean isPossibleJoin(String id, String pw) {
		LoginBiz lb = new LoginBiz();
		List<LoginVO> res = lb.getSelectAllUser();				
		
		for ( LoginVO r : res ) {		
			if ( r.getId().equals(id) ) {
				SetMsgBox("동일한 아이디가 존재합니다", true);
				return false;
			}
		}
		
		return true;
	}
	
	public void DoItJoin(String id, String pw) {
		LoginBiz lb = new LoginBiz();
		lb.insertLoginEntity(new LoginVO(id, pw));
	}
	
	public void SetMsgBox(String text, boolean option ) {
		
		if ( option )		
			JOptionPane.showMessageDialog(null, text, null, JOptionPane.WARNING_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, text, null, JOptionPane.NO_OPTION);
	}
	
	public void ClickedLoginButton() {
		// 아이디 & 패스워드에 공백 입력된 상태일 때 경고 메시지
		if ( tfID.getText().equals("") || tfPW.getText().equals("") ) {
			SetMsgBox("아이디와 비밀번호를 다시 확인해주세요", true);			
		}		
				
		else if ( isPossibleLogin(tfID.getText(), tfPW.getText()) ) {	
			setVisible(false);
			ElectricView go = new ElectricView(tfID.getText());	
			//System.out.println("-=["+tfID.getText()+" Log in]=-");
			
		}		
		else {
			SetMsgBox("아이디와 비밀번호를 다시 확인해주세요", true);
		}
		
		tfID.setText("");
		tfPW.setText("");
		tfID.requestFocusInWindow();
	}
	
	public void ClickedJoinButton() {
		// 아이디 & 패스워드에 공백 입력된 상태일 때 경고 메시지
		if ( tfID.getText().equals("") || tfPW.getText().equals("") ) {
			SetMsgBox("아이디와 비밀번호를 다시 확인해주세요", true);			
		}						
		else if ( isPossibleJoin(tfID.getText(), tfPW.getText()) ) {	
			DoItJoin(tfID.getText(), tfPW.getText());
			SetMsgBox("가입 되었습니다", false);
		}
		
		tfID.setText("");
		tfPW.setText("");
		tfID.requestFocusInWindow();
	}
		
	class LoginEvent extends KeyAdapter implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
					
			JButton tempButton = (JButton)e.getSource();		
			
			if ( tempButton.equals(btLogin) ) {			// 로그인 버튼클릭
				ClickedLoginButton();					
			}
			else if ( tempButton.equals(btJoin) ) {	// 회원가입 버튼클릭
				ClickedJoinButton();					
			}			
		}
		
		@Override
		public void keyPressed(KeyEvent arg0) {
			if ( arg0.getKeyCode() == KeyEvent.VK_ENTER ) {
				btLogin.doClick();	
			}				
		}			
	}
}
