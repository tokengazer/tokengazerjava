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

public class IcodropsWhitelist implements PageProcessor{
	private Site site = new Site().setRetryTimes(3).setSleepTime(100).setDomain("www.icorating.com");
	public static void main(String[] args) {
		start();
		
	}
	public static void start() {
		Spider.create(new IcodropsWhitelist()).addUrl("https://icodrops.com/whitelist/").run();
		
	}
	

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		Connection con=null;
		String driver = "com.mysql.jdbc.Driver";

        String sqlurl = "jdbc:mysql://rm-bp1kq68i5h4493twvzo.mysql.rds.aliyuncs.com:3306/app_tokenworm";

        String user = "lybjx";

        String password = "Lybjx54709488dh";
		Html html = page.getHtml();
		//System.out.println(page.getHeaders());
		Selectable selectable=html.xpath("//div[@id='whitecol'");
		List<String> Tmp1=html.xpath("//div[@class='white_info']/").xpath("//a/text()").all();
		String[] array=Tmp1.toArray(new String[Tmp1.size()]);
		List<String> NameList = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
	        //NameList.add(array[i]);
	    }
		
		List<String> Tmp2=html.xpath("//div[@class='whitelist_date']/text()").all();
		String[] array2=Tmp2.toArray(new String[Tmp2.size()]);
		List<String> WhiteListStatusList = new ArrayList<>();
		for (int i = 0; i < array2.length; i++) {
			//WhiteListStatusList.add(array2[i]);
	    }
		
		List<String> links=selectable.xpath("//a[@id='ccc']").links().regex("(https://icodrops\\.com/(\\w+)*)").all();
		String[] array3=links.toArray(new String[links.size()]);
		List<String> LinksList = new ArrayList<>();
		
		
		for (int i = 0; i < array3.length; i++) {
			//LinksList.add(array3[i]);
		}
		System.out.println(array.length);
		for (int i = 0; i < array.length; i++) {
			String sql="select count(*) from Project_WhiteList where icolink='"+array3[i]+"';";
			try {
				con=DriverManager.getConnection(sqlurl, user, password);
				Statement stat=con.createStatement();
				ResultSet rs=stat.executeQuery(sql);
				rs.next();
				int count=Integer.parseInt(rs.getString("count(*)"));
				String sql1="";
				if(count==0) {
				sql1="insert into Project_WhiteList (id,icolink,status) values (Null,'"+array3[i]+"','"+array2[i]+"');";
				i++;
				stat.executeUpdate(sql1);
				System.out.println(sql1);
				con.close();
				continue;
				}else {
					sql1="update Project_WhiteList set status='"+array2[i]+"' where icolink='"+array3[i]+"';";
					i++;
					stat.executeUpdate(sql1);
					System.out.println(sql1);
					con.close();
					continue;
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(i);
	    }
		ArrayList<Request> list=new ArrayList<Request>();
       for(String url:links) {
    	   Request request=new Request(url);
    	   list.add(request);
    	   //Spider.create(new Icodropsdetail()).addUrl(url).run();
       }
       Request[] strings = new Request[list.size()];
       list.toArray(strings);
       Spider.create(new Icodropsdetail()).addRequest(strings).thread(10).run();
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}
	
}
