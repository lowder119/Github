package com.biz;
import static com.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.dao.Progressive_DAO;
import com.entity.ProgressiveVO;

public class Progressive_Biz { 
   List<ProgressiveVO> list = new ArrayList<>(); 
   public Progressive_Biz(){}
   
   public int CalculateProgressive(int Power, String button) { //�� kWh �� Power �Է� �� �� ������ �´� list ��ȯ
      Connection conn = getConnection();
      int price = new Progressive_DAO(conn).CalculateProgressive(Power, button);
      Close(conn);
      return price;
   }
   
   public List<ProgressiveVO> getProgressiveList(){
      Connection conn = getConnection();
      list = new Progressive_DAO(conn).getProgressiveList();
      Close(conn);
      return list;
   }
   
   public String getExplain(int Power, String button, int Power_Charge){
      
      Connection conn = getConnection();
      String explain = null;
      int grade = 0;
      int basic_rate=0;
      for(int i =0; i<list.size(); i++){
         basic_rate =list.get(i).getBasic_rate();
         grade++;
      }
      /////////////////////////
      int saving_pwh = 0;
      if( (Power<600) &&(Power%100 !=0)){ saving_pwh = Power % 100;}
      else if((Power<600) &&(Power%100 ==0)){saving_pwh = Power -100;}
      else {saving_pwh = Power - 500 ;}
      int saving_power = (Power / 100) *100;
      
      if(Power > 500){saving_power = 500;}
      else if( (Power %100 ==0)){saving_power -= 100;}
      int saving_price = new Progressive_DAO(conn).CalculateProgressive(saving_power, button);
      if(grade != 1){
      explain ="���� ���⴩���� ����� " + grade +"�Դϴ�. \n�⺻����� " +basic_rate+"�� ����"
            +" �������� ������ �߰������ �����ϰ� �ֽ��ϴ�.\n"
            +(grade -1) +"����� �Ǳ� ���ؼ��� �ּ� "+saving_pwh+"kWh ��ŭ ���⸦ �����ؾ� �մϴ�.\n"
            +(grade-1) +"������� �����ϸ� ���翡�� �ּ� "+ (Power_Charge-saving_price) +"�� �̻��� ����˴ϴ�.";
      } else {
      explain ="���� ���⴩���� ����� " + grade +"�Դϴ�. \n�⺻����� " +basic_rate+"�� ����"
               +" �������� ������ �߰������ �����ϰ� �ֽ��ϴ�.\n"
               +"���������� �ſ� ���ϰ� ��ʴϴ�."; 
      }
      ///////////////
      Close(conn);
      return explain;
   }
   
   public int getBasic_Rate(int Power,List<ProgressiveVO> list){
      
      Connection conn = getConnection();
      int basic_rate=0;
      for(int i = 0 ; i<list.size(); i++){
         basic_rate = list.get(i).getBasic_rate();
      }
      Close(conn);
      return basic_rate;
   }
   
   public int getDetail_Progressive1(int Power,List<ProgressiveVO> list){ //��뷮���(���̸� ����) 
      Connection conn = getConnection();
      double n =0;
      for(int i =0; i<list.size(); i++){
         if(Power > 100){
            n += list.get(i).getAdditional_rate()  * 100;
            Power -= 100;
         }
         else{
            n += list.get(i).getAdditional_rate()  * Power;
         }
      }
      n = Math.floor(n);
      int usded_charge = (int)n;
      Close(conn);
      return usded_charge;
   }
   
   public String getDetail_Progressive2(int Power,List<ProgressiveVO> list){ //���� ��곻�� 
      Connection conn = getConnection();
      String s ="";
      for(int i =0; i<list.size(); i++){
         if(Power > 100){
            s += list.get(i).getGrade()+"�ܰ�: "+list.get(i).getAdditional_rate()  +"�� X 100kWh     ";
            Power -= 100;
         }
         else{
            s += list.get(i).getGrade()+"�ܰ�: "+list.get(i).getAdditional_rate()  +"�� X " +Power+"kWh";
         }
      }
      Close(conn);
      return s;
   }
}