import java.io.*;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
public  class Getlogotolocal implements PageProcessor {
	public static int App_api_id=220865;
	public static String App_api_hash="679fed61d1930a804eb6d153677792f3";
	public static String token="608015553:AAExwq5eLT7Uwy_qWnZ878MMPb-0Y1F-LJ8";
	public static void main(String[] args) {
		list();
	}
	
	private static void list() {
		// TODO Auto-generated method stub
		String sql="select id, logo from ico_Analysis where locallogo <>''";
		Connection con=null;
		Connection con1=null;
		String driver = "com.mysql.jdbc.Driver";
		String sqlurl="jdbc:mysql://rm-bp1kq68i5h4493twvzo.mysql.rds.aliyuncs.com:3306/app_tokenworm";
        //URL指向要访问的数据库名mydata
        //MySQL配置时的用户名
        String user = "lybjx";
        //MySQL配置时的密码
        String password = "Lybjx54709488dh";
        ResultSet rs=null;
        System.out.print(1);
        try {
		con=DriverManager.getConnection(sqlurl,user,password);
		Statement stat=con.createStatement();
		rs=stat.executeQuery(sql);
		//rs.last();
		
		System.out.print(22);
		ArrayList<Request> list=new ArrayList<Request>();
		String updatesql="";
		con1=DriverManager.getConnection(sqlurl,user,password);
    	Statement st1=con1.createStatement();
    	boolean autoCommit=con1.getAutoCommit();
		
	        while(rs.next()){
	        	Request request = new Request(rs.getString("logo"));
	        	request.addHeader("Authorization","token "+ token).addHeader("Accept", "application/vnd.github.hellcat-preview+json").addHeader("User-Agent", "Awesome-Octocat-App");
	        	list.add(request); 
	        	String url=rs.getString("logo");
	    		String[] splitlist=url.split("/");
	    		int c=splitlist.length;
	    		url=splitlist[c-1];
	    		//ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
	    		String uri="C:/Users/jack/Desktop/logos/"+url;
	    		  //关闭自动提交
	    		  
	    		//updatesql="update ico_Analysis set locallogo='"+uri+"' where id="+rs.getInt("id")+";";
	    		//st1.addBatch(updatesql);
	        }
	        con=DriverManager.getConnection(sqlurl, user, password);
    		//st1.executeBatch();
    		//con1.setAutoCommit(false);
    		//con1.commit();
    		//con1.setAutoCommit(autoCommit);
    		//con1.close();
	        con.close();
	        Request[] strings = new Request[list.size()];
            list.toArray(strings);
           
            
            Spider.create(new getlogo()).addRequest(strings).thread(100).run();
        }
        catch(SQLException e) {
        	e.printStackTrace();
        }
        System.out.print(11);
	}

	@Override
	public void process(Page page) {
		
		
	}
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}
	

}
