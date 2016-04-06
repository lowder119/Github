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
		
		lID = new JLabel("���̵� : ");
		lPW = new JLabel("�н����� : ");
		
		tfID = new JTextField("");
		tfPW = new JPasswordField("");
		
		btLogin = new JButton("�α���");
		btJoin = new JButton("ȸ������");
		
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
		
		setTitle("�α���");
		
		// �α���â
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width / 4;		//	- ������� ���� ���� / 4
		int height = screenSize.height / 6;		// - ������� ���� ����  / 6
		
		// ������� ��� â�� ��ġ
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
				SetMsgBox("������ ���̵� �����մϴ�", true);
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
		// ���̵� & �н����忡 ���� �Էµ� ������ �� ��� �޽���
		if ( tfID.getText().equals("") || tfPW.getText().equals("") ) {
			SetMsgBox("���̵�� ��й�ȣ�� �ٽ� Ȯ�����ּ���", true);			
		}		
				
		else if ( isPossibleLogin(tfID.getText(), tfPW.getText()) ) {	
			setVisible(false);
			ElectricView go = new ElectricView(tfID.getText());	
			//System.out.println("-=["+tfID.getText()+" Log in]=-");
			
		}		
		else {
			SetMsgBox("���̵�� ��й�ȣ�� �ٽ� Ȯ�����ּ���", true);
		}
		
		tfID.setText("");
		tfPW.setText("");
		tfID.requestFocusInWindow();
	}
	
	public void ClickedJoinButton() {
		// ���̵� & �н����忡 ���� �Էµ� ������ �� ��� �޽���
		if ( tfID.getText().equals("") || tfPW.getText().equals("") ) {
			SetMsgBox("���̵�� ��й�ȣ�� �ٽ� Ȯ�����ּ���", true);			
		}						
		else if ( isPossibleJoin(tfID.getText(), tfPW.getText()) ) {	
			DoItJoin(tfID.getText(), tfPW.getText());
			SetMsgBox("���� �Ǿ����ϴ�", false);
		}
		
		tfID.setText("");
		tfPW.setText("");
		tfID.requestFocusInWindow();
	}
		
	class LoginEvent extends KeyAdapter implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
					
			JButton tempButton = (JButton)e.getSource();		
			
			if ( tempButton.equals(btLogin) ) {			// �α��� ��ưŬ��
				ClickedLoginButton();					
			}
			else if ( tempButton.equals(btJoin) ) {	// ȸ������ ��ưŬ��
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
