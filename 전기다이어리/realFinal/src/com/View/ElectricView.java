
package com.View;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import com.biz.Experience_Biz;
import com.biz.Progressive_Biz;
import com.biz.ElectricBiz;
import com.dao.Experience_DAO;
import com.entity.ApplianceVO;
import com.entity.ProgressiveVO;
import com.entity.ElectricVo;
import com.function.BillCal;
import com.function.Builder;
import com.function.DayDeco;
import com.function.Evaluation;
import com.toedter.calendar.JCalendar;

public class ElectricView implements ActionListener, MouseListener, ItemListener, PropertyChangeListener{

	private static Date cursor=Calendar.getInstance().getTime();
	private static String stack;
	private static JFreeChart chart;
	private static ChartPanel StatChart;
	private static SimpleDateFormat sdf = new SimpleDateFormat("날짜 : YYYY년 MM월 dd일");    // hh시 mm분
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY/MM/dd");
	private static ArrayList<JButton> days;

	private static JTable StatLTable;
	private static JTable StatRTable;
	private static JTextField StatInDateField;
	private static JTextField StatInUseField;
	private static JFrame jf;
	private static JTabbedPane tabbedPane;
	private static JPanel Stat_Page;
	private static JPanel StatRPanel;
	private static JPanel StatGraph;
	private static JPanel StatInputPanel;
	private static ScrollPane StatOutputPanel;
	private static JCalendar StatCalendar;
	private static JButton StatInputButton;
	private static JTabbedPane StatSubTab;
	private static JPanel StatCalPanel;
	private static JPanel StatTablePanel; 
	private static ScrollPane StatTableScrollPane;
	private static Panel panel;
	private static JPanel panel_2;
	private static JSeparator separator_2; 
	private static JPanel Pro_Bottom;
	private static JLabel Interduce2;
	///////////////////////////////////////////////////////////////////////

	private JPanel Progressive_Page;//add
	private JPanel Pro_Mid_Left;
	private ImageIcon icon;
	private JButton Pro_Image1;
	private JPanel Pro_Mid_Right;
	private ButtonGroup Pro_Buttongroup;
	private JRadioButton Pro_Button1;
	private String r1 = "저압(주택)";
	private JRadioButton Pro_Button2;
	private JLabel Pro_Power;
	private JTextField Pro_Power_Input;
	private JLabel Pro_kWh;
	private JButton Pro_Input_Button;
	private JLabel Pro_Power_Charge;
	private int Power_Charge;
	private String Power_Charge2 = null; 
	private 	JLabel TPage_Power_ChargeResult;
	private JTextArea Memo_Input; 


	////////////////////////////////////////////
	private JPanel Experience_Page;//add
	private JPanel Experience_Mid_Right;
	private JLabel ExPage_Message1;
	private JLabel ExPage_Message2;
	private JLabel ExPage_Message3;
	private JTable Ex_table;
	private JScrollPane Ex_Scroll;
	private JPanel Experience_Mid_Left;
	private JLabel Menu;
	private JButton ExPage_Reset;
	private JCheckBox ch1;private JCheckBox ch2;private JCheckBox ch3;
	private JCheckBox ch4;private JCheckBox ch5;private JCheckBox ch6;
	private JCheckBox ch7;private JCheckBox ch8;private JCheckBox ch9;
	private JCheckBox ch10;private JCheckBox ch11;private JCheckBox ch12;
	private JCheckBox ch13;private JCheckBox ch14;private JCheckBox ch15;
	private JCheckBox ch16;private JCheckBox ch17;private JCheckBox ch18;
	private JCheckBox ch19;private JCheckBox ch20;private JCheckBox ch21;
	private JLabel ExPage_CalPower;
	private JLabel ExPage_SumkWh;
	private JLabel ExPage_PowerCharge;
	private String data[][];
	private JLabel ExPage_PowerCharge2;
	private JButton ExPage_detail;
	private SubFrame sf;
	private int sum2;
	private JLabel ExPage_SumkWh2;
	private JButton ExPage_Save;
	private String AppName;
	////////////////////////가전 참조변수////////////////////////
	///////////////////////////////////////////////////
	Experience_Biz ex_Tv;
	Experience_DAO experience_page_con;
	static int table_index=0;
	Experience_Biz experience_biz = new Experience_Biz();
	Map<Integer,ApplianceVO> ex_hm;



	///////////////////////////////////////////////////
	private JPanel Login_Page;
	private String myID;
	private ElectricBiz eb;
	private DayDeco dd;
	private JPanel CheckList_Page;
	private JLabel CL_Massage;
	private JPanel CList_Left;
	private JCheckBox CL_ch1;private JCheckBox CL_ch2;
	private JCheckBox CL_ch3;private JCheckBox CL_ch4;private JCheckBox CL_ch5;
	private static int  CL_Score =0;
	Evaluation ev = new Evaluation();
	private JPanel CList_Right;
	private JLabel CL_image;
	private JLabel CL_Text;
	///////////////////////////////////

	private static int m,y; //날짜
	private JLabel kWh_label, power_label, VATlabel;
	private JLabel lblBasicprice, lblPowerprice,  lblTotal;
	private JLabel lblBase, lblPower, sumBPlabel;
	private JLabel powerTaxlabel, totalPricelabel;
	private JLabel sumBPvalue, lVat_value, Tax_value,price_value;
	private JLabel guessLabel;
	private BillCal bc;
	
	
	
	
	
	
	
	
	
	
	public ElectricView(String id){
		dd=new DayDeco();
		eb=new ElectricBiz();
		myID=id;
		//System.out.println(myID);
		Builder cb = new Builder();

		DefaultTableModel model = new DefaultTableModel(cb.getColNames(), 0);		
		model.addRow(cb.getColNames());

		// ㅅ
		jf = new JFrame("Electric Bill Chaser");
		jf.setBounds(120,120,1004,516);
		jf.getContentPane().setLayout(null);
		jf.setIconImage(new ImageIcon("Icon.png").getImage());
		jf.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}


		});

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);	
		tabbedPane.setBounds(0, 0, 988, 456);
		jf.getContentPane().add(tabbedPane);

		//		Login_Page = new JPanel();
		//		tabbedPane.addTab("New tab", null, Login_Page, null);
		//		Login_Page.setLayout(null);
		//		
		//		JButton btnEnterance = new JButton("Enterance");
		//		
		//		btnEnterance.setBounds(216, 206, 97, 23);
		//		Login_Page.add(btnEnterance);

		Stat_Page = new JPanel();

		Stat_Page.setLayout(null);


		StatRPanel = new JPanel();
		StatRPanel.setBounds(430, 0, 553, 421);
		Stat_Page.add(StatRPanel);
		StatRPanel.setLayout(null);

		StatGraph = new JPanel();
		StatGraph.setBounds(0, 0, 553, 323);
		StatRPanel.add(StatGraph);


		StatInputPanel = new JPanel();
		StatInputPanel.setBounds(304, 375, 249, 46);
		StatRPanel.add(StatInputPanel);
		StatInputPanel.setLayout(null);

		StatInDateField = new JTextField();
		StatInDateField.setBounds(42, 0, 122, 21);
		StatInputPanel.add(StatInDateField);
		StatInDateField.setColumns(10);

		StatInUseField = new JTextField();
		StatInUseField.setBounds(42, 25, 122, 21);
		StatInputPanel.add(StatInUseField);
		StatInUseField.setColumns(10);


		JLabel StatInDateLabel = new JLabel("\uB0A0\uC9DC");
		StatInDateLabel.setBounds(1, 3, 43, 15);
		StatInputPanel.add(StatInDateLabel);

		JLabel StatInUseLabel = new JLabel("\uC0AC\uC6A9\uB7C9");
		StatInUseLabel.setBounds(0, 28, 43, 15);
		StatInputPanel.add(StatInUseLabel);


		StatCalendar = new JCalendar();

		StatOutputPanel = new ScrollPane();
		StatOutputPanel.setBounds(12, 375, 286, 36);
		StatRPanel.add(StatOutputPanel);









		cb.sqlChartBuild(myID,StatRTable,"select * from elec where id like '"+myID+"' and day='"
				+ElectricVo.date2String(cursor)+"' order by day asc",cb.getG());
		//		cb.sqlChartBuild(myID,StatRTable,"select * from elec where id like '"+myID+"' order by day asc",cb.getG());






		chart = cb.graphBuild(myID,ElectricVo.date2String(cursor));
		StatGraph.setLayout(null);
		StatChart=new ChartPanel(chart);
		StatChart.setBounds(0, 0, 553, 323);
		StatChart.setPreferredSize(new Dimension(453, 346));
		StatGraph.add(StatChart);






		StatInputButton = new JButton("\uC785\uB825");

		StatInputButton.setBounds(176, 0, 61, 21);
		StatInputPanel.add(StatInputButton);

		JButton StatDelButton = new JButton("\uC0AD\uC81C");

		StatDelButton.setBounds(176, 25, 61, 21);
		StatInputPanel.add(StatDelButton);






		////////////////////////////////////









		///여기까지




		JLabel StatRLabel2 = new JLabel("");
		StatRLabel2.setBounds(0, 343, 553, 15);
		StatRPanel.add(StatRLabel2);


		StatRTable = new JTable();
		StatRTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		StatRTable.setBounds(10, 382, 282, 32);
		StatOutputPanel.add(StatRTable);





		JLabel StatRLabel1 = new JLabel("");
		StatRLabel1.setBounds(0, 325, 553, 15);
		StatRPanel.add(StatRLabel1);

		StatSubTab = new JTabbedPane(JTabbedPane.TOP);
		StatSubTab.setBounds(0, 0, 430, 421);
		Stat_Page.add(StatSubTab);

		StatCalPanel = new JPanel();
		StatSubTab.addTab("달력", null, StatCalPanel, null);
		StatCalPanel.setLayout(null);


		if(cb.getG().size()>0){
			StatRLabel1.setText(String.format("%d년%d월말예상사용량: %.1f kwh ", cb.getG().get(0).getD().getYear()+1900,cb.getG().get(0).getD().getMonth()+1,cb.getGuess()));
			double d=cb.getG().get(cb.getG().size()-1).getUsed()-cb.getG().get(0).getUsed();

			if(d<0){d+=10000;}
			StatRLabel2.setText(String.format("%d년%d월 %d ~ %d일 사용량: %.1f kwh ", cb.getG().get(0).getD().getYear()+1900,cb.getG().get(0).getD().getMonth()+1,cb.getG().get(0).getD().getDate(),cb.getG().get(cb.getG().size()-1).getD().getDate(),d));
			//			cb.setGuess(0);

		}
		Calendar t=Calendar.getInstance();








		///////////////////////////////////////////////////////////////////////////////////
		//누진세계산패널
		Progressive_Page = new JPanel();
		tabbedPane.addTab("누진세계산", Progressive_Page);
		Progressive_Page.setLayout(null);

		Pro_Mid_Left = new JPanel();
		Pro_Mid_Left.setBounds(0, 0, 418, 428);
		Progressive_Page.add(Pro_Mid_Left);

		icon = new ImageIcon("image.jpg");
		Pro_Mid_Left.setLayout(new GridLayout(0, 1, 0, 0));
		Pro_Image1 = new JButton("",icon);
		Pro_Image1.setBackground(Color.WHITE);
		Pro_Mid_Left.add(Pro_Image1);

		Pro_Mid_Right = new JPanel();
		Pro_Mid_Right.setBounds(430, 0, 555, 427);
		Progressive_Page.add(Pro_Mid_Right);
		Pro_Mid_Right.setLayout(null);

		// 라디오 버튼
		Pro_Buttongroup = new ButtonGroup();
		Pro_Button1 = new JRadioButton("저압(주택)",true);
		Pro_Button1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		Pro_Button1.setBounds(144, 45, 121, 23);
		Pro_Mid_Right.add(Pro_Button1);

		Pro_Button2 = new JRadioButton("고압(주택)");
		Pro_Button2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		Pro_Button2.setBounds(322, 45, 121, 23);
		Pro_Mid_Right.add(Pro_Button2);

		Pro_Buttongroup.add(Pro_Button1);
		Pro_Buttongroup.add(Pro_Button2);
		// 라디오 버튼

		Pro_Power = new JLabel("월 전기소비량 입력");
		Pro_Power.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		Pro_Power.setBounds(79, 114, 175, 30);
		Pro_Mid_Right.add(Pro_Power);

		Pro_Power_Input = new JTextField();
		Pro_Power_Input.setBounds(266, 114, 116, 30);
		Pro_Mid_Right.add(Pro_Power_Input);
		Pro_Power_Input.setColumns(10);

		Pro_kWh = new JLabel("kWh");
		Pro_kWh.setFont(new Font("굴림", Font.PLAIN, 15));
		Pro_kWh.setBounds(394, 117, 57, 29);
		Pro_Mid_Right.add(Pro_kWh);

		TPage_Power_ChargeResult = new JLabel();

		Pro_Input_Button = new JButton("\uB204\uC9C4\uC138 \uACC4\uC0B0\uD558\uAE30");
		Pro_Input_Button.setFont(new Font("굴림", Font.PLAIN, 15));

		Pro_Power_Charge = new JLabel("월 전기요금");
		Pro_Power_Charge.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		Pro_Power_Charge.setBounds(28, 236, 136, 30);
		Pro_Mid_Right.add(Pro_Power_Charge);

		Memo_Input = new JTextArea("");
		Memo_Input.setBackground(SystemColor.menu);
		Memo_Input.setEditable(false);
		Memo_Input.setSize(420,76);
		Memo_Input.setLocation(79,292);
		Pro_Mid_Right.add(Memo_Input);

		Pro_Input_Button.setBounds(374, 178, 142, 35);
		Pro_Mid_Right.add(Pro_Input_Button);

		addPro_Listener();
		// TODO Auto-generated method stub
		//체험 패널
		Experience_Page = new JPanel();
		tabbedPane.addTab("전기요금체험관", Experience_Page);
		Experience_Page.setLayout(null);

		Experience_Mid_Right = new JPanel();
		Experience_Mid_Right.setBounds(430, 0, 553, 427);
		Experience_Page.add(Experience_Mid_Right);
		Experience_Mid_Right.setLayout(null);

		ExPage_Message1 = new JLabel("\uC120\uD0DD\uD558\uC2E0 \uC81C\uD488\uC758 \uC18C\uBE44\uC804\uB825, \uC218\uB7C9, \uC0AC\uC6A9\uC2DC\uAC04\uC744 \uC785\uB825\uD558\uBA74");
		ExPage_Message1.setFont(new Font("굴림", Font.PLAIN, 13));
		ExPage_Message1.setHorizontalAlignment(SwingConstants.CENTER);
		ExPage_Message1.setBounds(10, 247, 514, 15);
		Experience_Mid_Right.add(ExPage_Message1);

		ExPage_Message2 = new JLabel("\uC804\uAE30\uC694\uAE08\uC774 \uACC4\uC0B0\uB418\uC5B4\uC9D1\uB2C8\uB2E4.");
		ExPage_Message2.setFont(new Font("굴림", Font.PLAIN, 13));
		ExPage_Message2.setForeground(Color.RED);
		ExPage_Message2.setHorizontalAlignment(SwingConstants.CENTER);
		ExPage_Message2.setBounds(45, 273, 463, 15);
		Experience_Mid_Right.add(ExPage_Message2);

		ExPage_Message3 = new JLabel("\u203B \uC120\uD0DD\uD558\uC2E0 \uC81C\uD488\uC758 \uC18C\uBE44\uC804\uB825, \uC218\uB7C9, \uC0AC\uC6A9\uC2DC\uAC04\uB294 \uC218\uC815\uD560 \uC218 \uC788\uC2B5\uB2C8\uB2E4.");
		ExPage_Message3.setForeground(Color.DARK_GRAY);
		ExPage_Message3.setHorizontalAlignment(SwingConstants.LEFT);
		ExPage_Message3.setFont(new Font("굴림", Font.ITALIC, 11));
		ExPage_Message3.setBounds(12, 298, 529, 15);
		Experience_Mid_Right.add(ExPage_Message3);

		// TODO Auto-generated method stub
		data = new String[21][7];
		String col[] ={"기기명","소비전력(W)","수량","사용시간","사용주기","월사용시간","월간사용량(kWh)"};
		Ex_table = new JTable(data,col);
		Ex_table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		Ex_Scroll = new JScrollPane(Ex_table);
		Ex_Scroll.setBounds(0, 0, 553, 237);
		Ex_table.setVisible(true);
		Ex_table.setBounds(12, 228, 429, -217);

		Experience_Mid_Right.add(Ex_Scroll);

		ExPage_CalPower = new JLabel("□ 전기요금 계산");
		ExPage_CalPower.setFont(new Font("굴림", Font.PLAIN, 13));
		ExPage_CalPower.setBounds(45, 327, 104, 15);
		Experience_Mid_Right.add(ExPage_CalPower);

		ExPage_SumkWh = new JLabel("월간 전력 사용량 합계 : ");
		ExPage_SumkWh.setBounds(92, 356, 147, 15);
		Experience_Mid_Right.add(ExPage_SumkWh);

		ExPage_PowerCharge = new JLabel("전기요금 : ");
		ExPage_PowerCharge.setBounds(321, 356, 98, 15);
		Experience_Mid_Right.add(ExPage_PowerCharge);

		Experience_Mid_Left = new JPanel();
		Experience_Mid_Left.setBounds(0, 0, 418, 427);
		Experience_Page.add(Experience_Mid_Left);
		Experience_Mid_Left.setLayout(null);

		Menu = new JLabel("가전제품목록");
		Menu.setHorizontalAlignment(SwingConstants.CENTER);
		Menu.setBounds(155, 20, 118, 15);
		Experience_Mid_Left.add(Menu);

		ch1 = new JCheckBox("TV");
		ch1.setBounds(32, 51, 115, 23);
		Experience_Mid_Left.add(ch1);

		ch2 = new JCheckBox("에어컨");
		ch2.setBounds(156, 51, 115, 23);
		Experience_Mid_Left.add(ch2);

		ch3 = new JCheckBox("공기청정기");
		ch3.setBounds(282, 51, 115, 23);
		Experience_Mid_Left.add(ch3);

		ch4 = new JCheckBox("오디오");
		ch4.setBounds(32, 99, 115, 23);
		Experience_Mid_Left.add(ch4);

		ch5 = new JCheckBox("선풍기");
		ch5.setBounds(156, 99, 115, 23);
		Experience_Mid_Left.add(ch5);

		ch6 = new JCheckBox("형광등");
		ch6.setBounds(282, 99, 115, 23);
		Experience_Mid_Left.add(ch6);

		ch7 = new JCheckBox("가습기");
		ch7.setBounds(32, 144, 115, 23);
		Experience_Mid_Left.add(ch7);

		ch8 = new JCheckBox("냉장고");
		ch8.setBounds(156, 144, 115, 23);
		Experience_Mid_Left.add(ch8);

		ch9 = new JCheckBox("전기밥솥(취사)");
		ch9.setBounds(282, 144, 115, 23);
		Experience_Mid_Left.add(ch9);

		ch10 = new JCheckBox("전자레인지");
		ch10.setBounds(32, 191, 115, 23);
		Experience_Mid_Left.add(ch10);

		ch11 = new JCheckBox("믹서기");
		ch11.setBounds(156, 191, 115, 23);
		Experience_Mid_Left.add(ch11);

		ch12 = new JCheckBox("컴퓨터");
		ch12.setBounds(282, 191, 115, 23);
		Experience_Mid_Left.add(ch12);

		ch13 = new JCheckBox("모니터");
		ch13.setBounds(32, 235, 115, 23);
		Experience_Mid_Left.add(ch13);

		ch14 = new JCheckBox("프린터");
		ch14.setBounds(156, 235, 115, 23);
		Experience_Mid_Left.add(ch14);

		ch15 = new JCheckBox("휴대폰충전기");
		ch15.setBounds(282, 235, 115, 23);
		Experience_Mid_Left.add(ch15);

		ch16 = new JCheckBox("DVD Player");
		ch16.setBounds(32, 283, 115, 23);
		Experience_Mid_Left.add(ch16);

		ch17 = new JCheckBox("김치냉장고");
		ch17.setBounds(156, 283, 115, 23);
		Experience_Mid_Left.add(ch17);

		ch18 = new JCheckBox("세탁기");
		ch18.setBounds(282, 283, 115, 23);
		Experience_Mid_Left.add(ch18);

		ch19 = new JCheckBox("진공청소기");
		ch19.setBounds(32, 329, 115, 23);
		Experience_Mid_Left.add(ch19);

		ch20 = new JCheckBox("헤어드라이기");
		ch20.setBounds(156, 329, 115, 23);
		Experience_Mid_Left.add(ch20);

		ch21 = new JCheckBox("비데");
		ch21.setBounds(282, 329, 115, 23);
		Experience_Mid_Left.add(ch21);
		
		////////////////////////////////////////////////////////
		
		Progressive_Biz ProBiz3= new Progressive_Biz();
		
		
		
		
		
		
		
		
		
///////////////////////////////////////////////////////////////////
		// TODO Auto-generated method stub
		//////////////////수정///////////////
		ExPage_SumkWh2 = new JLabel("New label"); //위치 바꾸기
		ExPage_PowerCharge2 = new JLabel("New label"); //위치 바꾸기
		ex_hm = experience_biz.getMyAppliance(myID);
		if (!ex_hm.isEmpty()) {
			for (int i = 0; i < ex_hm.size(); i++) {
				String row[] = experience_biz.getRow(i);
				data[table_index] = row;
				table_index++;
			}
			for(int i=0; i< table_index ; i++){
				AppName = data[i][0];
				Change_Click(AppName);
				Enabled(); //수정된곳
			}
			Total_Result();
		}
		//////////////////수정///////////////

		// TODO Auto-generated method stub
		////////////////////수정//////////////////
		ExPage_Reset = new JButton("초기화");
		ExPage_Reset.setBounds(300, 372, 97, 23);
		Experience_Mid_Left.add(ExPage_Reset);

		ExPage_Save = new JButton("저장");
		ExPage_Save.setBounds(191, 372, 97, 23);
		Experience_Mid_Left.add(ExPage_Save);
		////////////////////수정//////////////////

		ExPage_detail = new JButton("전기요금상세조회");
		ExPage_detail.setBounds(382, 323, 142, 23);
		Experience_Mid_Right.add(ExPage_detail);
		ExPage_detail.addActionListener(this);

		addEx_Listener();


		//////////////////////////////////////////////////////////////////////////////////////
		//체험 패널

		//////////////////////////////////////////////////////////////////////////////////				

		Component[] c = StatCalendar.getDayChooser().getDayPanel().getComponents();
		days=dd.DayCheck(c, null);
		dd.DayFontDeco(days, new Font("고딕",Font.PLAIN,15));
		dd.DayBackgroundDeco(days, Color.WHITE);


		days = dd.DayCheck(c, cb.getG());
		dd.DayFontDeco(days, new Font("고딕",Font.BOLD,20));
		
		
//		cursor=new Date(t.get(Calendar.YEAR)+"/"+(t.get(Calendar.MONTH)+1)+"/01");
		
		

		StatCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				//calendar에 클릭된 날짜값이 변했을때
				if(cursor.getMonth()!=StatCalendar.getDate().getMonth()
						||cursor.getYear()!=StatCalendar.getDate().getYear()){	//연도달이 바뀌면
					
					cursor=StatCalendar.getDate();
					StatGraph.remove(StatChart);
//					System.out.println("바꼈다");

					chart=cb.graphBuild(myID,ElectricVo.date2String(cursor));
//					System.out.println(ElectricVo.date2String(cursor));
					StatChart=new ChartPanel(chart);
					StatChart.setBounds(0, 0, 553, 323);
					StatChart.setPreferredSize(new Dimension(453, 346));
					StatGraph.add(StatChart);
					StatGraph.repaint();

					StatRLabel1.setText("");
					StatRLabel2.setText("");
					if(cb.getG().size()>0){
						StatRLabel1.setText(String.format("%d년%d월말예상사용량: %.1f kWh ", cb.getG().get(0).getD().getYear()+1900,cb.getG().get(0).getD().getMonth()+1,cb.getGuess()));
						double d=cb.getG().get(cb.getG().size()-1).getUsed()-cb.getG().get(0).getUsed();
						guessLabel.setText(String.valueOf(cb.getGuess()));
						bc.setMonth(String.valueOf(cursor.getYear()+1900),String.valueOf(cursor.getMonth()+1));
						bc.set0();
						if(d<0){d+=10000;}
						StatRLabel2.setText(String.format("%d년%d월 %d ~ %d일 사용량: %.1f kWh ", cb.getG().get(0).getD().getYear()+1900,cb.getG().get(0).getD().getMonth()+1,cb.getG().get(0).getD().getDate(),cb.getG().get(cb.getG().size()-1).getD().getDate(),d));
						//						cb.setGuess(0);

					}else{
						bc.setMonth("", "");
						guessLabel.setText("");
					}

					Component[] c = StatCalendar.getDayChooser().getDayPanel().getComponents();
					days=dd.DayCheck(c, null);
					dd.DayFontDeco(days, new Font("고딕",Font.PLAIN,15));
					dd.DayBackgroundDeco(days, Color.WHITE);

					days = dd.DayCheck(c, cb.getG());
					dd.DayFontDeco(days, new Font("고딕",Font.BOLD,20));


				}else{
					//System.out.println("같다!!");
				}


				

				cb.sqlChartBuild(myID,StatRTable,"select * from elec where id like '"+myID+"' and day='"
						+ElectricVo.date2String(cursor)+"' order by day asc",cb.getG());


				StatInDateField.setText(ElectricVo.date2String(StatCalendar.getDate()));
				StatInUseField.setText("");



			}
		});
		
		
		
		

		////여기부터 그래프
		/*		DefaultCategoryDataset dataSet = new DefaultCategoryDataset(); 
		for(int i=0;i<cb.g.size();i++){
			dataSet.addValue(cb.g.get(i).getUsed(), "used", Integer.valueOf(cb.g.get(i).getD().getDate()));

		}*/




		// ********** Chart 설정  **********










		StatInputButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {


				String dateTxt=StatInDateField.getText();
				String[] s=dateTxt.split("/");

				//				cb.sqlChartBuild(StatRTable,"insert into elec values('"+ID+"','"
				//						+StatInDateField.getText()+"',"+StatInUseField.getText()+")",cb.g);
				eb.insertVo(new ElectricVo(myID,new Date(Integer.valueOf(s[0])+100,Integer.valueOf(s[1])-1,Integer.valueOf(s[2])),Double.valueOf(StatInUseField.getText())));


				cursor=StatCalendar.getDate();


				cb.sqlChartBuild(myID,StatLTable,"select * from elec where id like '"+myID+"' order by day asc",cb.getG());
				cb.sqlChartBuild(myID,StatRTable,"select * from elec where id like '"+myID+"' and day like '"+ElectricVo.date2String(cursor)+"' order by day asc",cb.getG());


				StatGraph.remove(StatChart);
				chart=cb.graphBuild(myID,ElectricVo.date2String(cursor));
				StatChart=new ChartPanel(chart);
				StatChart.setBounds(0, 0, 553, 323);
				StatChart.setPreferredSize(new Dimension(453, 346));
				StatGraph.add(StatChart);
				StatGraph.repaint();

				Component[] c = StatCalendar.getDayChooser().getDayPanel().getComponents();
				days=dd.DayCheck(c, null);
				dd.DayFontDeco(days, new Font("고딕",Font.PLAIN,15));


				days = dd.DayCheck(c, cb.getG());
				dd.DayFontDeco(days, new Font("고딕",Font.BOLD,20));

				if(cb.getG().size()>0){
					StatRLabel1.setText(String.format("%d년%d월말예상사용량: %.1f kWh ", cb.getG().get(0).getD().getYear()+1900,cb.getG().get(0).getD().getMonth()+1,cb.getGuess()));
					double d=cb.getG().get(cb.getG().size()-1).getUsed()-cb.getG().get(0).getUsed();
					guessLabel.setText(String.valueOf(cb.getGuess()));
					bc.set0();
					if(d<0){d+=10000;}
					StatRLabel2.setText(String.format("%d년%d월 %d ~ %d일 사용량: %.1f kWh ", cb.getG().get(0).getD().getYear()+1900,cb.getG().get(0).getD().getMonth()+1,cb.getG().get(0).getD().getDate(),cb.getG().get(cb.getG().size()-1).getD().getDate(),d));
					//					cb.setGuess(0);

				}

			}


		});


		StatDelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String dateTxt=StatInDateField.getText();


				eb.deleteVo(dateTxt,myID);


				cursor=StatCalendar.getDate();


				cb.sqlChartBuild(myID,StatLTable,"select * from elec where id like '"+myID+"' order by day asc",cb.getG());
				cb.sqlChartBuild(myID,StatRTable,"select * from elec where id like '"+myID+"' and day like '"+ElectricVo.date2String(cursor)+"' order by day asc",cb.getG());


				StatGraph.remove(StatChart);
				chart=cb.graphBuild(myID,ElectricVo.date2String(cursor));
				StatChart=new ChartPanel(chart);
				StatChart.setBounds(0, 0, 553, 323);
				StatChart.setPreferredSize(new Dimension(453, 346));
				StatGraph.add(StatChart);
				StatGraph.repaint();

				Component[] c = StatCalendar.getDayChooser().getDayPanel().getComponents();
				days=dd.DayCheck(c, null);
				dd.DayFontDeco(days, new Font("고딕",Font.PLAIN,15));


				days = dd.DayCheck(c, cb.getG());
				dd.DayFontDeco(days, new Font("고딕",Font.BOLD,20));

				if(cb.getG().size()>0){
					StatRLabel1.setText(String.format("%d년%d월말예상사용량: %.1f kWh ", cb.getG().get(0).getD().getYear()+1900,cb.getG().get(0).getD().getMonth()+1,cb.getGuess()));
					double d=cb.getG().get(cb.getG().size()-1).getUsed()-cb.getG().get(0).getUsed();
					guessLabel.setText(String.valueOf(cb.getGuess()));
					bc.set0();
					if(d<0){d+=10000;}
					StatRLabel2.setText(String.format("%d년%d월 %d ~ %d일 사용량: %.1f kWh ", cb.getG().get(0).getD().getYear()+1900,cb.getG().get(0).getD().getMonth()+1,cb.getG().get(0).getD().getDate(),cb.getG().get(cb.getG().size()-1).getD().getDate(),d));
					//					cb.setGuess(0);

				}


			}
		});
		StatCalendar.setBounds(0, 0, 425, 392);
		StatCalPanel.add(StatCalendar);

		StatTablePanel = new JPanel();
		StatSubTab.addTab("테이블", null, StatTablePanel, null);
		StatTablePanel.setLayout(null);

		StatTableScrollPane = new ScrollPane();
		StatTableScrollPane.setBounds(0, 0, 425, 392);
		StatTablePanel.add(StatTableScrollPane);

		StatLTable = new JTable(model);
		StatLTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cb.sqlChartBuild(myID,StatRTable,"select * from elec where id like '"+myID+"' and  day like '"
						+StatLTable.getValueAt(StatLTable.getSelectedRow(), 0).toString().replace('-', '/').substring(2)
						+"' order by day asc",cb.getG());
				StatInDateField.setText(StatLTable.getValueAt(StatLTable.getSelectedRow(), 0).toString().replace('-', '/').substring(2));
				StatInUseField.setText("");



				cursor=new Date(StatLTable.getValueAt(StatLTable.getSelectedRow(), 0).toString().replace('-', '/'));
				StatGraph.remove(StatChart);
				chart=cb.graphBuild(myID,StatLTable.getValueAt(StatLTable.getSelectedRow(), 0).toString().substring(2));
				StatChart=new ChartPanel(chart);
				StatChart.setBounds(0, 0, 553, 323);

				StatGraph.add(StatChart);
				StatGraph.repaint();
				//System.out.println(cursor);
				//System.out.println(cursor.getYear()+" "+(cursor.getMonth()+1));
				StatCalendar.getYearChooser().setYear(cursor.getYear()+1900);
				StatCalendar.getMonthChooser().setMonth(cursor.getMonth());
				
			

				

				cb.sqlChartBuild(myID,null,"select * from elec where id like '"+myID+"' and day like '"+ElectricVo.date2String(cursor).substring(0, 6)+"%%' order by day asc",cb.getG());
				days=dd.DayCheck(c, null);
				dd.DayFontDeco(days, new Font("고딕",Font.PLAIN,15));
				dd.DayBackgroundDeco(days, Color.WHITE);


				days = dd.DayCheck(c, cb.getG());
				dd.DayFontDeco(days, new Font("고딕",Font.BOLD,20));
				
				//System.out.println(ElectricVo.date2String(cursor).substring(0, 6));
				if(cb.getG().size()>0){
					StatRLabel1.setText(String.format("%d년%d월말예상사용량: %.1f kWh ", cb.getG().get(0).getD().getYear()+1900,cb.getG().get(0).getD().getMonth()+1,cb.getGuess()));
					double d=cb.getG().get(cb.getG().size()-1).getUsed()-cb.getG().get(0).getUsed();
					guessLabel.setText(String.valueOf(cb.getGuess()));
					bc.setMonth(String.valueOf(cursor.getYear()+1900),String.valueOf(cursor.getMonth()+1));
					bc.set0();
					if(d<0){d+=10000;}
					StatRLabel2.setText(String.format("%d년%d월 %d ~ %d일 사용량: %.1f kWh ", cb.getG().get(0).getD().getYear()+1900,cb.getG().get(0).getD().getMonth()+1,cb.getG().get(0).getD().getDate(),cb.getG().get(cb.getG().size()-1).getD().getDate(),d));
					//						cb.setGuess(0);

				}




			}
		});


		StatLTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		StatLTable.setBounds(0, 0, 416, 403);
		StatTableScrollPane.add(StatLTable);
		StatLTable.setVisible(true);
		cb.sqlChartBuild(myID,StatLTable,"select * from elec where id like '"+myID+"' order by day asc",cb.getG());
		JPanel bill_panel = new JPanel();
		bill_panel.setLocation(0, 0);
		bill_panel.setSize(434, 401);
		bill_panel.setLayout(null);
		StatSubTab.add("계산서", bill_panel);
		//		jf.getContentPane().add(bill_panel);
				
				JButton button = new JButton("상세요금");
				button.setBounds(300, 10, 97, 23);
				bill_panel.add(button);
				
				JLabel year_value = new JLabel("Y");
				year_value.setFont(new Font("맑은 고딕", Font.BOLD, 20));
				year_value.setBounds(30, 14, 57, 15);
				bill_panel.add(year_value);
				year_value.setText(String.valueOf(cursor.getYear()+1900));
			
				
				JLabel year_label = new JLabel("\uB144");
				year_label.setBounds(87, 14, 19, 19);
				bill_panel.add(year_label);
				
				JLabel month_value = new JLabel("M");
				month_value.setFont(new Font("맑은 고딕", Font.BOLD, 20));
				month_value.setBounds(137, 14, 34, 15);
				bill_panel.add(month_value);
				month_value.setText(String.valueOf(cursor.getMonth()+1));
			
				
				JLabel label = new JLabel("\uC6D4");
				label.setBounds(185, 14, 18, 19);
				bill_panel.add(label);
				
				guessLabel = new JLabel("");
				guessLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				guessLabel.setBounds(229, 57, 116, 21);
				guessLabel.setFont(new Font("고딕",Font.BOLD,15));
				bill_panel.add(guessLabel);
				
				
				
				
				guessLabel.setText(String.valueOf(cb.getGuess()));

				
				
				
				kWh_label = new JLabel("kwh");
				kWh_label.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
				kWh_label.setBounds(357, 49, 57, 41);
				bill_panel.add(kWh_label);
				
				price_value = new JLabel("price");
				price_value.setHorizontalAlignment(SwingConstants.RIGHT);
				price_value.setFont(new Font("맑은 고딕", Font.BOLD, 26));
				price_value.setForeground(Color.MAGENTA);
				price_value.setBounds(89, 335, 200, 36);
				bill_panel.add(price_value);
				
				JLabel won_label = new JLabel("won");
				won_label.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
				won_label.setBounds(357, 344, 57, 23);
				bill_panel.add(won_label);	 
				
				lblBasicprice = new JLabel("기본요금(원미만 절사)");
				lblBasicprice.setBounds(40, 100, 135, 15);
				bill_panel.add(lblBasicprice);
				
				lblBase = new JLabel("base");
				lblBase.setBounds(347, 100, 50, 15);
				lblBase.setHorizontalAlignment(SwingConstants.RIGHT);
				bill_panel.add(lblBase);
				
				lblPowerprice = new JLabel("전력량요금(원미만 절사)");
				lblPowerprice.setBounds(40, 130, 140, 15);
				bill_panel.add(lblPowerprice);
				
				lblPower = new JLabel("power");
				bill_panel.add(lblPower);
				lblPower.setHorizontalAlignment(SwingConstants.RIGHT);
				lblPower.setBounds(347, 130, 50, 15);
				bill_panel.add(lblPower);
				
				sumBPlabel = new JLabel("합계(기본요금 + 전력량요금)");
				sumBPlabel.setBounds(40, 160, 200, 15);
				bill_panel.add(sumBPlabel);
				
				sumBPvalue = new JLabel("sumbp");
				sumBPvalue.setForeground(Color.BLUE);
				sumBPvalue.setHorizontalAlignment(SwingConstants.RIGHT);
				sumBPvalue.setBounds(347, 160, 50, 15);
				bill_panel.add(sumBPvalue);
				
				VATlabel = new JLabel("부가가치세(원미만 4사 5입)");
				VATlabel.setBounds(40, 190, 178, 15);
				bill_panel.add(VATlabel);
				
				lVat_value = new JLabel("vat");
				lVat_value.setHorizontalAlignment(SwingConstants.RIGHT);
				lVat_value.setBounds(347, 190, 50, 15);
				bill_panel.add(lVat_value);
				
				powerTaxlabel = new JLabel("전력산업기반기금(10원미만 절사)");
				powerTaxlabel.setBounds(40, 220, 194, 15);
				bill_panel.add(powerTaxlabel);
				
				Tax_value = new JLabel("tax");
				Tax_value.setHorizontalAlignment(SwingConstants.RIGHT);
				Tax_value.setBounds(347, 220, 50, 15);
				bill_panel.add(Tax_value);
				
				totalPricelabel = new JLabel("합계 + 부가가치세 + 전력산업기반금 = 청구금액");
				totalPricelabel.setBounds(39, 256, 337, 15);
				bill_panel.add(totalPricelabel);
				
				lblTotal = new JLabel("total");
				lblTotal.setFont(new Font("굴림", Font.BOLD, 12));
				lblTotal.setBounds(40, 280, 337, 15);
				lblTotal.setBackground(Color.green);
				bill_panel.add(lblTotal);
				bc=new BillCal(price_value,lblBase,lblPower,sumBPvalue,lVat_value,Tax_value,lblTotal,month_value,year_value);
				
				//사용량 데이터 받아오기
				button.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					double power = Double.parseDouble(guessLabel.getText());
					int power2 = (int)power;
					//System.out.println(m);
					
					guessLabel.setText(String.valueOf(power));
					Progressive_Biz ProBiz2= new Progressive_Biz();
	
					Power_Charge = ProBiz2.CalculateProgressive(power2, r1);
					Power_Charge2 = Power_Charge + "원";
					price_value.setText(Power_Charge2);
								
					List<ProgressiveVO> list = ProBiz3.getProgressiveList();
				    int basic_rate = ProBiz3.getBasic_Rate(power2,list);
				    //System.out.println("기본요금"+power);
				    lblBase.setText(String.valueOf(basic_rate + "원"));
					
					
					int use_price =ProBiz3.getDetail_Progressive1(power2, list);
					lblPower.setText(String.valueOf(use_price + "원"));
					
					
					int sumBP = (basic_rate + use_price);
					sumBPvalue.setText(String.valueOf(sumBP + "원"));
					
					
					double vat =  sumBP * 0.1;
					lVat_value.setText(String.valueOf(Math.round(vat)) +"원");
					
					
					double tax =  sumBP * 0.037;
					Tax_value.setText(String.valueOf(Math.round(tax)) +"원");
					
				
					lblTotal.setText(sumBP + "+" + Math.round(vat)+ "+" + Math.round(tax)+ "="  + Power_Charge2);
				}
				} );


		Pro_Bottom = new JPanel();
		Pro_Bottom.setBounds(0, 457, 883, 20);
		jf.getContentPane().add(Pro_Bottom);
		Pro_Bottom.setLayout(null);

		Interduce2 = new JLabel();
		Interduce2.setBounds(0, 0, 405, 18);
		Interduce2.setText("제작자 : KITRI 빅데이터 개발자 1조 - 윤한종, 박성민, 김도환, 백주희");
		Pro_Bottom.add(Interduce2);



		/*		btnEnterance.addMouseListener(new MouseAdapter() {	//로그인버튼 이벤트
			@Override
			public void mouseClicked(MouseEvent arg0) {
		 */
		tabbedPane.addTab("통계", null, Stat_Page, null);
		tabbedPane.addTab("누진세 계산", Progressive_Page);
		tabbedPane.addTab("전기요금체험관", Experience_Page);

		// TODO Auto-generated method stub
		CheckList_Page = new JPanel();
		tabbedPane.addTab("데일리체크리스트", null, CheckList_Page, null);
		CheckList_Page.setLayout(null);

		CList_Left = new JPanel();
		CList_Left.setBounds(0, 0, 499, 427);
		CList_Left.setLayout(null);
		CheckList_Page.add(CList_Left);

		CL_ch1 = new JCheckBox("\uC4F0\uC9C0 \uC54A\uB294 \uAC00\uC804\uC81C\uD488 \uB300\uAE30\uC804\uB825 \uCC28\uB2E8\uD558\uAE30");
		CL_ch1.setBounds(56, 50, 394, 23);
		CList_Left.add(CL_ch1);

		CL_ch2 = new JCheckBox("\uC815\uC218\uAE30 \uB0C9,\uC628\uC218, \uC5BC\uC74C \uAE30\uB2A5 \uAEBC\uB193\uAE30");
		CL_ch2.setBounds(56, 123, 394, 23);
		CList_Left.add(CL_ch2);

		CL_ch3 = new JCheckBox("\uB0C9\uC7A5\uACE0 \uBB38 \uC790\uC8FC \uC5F4\uC9C0 \uC54A\uAE30");
		CL_ch3.setBounds(56, 196, 394, 23);
		CList_Left.add(CL_ch3);

		CL_ch4 = new JCheckBox("\uACC4\uC808\uBCC4 \uBCF5\uC7A5\uC73C\uB85C \uCCB4\uAC10\uC628\uB3C4 \uB0AE\uCD94\uAE30");
		CL_ch4.setBounds(56, 269, 394, 23);
		CList_Left.add(CL_ch4);

		CL_ch5 = new JCheckBox("\uC81C\uD488 \uC2E4\uC678\uAE30\uC5D0 \uCC28\uC591\uB9C9 \uC124\uCE58\uD558\uAE30");
		CL_ch5.setBounds(56, 342, 394, 23);
		CList_Left.add(CL_ch5);

		CL_Massage = new JLabel("에너지 절약 체크리스트");
		CL_Massage.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		CL_Massage.setHorizontalAlignment(SwingConstants.CENTER);
		CL_Massage.setBounds(111, 10, 240, 23);
		CList_Left.add(CL_Massage);

		CList_Right = new JPanel();
		CList_Right.setBackground(SystemColor.text);
		CList_Right.setBounds(500, 0, 483, 427);
		CList_Right.setLayout(null);
		CheckList_Page.add(CList_Right);

		CL_image = new JLabel("");
		CL_image.setBounds(78, 10, 337, 337);
		CList_Right.add(CL_image);

		CL_Text = new JLabel("");
		CL_Text.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		CL_Text.setHorizontalAlignment(SwingConstants.CENTER);
		CL_Text.setBounds(58, 368, 376, 38);
		CList_Right.add(CL_Text);

		addCL_Listener();
		// TODO Auto-generated method stub

		tabbedPane.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e){
				JTabbedPane tp = (JTabbedPane)e.getSource();
				if(tp.getTabPlacement()==1){
					String strGuess = String.valueOf((int)cb.getGuess());
					Pro_Power_Input.setText(strGuess);
				}
			}

		});
		//			} //로그인버튼 이벤트
		//		});







		panel = new Panel();
		panel.setBounds(425, 0, 458, 421);

		panel.setLayout(null);
		panel_2 = new JPanel();
		panel_2.setBounds(0, 237, 458, 184);
		panel.add(panel_2);
		panel_2.setLayout(null);


		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(417, 0, 8, 421);

		ev.result(CL_image, CL_Text, 0);
		
		jf.setVisible(true);
		
//		StatCalendar.setDate(new Date(t.get(Calendar.YEAR)+"/"+(t.get(Calendar.MONTH)+1)+"/"+t.get(Calendar.DAY_OF_MONTH)));
		
	}
	public void addPro_Listener() {
		Pro_Button1.addActionListener(this);
		Pro_Button2.addActionListener(this);

		//////////03-31추가///////
		Pro_Power_Input.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					Pro_Input_Button.doClick();
				}
			}
		});

		Pro_Input_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int Power = Integer.parseInt(Pro_Power_Input.getText());
				Progressive_Biz ProBiz= new Progressive_Biz();
				Power_Charge = ProBiz.CalculateProgressive(Power, r1);
				List<ProgressiveVO> list = ProBiz.getProgressiveList();

				Power_Charge2 = Power_Charge + "원";

				TPage_Power_ChargeResult.setBounds(152, 234, 209, 30);
				TPage_Power_ChargeResult.setFont(new Font("맑은 고딕",Font.BOLD,20));
				TPage_Power_ChargeResult.setText(Power_Charge2);
				Pro_Mid_Right.add(TPage_Power_ChargeResult);



				String explain = ProBiz.getExplain(Power, r1, Power_Charge);
				Memo_Input.setText(explain);

				Pro_Mid_Right.add(Memo_Input);

				jf.repaint();
			}
		});
		//////////03-31추가///////

	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("저압(주택)") || ae.getActionCommand().equals("고압(주택)")) {
			r1 = ae.getActionCommand();
			//System.out.println(r1);
		}
		else {		
			sf = new SubFrame(sum2,r1);
		}
	}

	public void addEx_Listener() {
		ch1.addItemListener(this);  
		ch1.addMouseListener(this);
		ch2.addItemListener(this); 
		ch2.addMouseListener(this);			
		ch3.addItemListener(this); 
		ch3.addMouseListener(this);
		ch4.addItemListener(this); 
		ch4.addMouseListener(this);
		ch5.addItemListener(this); 
		ch5.addMouseListener(this);
		ch6.addItemListener(this); 
		ch6.addMouseListener(this);
		ch7.addItemListener(this); 
		ch7.addMouseListener(this);
		ch8.addItemListener(this); 
		ch8.addMouseListener(this);
		ch9.addItemListener(this); 
		ch9.addMouseListener(this);
		ch10.addItemListener(this); 
		ch10.addMouseListener(this);
		ch11.addItemListener(this); 
		ch11.addMouseListener(this);
		ch12.addItemListener(this); 
		ch12.addMouseListener(this);
		ch13.addItemListener(this); 
		ch13.addMouseListener(this);
		ch14.addItemListener(this); 
		ch14.addMouseListener(this);			
		ch15.addItemListener(this); 
		ch15.addMouseListener(this);
		ch16.addItemListener(this); 
		ch16.addMouseListener(this);
		ch17.addItemListener(this); 
		ch17.addMouseListener(this);
		ch18.addItemListener(this); 
		ch18.addMouseListener(this);
		ch19.addItemListener(this); 
		ch19.addMouseListener(this);
		ch20.addItemListener(this); 
		ch20.addMouseListener(this);
		ch21.addItemListener(this); 
		ch21.addMouseListener(this);

		Ex_table.addPropertyChangeListener(this);

		ExPage_Reset.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				ex_hm.clear();
				//System.out.println("리셋버튼클릭");
				for(int i=0; i<table_index;i++){
					for(int j=0; j<7; j++){
						data[i][j] = "";
					}
				}
				ch1.setSelected(false);ch2.setSelected(false);ch3.setSelected(false);ch4.setSelected(false);ch5.setSelected(false);ch6.setSelected(false);
				ch7.setSelected(false);ch8.setSelected(false);ch9.setSelected(false);ch10.setSelected(false);ch11.setSelected(false);ch12.setSelected(false);
				ch13.setSelected(false);ch14.setSelected(false);ch15.setSelected(false);ch16.setSelected(false);ch17.setSelected(false);ch18.setSelected(false);
				ch19.setSelected(false);ch20.setSelected(false);ch21.setSelected(false);
				experience_biz.MapClear();
				table_index=0;
				Enabled(); //수정된곳
				Total_Result(); //수정된곳
				jf.repaint();
			}
		});

		////////////////////수정시작//////////////////
		ExPage_Save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				experience_biz.setMyAppliance(ex_hm, table_index, myID);
				Enabled(); //수정된곳
				jf.repaint();
			}
		});
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		ex_hm = experience_biz.get_listAppliance(e);
		Enabled();  //수정된 곳
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		String row[] = experience_biz.getRow(table_index);
		data[table_index] = row;
		table_index++;
		Total_Result();
	}

	public void Total_Result(){
		///월간전력사용량합계///
		double sum = 0;
		ExPage_SumkWh2.setBounds(240, 356, 57, 15);
		for(int i =0; i<table_index; i++){
			sum += Double.parseDouble(data[i][6]);
		}
		sum2 = (int)sum;
		ExPage_SumkWh2.setText(String.valueOf(sum2)+"kWh");
		Experience_Mid_Right.add(ExPage_SumkWh2);
		///전기요금///
		Progressive_Biz ProBiz = new Progressive_Biz();
		int Power = ProBiz.CalculateProgressive(sum2, r1);
		ExPage_PowerCharge2.setBounds(400, 356, 57, 15);
		ExPage_PowerCharge2.setText(String.valueOf(Power)+"원");
		Experience_Mid_Right.add(ExPage_PowerCharge2);

		jf.repaint();
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		/*System.out.println(Ex_table.getEditingRow() + "," + Ex_table.getEditingColumn()); // 컬럼확인
		 */		if ((Ex_table.getEditingRow() != -1) && (Ex_table.getEditingColumn() != -1)) {
			 //System.out.println("컬럼이 바뀌었습니다.");
			 String Changed_Text = data[Ex_table.getEditingRow()][Ex_table.getEditingColumn()];
			 ex_hm = experience_biz.Changed_Content(Changed_Text, Ex_table.getEditingColumn(), ex_hm, Ex_table.getEditingRow());
			 String Change_row[] = experience_biz.getRow(Ex_table.getEditingRow());
			 data[Ex_table.getEditingRow()] = Change_row;
			 Total_Result();
			 jf.repaint();
		 }
	}

	////////////////////수정//////////////////
	public void Change_Click(String Name){
		switch(Name){
		case "TV":  ch1.setSelected(true); break;
		case "에어컨":ch2.setSelected(true);break;
		case "공기청정기":ch3.setSelected(true);break;
		case "오디오":ch4.setSelected(true);break;
		case "선풍기":ch5.setSelected(true);break;
		case "형광등":  ch6.setSelected(true); break;
		case "가습기":ch7.setSelected(true);break;
		case "냉장고":ch8.setSelected(true);break;
		case "전기밥솥(취사)":ch9.setSelected(true);break;
		case "전자레인지":ch10.setSelected(true);break;
		case "믹서기":  ch11.setSelected(true); break;
		case "컴퓨터":ch12.setSelected(true);break;
		case "모니터":ch13.setSelected(true);break;
		case "프린터":ch14.setSelected(true);break;
		case "휴대폰충전기":ch15.setSelected(true);break;
		case "DVD Player":  ch16.setSelected(true); break;
		case "김치냉장고":ch17.setSelected(true);break;
		case "세탁기":ch18.setSelected(true);break;
		case "진공청소기":ch19.setSelected(true);break;
		case "헤어드라이기":ch20.setSelected(true);break;
		case "비데":ch21.setSelected(true);break;
		}
	}
	////////////////////수정 끝//////////////////

	////////////////////수정시작//////////////////
	public void Enabled(){
		if(ch1.isSelected()) {ch1.setEnabled(false);} else{ch1.setEnabled(true);}if(ch2.isSelected()) {ch2.setEnabled(false);}else{ch2.setEnabled(true);}if(ch3.isSelected()) {ch3.setEnabled(false);}else{ch3.setEnabled(true);}
		if(ch4.isSelected()) {ch4.setEnabled(false);}else{ch4.setEnabled(true);}if(ch5.isSelected()) {ch5.setEnabled(false);}else{ch5.setEnabled(true);}if(ch6.isSelected()) {ch6.setEnabled(false);}else{ch6.setEnabled(true);}
		if(ch7.isSelected()) {ch7.setEnabled(false);}else{ch7.setEnabled(true);}if(ch8.isSelected()) {ch8.setEnabled(false);}else{ch8.setEnabled(true);}if(ch9.isSelected()) {ch9.setEnabled(false);}else{ch9.setEnabled(true);}
		if(ch10.isSelected()) {ch10.setEnabled(false);}else{ch10.setEnabled(true);}if(ch11.isSelected()) {ch11.setEnabled(false);}else{ch11.setEnabled(true);}if(ch12.isSelected()) {ch12.setEnabled(false);}else{ch12.setEnabled(true);}
		if(ch13.isSelected()) {ch13.setEnabled(false);}else{ch13.setEnabled(true);}if(ch14.isSelected()) {ch14.setEnabled(false);}else{ch14.setEnabled(true);}if(ch15.isSelected()) {ch15.setEnabled(false);}else{ch15.setEnabled(true);}
		if(ch16.isSelected()) {ch16.setEnabled(false);}else{ch16.setEnabled(true);}if(ch17.isSelected()) {ch17.setEnabled(false);}else{ch17.setEnabled(true);}if(ch18.isSelected()) {ch18.setEnabled(false);}else{ch18.setEnabled(true);}
		if(ch19.isSelected()) {ch19.setEnabled(false);}else{ch19.setEnabled(true);}if(ch20.isSelected()) {ch20.setEnabled(false);}else{ch20.setEnabled(true);}if(ch21.isSelected()) {ch21.setEnabled(false);}else{ch21.setEnabled(true);}
		////////////////////수정끝//////////////////
	}

	public void addCL_Listener(){
		CL_ch1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ev.Ch(CL_image, CL_Text, e);
			}
		});
		CL_ch2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ev.Ch(CL_image, CL_Text, e);
			}
		});
		CL_ch3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ev.Ch(CL_image, CL_Text, e);
			}
		});
		CL_ch4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ev.Ch(CL_image, CL_Text, e);
			}
		});
		CL_ch5.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ev.Ch(CL_image, CL_Text, e);
			}
		});
	}
}





