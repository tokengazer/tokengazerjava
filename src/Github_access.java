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
public class Github_access implements PageProcessor{

	public static void main(String[]args) {
		Request request=new Request("https://api.github.com/authorizations");
		request.addHeader("Authorization", "Basic bHliang6bHliang1NDcwOTQ4OGRo");

		request.addHeader("Accept","application/vnd.github.hellcat-preview+json");
		request.addHeader("User-Agen","Awesome-Octocat-App").setMethod("GET");
		Spider.create(new Github_accessfromgithub()).addRequest(request).run();;
		
	}
	
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me().setSleepTime(1000);
	}

	@Override
	public void process(Page arg0) {
		// TODO Auto-generated method stub
		
	}

}
