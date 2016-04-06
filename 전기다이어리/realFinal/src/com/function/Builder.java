package com.function;

import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.CategoryTableXYDataset;

import com.View.ElectricView;
import com.biz.ElectricBiz;
import com.entity.ElectricVo;

public class Builder {
	public String[] getColNames() {
		return colNames;
	}
	public  void setColNames(String[] colNames) {
		Builder.colNames = colNames;
	}
	public  ArrayList<ElectricVo> getG() {
		return g;
	}
	public  void setG(ArrayList<ElectricVo> g) {
		Builder.g = g;
	}
	public  double getGuess() {
		return guess;
	}
	public  void setGuess(double guess) {
		Builder.guess = guess;
	}
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String User_name = "big5";
	private static final String User_pw = "admin1234";
	private static String row[] = new String[2];
	private static String colNames[] = {"날짜","계량기값"};
	private static ArrayList<ElectricVo> g =new ArrayList<ElectricVo>();
	private static double guess;



	public static JFreeChart graphBuild(String user,String date){ //날짜를 받으면 해당 월에대한 그래프 출력
		String[] d=null;	//=date.split("/");// YYYY/MM/DD
		if(date.contains("/")){
			d=date.split("/");
		}else if(date.contains("-")){
			d=date.split("-");
		}


		sqlChartBuild(user, null,"select * from elec where ID like '"+user+"' and  day like '"+d[0]+"/"+d[1]+"%' order by day asc",g);
		//위 코드는 sql문을 실행하면서 ArrayList<Day> g에 해당 월의 데이터를 집어넣는다.
		CategoryTableXYDataset dataset = new CategoryTableXYDataset();//dataset 정의
		//사용량
		//		dataset.add(0,0,"avg");	//평균

		for(int i=0;i<g.size();i++){
			if(g.get(i).getUsed()-g.get(0).getUsed()<0){
				dataset.add(g.get(i).getD().getDate(),
						g.get(i).getUsed()+(10000-((g.get(0).getUsed())%10000)), "used");
			}
			else{			
				dataset.add(g.get(i).getD().getDate(),g.get(i).getUsed()-g.get(0).getUsed(),"used");
			}
		}
		if(!g.isEmpty()){
			int firstDay = g.get(0).getD().getDate();
			int lastDay = g.get(g.size()-1).getD().getDate();
		}
		//평균값 그래프
		//Day.date2String(cursor)

		if(!g.isEmpty()){
			int ld = ElectricVo.LastDay((g.get(0).getD().getYear()+1900)+"/"+g.get(0).getD().getMonth()+"/"+0);


			if(g.size()>1){
				if(g.get(g.size()-1).getD().getDate()<=ld){
					dataset.add(g.get(0).getD().getDate(),0,"avg");
					//				dataset.add(g.get(g.size()-1).getD().getDate(),g.get(g.size()-1).getUsed()-g.get(0).getUsed(),"avg");
					if(g.get(g.size()-1).getUsed()-g.get(0).getUsed()<0){
						dataset.add(ld, (g.get(g.size()-1).getUsed()+10000-g.get(0).getUsed())*(ld  -  g.get(0).getD().getDate())   /    (g.get(g.size()-1).getD().getDate()-g.get(0).getD().getDate()), "avg");
						guess=(g.get(g.size()-1).getUsed()+10000-g.get(0).getUsed())*(ld  -  g.get(0).getD().getDate())   /    (g.get(g.size()-1).getD().getDate()-g.get(0).getD().getDate());

					}else{
						guess =(g.get(g.size()-1).getUsed()-g.get(0).getUsed())   *   (ld  -  g.get(0).getD().getDate())   /    (g.get(g.size()-1).getD().getDate()-g.get(0).getD().getDate());

						dataset.add(ld,(g.get(g.size()-1).getUsed()-g.get(0).getUsed())   *   (ld  -  g.get(0).getD().getDate())   /    (g.get(g.size()-1).getD().getDate()-g.get(0).getD().getDate()),"avg");
					}
				}
			}
		}
		//평균 끝
		//누진세 경계값 그래프
		//100

		dataset.add(0,100,"1");
		dataset.add(0,200,"2");
		dataset.add(0,300,"3");
		dataset.add(0,400,"4");
		dataset.add(0,500,"5");
		dataset.add(31,100,"1");
		dataset.add(31,200,"2");
		dataset.add(31,300,"3");
		dataset.add(31,400,"4");
		dataset.add(31,500,"5");




		XYSplineRenderer r = new XYSplineRenderer();

		r.setSeriesLinesVisible(0, true);	//사용량
		r.setSeriesShapesVisible(0, true);
		r.setSeriesLinesVisible(1, true);	//평균 (점없음)
		r.setSeriesShapesVisible(1, false);
		r.setSeriesLinesVisible(2, true);
		r.setSeriesShapesVisible(2, false);
		r.setSeriesLinesVisible(3, true);
		r.setSeriesShapesVisible(3, false);
		r.setSeriesLinesVisible(4, true);
		r.setSeriesShapesVisible(4, false);
		r.setSeriesLinesVisible(5, true);
		r.setSeriesShapesVisible(5, false);
		r.setSeriesLinesVisible(6, true);
		r.setSeriesShapesVisible(6, false);

		//////////////////////////////////////////////////////////////////////////
		r.setItemLabelPaint(Color.BLACK);
		r.setItemLabelFont(new Font("굴림",Font.PLAIN,10));
		r.setItemLabelsVisible(true);
		r.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator("{2}",NumberFormat.getNumberInstance(),NumberFormat.getNumberInstance()));

		////////////////////////////////////////////////////////////////////////

		//	       XYPlot xyplot = new XYPlot(dataset, domain, range, r);
		JFreeChart chart = ChartFactory.createXYLineChart("", "Date", "amount(kWh)", dataset);
		//각각 제목, x축 ,y축, dataset
		XYPlot xyplot = (XYPlot)chart.getPlot();
		xyplot.setRenderer(r);



		chart.setBackgroundPaint(Color.white);

		return chart;

	}
	public static void sqlChartBuild(String user ,JTable table,String sql,ArrayList<ElectricVo> d){


		DefaultTableModel model = new DefaultTableModel(colNames, 0);		
		model.addRow(colNames);
		if(table!=null)table.setModel(model);

		d.clear();

		ElectricBiz eb = new ElectricBiz();
		List<ElectricVo> list=null;
		list =eb.selectSQL(sql);
		for(int i=0;i<list.size();i++){
			row[0]=String.valueOf(list.get(i).getD());
			row[1]=String.valueOf(list.get(i).getUsed());
			d.add(new ElectricVo(user, list.get(i).getD(),list.get(i).getUsed()));
			model.addRow(row);
		}


		/*try {
			Connection myConn = null;
			PreparedStatement myPStat = null;
			ResultSet myRe = null;
			Class.forName("oracle.jdbc.driver.OracleDriver");

			myConn = DriverManager.getConnection(url, User_name, User_pw);





			myPStat = (PreparedStatement) myConn.prepareStatement(sql);

			if(sql.contains("insert into")){//insert
				myPStat.executeUpdate(sql);





			}else{
				myRe = myPStat.executeQuery();
				while (myRe.next()) {

					row[0]=String.valueOf(myRe.getDate("day"));
					row[1]=String.valueOf(myRe.getDouble("use"));
					d.add(new electricVo(myRe.getDate("day"),myRe.getDouble("use")));
					model.addRow(row);
				}
			}



		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/

	}

}
