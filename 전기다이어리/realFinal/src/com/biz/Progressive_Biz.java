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
   
   public int CalculateProgressive(int Power, String button) { //총 kWh 인 Power 입력 시 그 값에만 맞는 list 반환
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
      explain ="고객님 전기누진세 등급은 " + grade +"입니다. \n기본요금을 " +basic_rate+"원 내며"
            +" 나머지는 누진세 추가요금을 지불하고 있습니다.\n"
            +(grade -1) +"등급이 되기 위해서는 최소 "+saving_pwh+"kWh 만큼 전기를 절약해야 합니다.\n"
            +(grade-1) +"등급으로 절약하면 현재에서 최소 "+ (Power_Charge-saving_price) +"원 이상이 절약됩니다.";
      } else {
      explain ="고객님 전기누진세 등급은 " + grade +"입니다. \n기본요금을 " +basic_rate+"원 내며"
               +" 나머지는 누진세 추가요금을 지불하고 있습니다.\n"
               +"전기절약을 매우 잘하고 계십니다."; 
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
   
   public int getDetail_Progressive1(int Power,List<ProgressiveVO> list){ //사용량요금(원미만 절사) 
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
   
   public String getDetail_Progressive2(int Power,List<ProgressiveVO> list){ //세부 계산내역 
      Connection conn = getConnection();
      String s ="";
      for(int i =0; i<list.size(); i++){
         if(Power > 100){
            s += list.get(i).getGrade()+"단계: "+list.get(i).getAdditional_rate()  +"원 X 100kWh     ";
            Power -= 100;
         }
         else{
            s += list.get(i).getGrade()+"단계: "+list.get(i).getAdditional_rate()  +"원 X " +Power+"kWh";
         }
      }
      Close(conn);
      return s;
   }
}