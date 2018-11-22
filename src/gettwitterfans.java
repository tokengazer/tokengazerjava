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

public class gettwitterfans implements PageProcessor{
	private Site site = new Site().setRetryTimes(3).setSleepTime(100).setDomain("www.icorating.com");
	public static void main(String[] args) {
		Spider.create(new gettwitterfans()).addUrl("https://twitter.com/bitcoin").run();;
		//list();
	}
	
	

	@Override
	public void process(Page page) {
		Request request=page.getRequest();
		String url=request.getUrl();
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		//System.out.print(html);
		Selectable selectable=html.xpath("//li[@class='ProfileNav-item--followers']");
		String countstring=selectable.toString().split("data-count=\"")[1].split("\"")[0];
		int count=Integer.parseInt(countstring);
		Connection con=null;
		String driver = "com.mysql.jdbc.Driver";
        //URL鎸囧悜瑕佽闂殑鏁版嵁搴撳悕mydata
        String sqlurl = "jdbc:mysql://rm-bp1kq68i5h4493twvzo.mysql.rds.aliyuncs.com:3306/app_tokenworm";
        //MySQL閰嶇疆鏃剁殑鐢ㄦ埛鍚�
        String user = "lybjx";
        //MySQL閰嶇疆鏃剁殑瀵嗙爜
        String password = "Lybjx54709488dh";
		try {
			con=DriverManager.getConnection(sqlurl, user, password);
			Statement stat=con.createStatement();
			String sql="update ico_Analysis set Twitter_Fanscount="+count+" where twitterurl ='"+url+"';";
			System.out.print(sql);
			System.out.print(stat.executeUpdate(sql));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}
	
}
