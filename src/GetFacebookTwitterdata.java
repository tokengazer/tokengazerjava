import java.io.*;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.selector.Selectable;
public  class GetFacebookTwitterdata implements PageProcessor {
	public static int App_api_id=220865;
	public static String App_api_hash="679fed61d1930a804eb6d153677792f3";
	public static String token="608015553:AAExwq5eLT7Uwy_qWnZ878MMPb-0Y1F-LJ8";
	public static void main(String[] args) {
		//Spider.create(new GetFacebookTwitterdata()).addUrl("https://api.coingecko.com/api/v3/coins/bitcoin?id=1").run();;
	}
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		Html html=page.getHtml();
		String url=page.getRequest().getUrl();
		int id=Integer.parseInt(url.split("=")[1]);
		//JsonPathSelector json=new JsonPathSelector(page.getRawText());
		JsonPathSelector facebook=new JsonPathSelector("$.community_data.facebook_likes");
		String facebook_friends=facebook.select(page.getRawText());
		JsonPathSelector twitter=new JsonPathSelector("$.community_data.twitter_followers");
		String twitter_fanscount=twitter.select(page.getRawText());
		String sql="update ico_Analysis set Twitter_FansCount="+twitter_fanscount+",Facebook_Friends="+facebook_friends+" where id="+id;
		Connection con=null;
		String driver = "com.mysql.jdbc.Driver";
        String sqlurl = "jdbc:mysql://rm-bp1kq68i5h4493twvzo.mysql.rds.aliyuncs.com:3306/app_tokenworm";
        String user = "lybjx";
        String password = "Lybjx54709488dh";
        try {
			con=DriverManager.getConnection(sqlurl, user, password);
			Statement stat=con.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.print(sql);
	}
	
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}
	

}
