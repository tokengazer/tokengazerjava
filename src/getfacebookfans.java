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

public class getfacebookfans implements PageProcessor{
	private Site site = new Site().setRetryTimes(3).setSleepTime(100).setDomain("www.icorating.com");
	public static void main(String[] args) {
		
		
	}
	

	@Override
	public void process(Page page) {
		Request request=page.getRequest();
		String url=request.getUrl();
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		//System.out.println(html);
		
		//System.out.println(page.getHeaders());
		Selectable selectable=html.xpath("//div[@id='pages_side_column']").xpath("//div[@class='_1xnd']");
		List<String> list=selectable.xpath("//div[@class='_2pi9 _2pi2']").all();
		String tmp1=list.get(1).split("<div class=\"_4bl9\">")[1].split("<div>")[1].split("</div>")[0].replace(" 位用户关注了", "").replaceAll(",", "");
		tmp1=tmp1.trim();
		System.out.println(url);
		String sql="update ico_Analysis set Facebook_Friends="+tmp1+" where facebookurl='"+url+"';";
		Mysql ms=new Mysql();
		System.out.println(sql);
		System.out.println(ms.Excutesql(sql,"update"));
		System.out.println(tmp1);
		/*String fans=selectable.toString();
		fans=fans.replace("members", "").replaceAll(" ", "");
		int fancount=Integer.valueOf(fans);
		String sql ="update ico_Analysis set Facebook_Friends="+fancount+" where facebookurl='"+url+"'";
		Mysql ms=new Mysql();
		System.out.println(ms.Excutesql(sql));
		System.out.println(sql);*/
		
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me().setSleepTime(2000);
	}
	
}
