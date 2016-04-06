package com.function;

import java.awt.event.ItemEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Evaluation {
   ImageIcon i1;
   ImageIcon i2;
   ImageIcon i3;
   ImageIcon i4;
   ImageIcon i5;
   ImageIcon i6;
   static int CL_Score = 0;
   public Evaluation(){
      i1=new ImageIcon("icon1.png");
       i2=new ImageIcon("icon2.png");
       i3=new ImageIcon("icon3.png");
       i4=new ImageIcon("icon4.png");
       i5=new ImageIcon("icon5.png");
       i6=new ImageIcon("icon6.png");   
   }
   
   public void Ch(JLabel image,JLabel text,ItemEvent e){
		if (e.getStateChange() == ItemEvent.SELECTED){
			CL_Score += 20;
		}else{
			CL_Score -= 20;
		}
		result(image,text,CL_Score);
   }
   
   public void result(JLabel image,JLabel text,int score){
      switch(score){

      case 0: 
         image.setIcon(i6);
         text.setText(score+"점. 엉망진창이군");
         break;

      case 20:
         image.setIcon(i5);
         text.setText(score+"점.돈 많은가봐요 ?_? ");
         break;

      case 40:
         image.setIcon(i4);
         text.setText(score+"점. 좀 더 아껴야하지않겠어요?");
         break;

      case 60:
         image.setIcon(i3);
         text.setText(score+"점. 좀만 더 노력합시다!");
         break;

      case 80:
         image.setIcon(i2);
         text.setText(score+"점. 잘하고 있어요~!");
         break;

      case 100: 
         image.setIcon(i1);
         text.setText(score+"점. 당신이 미래의 재벌");
         break;
      }
   }
}