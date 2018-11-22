import java.io.*;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
public  class Gethistorydata implements PageProcessor {
	public static int App_api_id=220865;
	public static String App_api_hash="679fed61d1930a804eb6d153677792f3";
	public static String token="608015553:AAExwq5eLT7Uwy_qWnZ878MMPb-0Y1F-LJ8";
	public static void main(String[] args) {
		//String url="https://api.telegram.org/bot"+token+"/getMessage?id=hashgraphnews";
		//String[]list= {"bitcoin","ethereum","ripple","bitcoin-cash","eos","litecoin","stellar","cardano","iota","golem-network-tokens","enigma","rlc","sonm","elastic","gridcoin","foldingcoin","tether"};
		//String[]list1= {"bitcoin","ethereum","XRP","bitcoin-cash","eos","litecoin","stellar","cardano","iota","golem","enigma","iExec RLC","sonm","elastic","gridcoin","FoldingCoin","Tether"};
		String[]list= {"sonm"};
		String[]list1= {"sonm"};
		Connection con=null;
		String driver = "com.mysql.jdbc.Driver";

        String sqlurl = "jdbc:mysql://localhost:3306/tokengazer";

        String user = "tokengazer";
        Statement stat=null;
        String password = "Token123";
        String sql1="select ticker,name  from historyname where id>50 and Ticker='EOS'";
        ResultSet rs=null;
        try {
			con=DriverManager.getConnection(sqlurl, user, password);
			stat=con.createStatement();
			rs=stat.executeQuery(sql1);
			int i=0;
			ArrayList<Request> list2=new ArrayList<Request>();
			while(rs.next()) {
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//
				String da=df.format(new Date());
				String url="https://coinmarketcap.com/currencies/"+rs.getString("name")+"/historical-data/?start=20170101&end="+da+"&name="+rs.getString("name")+"&ticker="+rs.getString("ticker");
				Request request=new Request(url);
				list2.add(request);
				//Spider.create(new Gethistory()).addUrl(url).run();
				//i++;
			}
			Request[] strings = new Request[list2.size()];
	        list2.toArray(strings);
	        Spider.create(new Gethistory()).addRequest(strings).thread(2).run();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		//String url="https://coinmarketcap.com/currencies/enigma-project/historical-data/?start=20130428&end=20180621";
		
	}
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}
	

}
