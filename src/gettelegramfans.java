import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
import java.io.*;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class gettelegramfans implements PageProcessor{
	private Site site = new Site().setRetryTimes(3).setSleepTime(100).setDomain("www.icorating.com");
	public static void main(String[] args) {
		list();
		
	}
	public static void list() {
		String sql="select id,telegramurl from ico_Analysis where telegramurl<>'' and id>17784";
		Mysql ms=new Mysql();
		Connection con;
		int res1=-2;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://13.114.134.239:3306/app_tokenworm";
        //MySQL配置时的用户名
        String user = "lybjx";
        //MySQL配置时的密码
        String password = "123456";
        ResultSet rs=null;
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            rs = statement.executeQuery( sql );// sql为待执行的sql
            ArrayList<Request> list=new ArrayList<Request>();
            while(rs.next()){//遍历结果集
            	Request request = new Request(rs.getString("telegramurl"));
            	list.add(request);   
            }
            Request[] strings = new Request[list.size()];
            list.toArray(strings);
           
            
            Spider.create(new gettelegramfans()).addRequest(strings).thread(100).run();
            con.close();
            
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            rs=null;
        }finally{
            System.out.println("数据库数据成功获取！！");
            
        }
        
		

		
	}
	public static void ended() {
		Spider.create(new gettelegramfans()).addUrl("https://icodrops.com/category/ended-ico/").run();
		
	}
	public static void upcoming() {
		Spider.create(new gettelegramfans()).addUrl("https://icodrops.com/category/upcoming-ico/").run();
		
	}

	@Override
	public void process(Page page) {
		Request request=page.getRequest();
		String url=request.getUrl();
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		Selectable selectable=html.xpath("//div[@class='tgme_page_extra']/text()");
		String fans=selectable.toString();
		fans=fans.replace("members", "").replaceAll(" ", "");
		int fancount=Integer.valueOf(fans);
		String sql ="update ico_Analysis set Telegram_fans="+fancount+" where telegramurl='"+url+"'";
		Mysql ms=new Mysql();
		System.out.println(ms.Excutesql(sql,"update"));
		System.out.println(sql);
		
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me().setSleepTime(1000);
	}
	
}
