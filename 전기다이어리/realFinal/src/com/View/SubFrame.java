package com.View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.SwingConstants;

import com.biz.Progressive_Biz;
import com.entity.ProgressiveVO;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.awt.Font;

//전기요금 상세 페이지(새창)
public class SubFrame extends JFrame {
	private int Power;
	private String button;
	 private Progressive_Biz probiz = new Progressive_Biz();
	
	public SubFrame(int Power, String button){
        super("전기요금내역 상세");
        setSize(537, 495);
        getContentPane().setLayout(new GridLayout(1, 1, 0, 0));
        this.Power = Power;
        this.button = button;
        
        JPanel panel_2 = new JPanel();
        getContentPane().add(panel_2);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[] {30, 74, 200, 50, 30};
        gbl_panel_2.rowHeights = new int[] {30, 30, 30, 30, 30, 0, 0, 0, 30, 30, 30, 10, 0, 0};
        gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
        gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        panel_2.setLayout(gbl_panel_2);
        panel_2.setSize(530, 400);
        
        //전기요금내역 상세
        JLabel label = new JLabel("\u2592 \uC804\uAE30\uC694\uAE08\uB0B4\uC5ED \uC0C1\uC138\uBCF4\uAE30");
        label.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 18));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setForeground(Color.BLUE);
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.gridheight = 2;
        gbc_label.gridwidth = 3;
        gbc_label.anchor = GridBagConstraints.WEST;
        gbc_label.insets = new Insets(0, 0, 5, 5);
        gbc_label.gridx = 1;
        gbc_label.gridy = 0;
        panel_2.add(label, gbc_label);
        
        //총전력사용량
        JLabel label_13 = new JLabel("\uCD1D \uC804\uB825 \uC0AC\uC6A9\uB7C9 : ");
        label_13.setBackground(new Color(204, 255, 255));
        GridBagConstraints gbc_label_13 = new GridBagConstraints();
        gbc_label_13.gridheight = 2;
        gbc_label_13.anchor = GridBagConstraints.WEST;
        gbc_label_13.insets = new Insets(0, 0, 5, 5);
        gbc_label_13.gridx = 1;
        gbc_label_13.gridy = 2;
        panel_2.add(label_13, gbc_label_13);
        
        JLabel label_14 = new JLabel("00000");
		// TODO Auto-generated method stub
        //System.out.println(this.Power);
        label_14.setText(String.valueOf(this.Power));
        label_14.setFont(new Font("굴림", Font.BOLD, 12));
        label_14.setForeground(Color.RED);
        label_14.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_label_14 = new GridBagConstraints();
        gbc_label_14.anchor = GridBagConstraints.EAST;
        gbc_label_14.gridheight = 2;
        gbc_label_14.insets = new Insets(0, 0, 5, 5);
        gbc_label_14.gridx = 2;
        gbc_label_14.gridy = 2;
        panel_2.add(label_14, gbc_label_14);
        
        JLabel label_15 = new JLabel("kwh");
        label_15.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_label_15 = new GridBagConstraints();
        gbc_label_15.gridheight = 2;
        gbc_label_15.insets = new Insets(0, 0, 5, 5);
        gbc_label_15.gridx = 3;
        gbc_label_15.gridy = 2;
        panel_2.add(label_15, gbc_label_15);
        
        
        //기본요금(원미만절사)
        JLabel basic_price_label = new JLabel("기본요금(원미만절사) :");
        basic_price_label.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 5;
        panel_2.add(basic_price_label, gbc_lblNewLabel);
        
        JLabel basic_price_value = new JLabel("기본요금");
		// TODO Auto-generated method stub
        probiz = new Progressive_Biz();
        int Charge = probiz.CalculateProgressive(Power, button);
        List<ProgressiveVO> list = probiz.getProgressiveList();
        int basic_rate = probiz.getBasic_Rate(Power,list);
        //System.out.println(Power+"기본요금");
        basic_price_value.setText(String.valueOf(basic_rate));
        
        basic_price_value.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_label_11 = new GridBagConstraints();
        gbc_label_11.anchor = GridBagConstraints.EAST;
        gbc_label_11.insets = new Insets(0, 0, 5, 5);
        gbc_label_11.gridx = 2;
        gbc_label_11.gridy = 5;
        panel_2.add(basic_price_value, gbc_label_11);
        
        JLabel basic_price_won = new JLabel("원");
        basic_price_won.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_label_12 = new GridBagConstraints();
        gbc_label_12.insets = new Insets(0, 0, 5, 5);
        gbc_label_12.gridx = 3;
        gbc_label_12.gridy = 5;
        panel_2.add(basic_price_won, gbc_label_12);
        
        //사용량요금(원미만 절사)
        JLabel use_price_label = new JLabel("사용량요금(원미만 절사) :");
        use_price_label.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_label_4 = new GridBagConstraints();
        gbc_label_4.anchor = GridBagConstraints.WEST;
        gbc_label_4.insets = new Insets(0, 0, 5, 5);
        gbc_label_4.gridx = 1;
        gbc_label_4.gridy = 6;
        panel_2.add(use_price_label, gbc_label_4);
        
        JLabel use_price_value = new JLabel("사용량요금");
		// TODO Auto-generated method stub
        int use_price =probiz.getDetail_Progressive1(Power, list);
        use_price_value.setText(String.valueOf(use_price));
        
        use_price_value.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblNewLabel2 = new GridBagConstraints();
        gbc_lblNewLabel2.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel2.gridx = 2;
        gbc_lblNewLabel2.gridy = 6;
        panel_2.add(use_price_value, gbc_lblNewLabel2);
        
        JLabel use_price_won = new JLabel("원");
        use_price_won.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_label_6 = new GridBagConstraints();
        gbc_label_6.insets = new Insets(0, 0, 5, 5);
        gbc_label_6.gridx = 3;
        gbc_label_6.gridy = 6;
        panel_2.add(use_price_won, gbc_label_6);
        
        
        //-세부계산내역
        JLabel detail_label = new JLabel("-세부계산내역");
        detail_label.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_label_7 = new GridBagConstraints();
        gbc_label_7.anchor = GridBagConstraints.WEST;
        gbc_label_7.insets = new Insets(0, 0, 5, 5);
        gbc_label_7.gridx = 1;
        gbc_label_7.gridy = 7;
        panel_2.add(detail_label, gbc_label_7);
        
        JLabel detail_label_value = new JLabel("세부계산");
		// TODO Auto-generated method stub
        String s = probiz.getDetail_Progressive2(Power, list);
        detail_label_value.setText(s);
        
        detail_label_value.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_label_8 = new GridBagConstraints();
        gbc_label_8.anchor = GridBagConstraints.EAST;
        gbc_label_8.insets = new Insets(0, 0, 5, 5);
        gbc_label_8.gridx = 2;
        gbc_label_8.gridy = 7;
        panel_2.add(detail_label_value, gbc_label_8);
        
        JLabel detail_label_won = new JLabel("");
        detail_label_won.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_label_3 = new GridBagConstraints();
        gbc_label_3.insets = new Insets(0, 0, 5, 5);
        gbc_label_3.gridx = 3;
        gbc_label_3.gridy = 7;
        panel_2.add(detail_label_won, gbc_label_3);
        
        //전기요금합계(월최저요금)
        JLabel sum_price_label = new JLabel("전기요금합계(월최저요금) :");
        sum_price_label.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_label_10 = new GridBagConstraints();
        gbc_label_10.anchor = GridBagConstraints.WEST;
        gbc_label_10.insets = new Insets(0, 0, 5, 5);
        gbc_label_10.gridx = 1;
        gbc_label_10.gridy = 8;
        panel_2.add(sum_price_label, gbc_label_10);
        
        JLabel sum_price_value = new JLabel("전기요금합계");
		// TODO Auto-generated method stub
        int sum_price = basic_rate + use_price; 
        sum_price_value.setText(String.valueOf(sum_price));
        
        sum_price_value.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_label_17 = new GridBagConstraints();
        gbc_label_17.anchor = GridBagConstraints.EAST;
        gbc_label_17.insets = new Insets(0, 0, 5, 5);
        gbc_label_17.gridx = 2;
        gbc_label_17.gridy = 8;
        panel_2.add(sum_price_value, gbc_label_17);
        
        JLabel sum_price_won = new JLabel("원");
        sum_price_won.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_label_9 = new GridBagConstraints();
        gbc_label_9.insets = new Insets(0, 0, 5, 5);
        gbc_label_9.gridx = 3;
        gbc_label_9.gridy = 8;
        panel_2.add(sum_price_won, gbc_label_9);
        
        //전력산업기반기금
        JLabel industry_tax_label = new JLabel("전력산업기반기금(10원 미만 절사) :");
        industry_tax_label.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_label_16 = new GridBagConstraints();
        gbc_label_16.anchor = GridBagConstraints.WEST;
        gbc_label_16.insets = new Insets(0, 0, 5, 5);
        gbc_label_16.gridx = 1;
        gbc_label_16.gridy = 9;
        panel_2.add(industry_tax_label, gbc_label_16);
        
        JLabel industry_tax_value = new JLabel("산업기반금");
        int industry_tax =  ((int)(sum_price * 0.037))/10 *10;
        industry_tax_value.setText(String.valueOf(industry_tax));
        
        industry_tax_value.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_label_20 = new GridBagConstraints();
        gbc_label_20.anchor = GridBagConstraints.EAST;
        gbc_label_20.insets = new Insets(0, 0, 5, 5);
        gbc_label_20.gridx = 2;
        gbc_label_20.gridy = 9;
        panel_2.add(industry_tax_value, gbc_label_20);
        
        JLabel industry_tax_won = new JLabel("원");
        industry_tax_won.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_label_18 = new GridBagConstraints();
        gbc_label_18.insets = new Insets(0, 0, 5, 5);
        gbc_label_18.gridx = 3;
        gbc_label_18.gridy = 9;
        panel_2.add(industry_tax_won, gbc_label_18);
        
        //부가가치세(원미만)
        JLabel VAT_label = new JLabel("부가가치세(원미만 4사 5입) :");
        VAT_label.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_label_19 = new GridBagConstraints();
        gbc_label_19.anchor = GridBagConstraints.WEST;
        gbc_label_19.insets = new Insets(0, 0, 5, 5);
        gbc_label_19.gridx = 1;
        gbc_label_19.gridy = 10;
        panel_2.add(VAT_label, gbc_label_19);
        
        JLabel VAT_label_value = new JLabel("부가가치세");
        double VAT_tax =  sum_price * 0.1;
        VAT_label_value.setText(String.valueOf(Math.round(VAT_tax)));
        
        
        VAT_label_value.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_label_5 = new GridBagConstraints();
        gbc_label_5.anchor = GridBagConstraints.EAST;
        gbc_label_5.insets = new Insets(0, 0, 5, 5);
        gbc_label_5.gridx = 2;
        gbc_label_5.gridy = 10;
        panel_2.add(VAT_label_value, gbc_label_5);
        
        JLabel VAT_label_won = new JLabel("원");
        VAT_label_won.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_label_21 = new GridBagConstraints();
        gbc_label_21.insets = new Insets(0, 0, 5, 5);
        gbc_label_21.gridx = 3;
        gbc_label_21.gridy = 10;
        panel_2.add(VAT_label_won, gbc_label_21);
        
        JLabel Charge_Price_label = new JLabel("청구금액(10원미만 절사)  : ");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 12;
        panel_2.add(Charge_Price_label, gbc_lblNewLabel_1);
        
        JLabel Charge_Price_value = new JLabel("총합");
        String Total_Price  = sum_price +"원 + "+ Math.round(industry_tax) +"원 + "+ Math.round(VAT_tax)+"원 = ";
        Charge_Price_value.setText(Total_Price+"     "+String.valueOf(Charge));
        
        GridBagConstraints gbc_lblNewLabel1 = new GridBagConstraints();
        gbc_lblNewLabel1.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel1.gridx = 2;
        gbc_lblNewLabel1.gridy = 12;
        panel_2.add(Charge_Price_value, gbc_lblNewLabel1);
        
        JLabel Charge_Price_won= new JLabel("원");
        Charge_Price_won.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_label_1 = new GridBagConstraints();
        gbc_label_1.insets = new Insets(0, 0, 5, 5);
        gbc_label_1.gridx = 3;
        gbc_label_1.gridy = 12;
        panel_2.add(Charge_Price_won, gbc_label_1);
              
        setLocation(400, 200);
        setVisible(true);
    }
    
}