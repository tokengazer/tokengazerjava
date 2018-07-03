import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.selector.Selectable;
import java.io.*;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.regexp.internal.recompile;
public  class GetDataFromIcoratingStatistics implements PageProcessor{
	public static void main(String[] args) {
		ArrayList<Request>li=new ArrayList<Request>();
		for(int i=0;i<=22;i++) {
			if(i!=0&&i!=5&&i!=7&&i!=15) {
				continue;
			}
			String url="https://icorating.com/statistics/jsonStats/?sort=voted.desc&icoPage="+i;
			Request request=new Request(url);
			li.add(request);
		}
		Request[] strings = new Request[li.size()];
        li.toArray(strings);
		Spider.create(new GetDataFromIcoratingStatistics()).addRequest(strings).run();
		
	}
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		String html = page.getRawText();
		JsonPathSelector json=new JsonPathSelector("$.data_icos[*].code");
		List<String> ticker=json.selectList(html);
		List price=new JsonPathSelector("$.data_icos[*].current_price").selectList(html);
		List icostartdate=new JsonPathSelector("$.data_icos[*].start_date").selectList(html);
		List tokenprice=new JsonPathSelector("$.data_icos[*].token_price").selectList(html);
		List icolink=new JsonPathSelector("$.data_icos[*].url").selectList(html);
		List ico_raised_money=new JsonPathSelector("$.data_icos[*].collected").selectList(html);
		Connection con=null;
		String driver = "com.mysql.jdbc.Driver";
        
        String url = "jdbc:mysql://rm-bp1kq68i5h4493twvzo.mysql.rds.aliyuncs.com:3306/app_tokenworm";
        
        String user = "lybjx";
        Statement stat=null;
        String password = "Lybjx54709488dh";
        ResultSet rs=null;
        try {
			con=DriverManager.getConnection(url, user, password);
			 stat=con.createStatement();
			 int i=0;
		        for(String arr:ticker) {
		        	String ticker1=arr;
		        	String sql1="select * from ico_Analysis where ticker='"+ticker1+"' and Ico_time='';";
		        	System.out.print(sql1);
		        	rs=stat.executeQuery(sql1);
		        	int count=0;
		        	while(rs.next()) {
		        		count+=1;
		        	}
		        	if(count>0) {
		        		String Ico_time=icostartdate.get(i).toString();
		        		String Ico_Price_Usd=tokenprice.get(i).toString();
		        		String ico_link="https://icorating.com/"+icolink.get(i).toString();
		        		String ico_raised_money1=ico_raised_money.get(i).toString().replaceAll(",", "");
		        		String updatesql="update ico_Analysis set Ico_time='"+Ico_time+"',ICO_Price_Usd='"+Ico_Price_Usd+"',icolink='"+ico_link+"',ico_raised_money="+ico_raised_money1+" where ticker='"+ticker1+"' and Ico_time='';";
		        	stat.addBatch(updatesql);
		        	}
		        	System.out.print(count);
		        }
		        stat.executeBatch();
				con.setAutoCommit(false);
				con.commit();
				con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        
        
		System.out.println(json.selectList(html));
		System.out.println(1);
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}

}
