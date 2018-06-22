import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;
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
public class Github_accessfromgithub implements PageProcessor{

	public static void main(String[]args) {
		Json html=new Page().getJson();
		System.out.print(html);
		
	}
	public static ResultSet gettokenlist() {
		Mysql ms=new Mysql();
		Request request=new Request();
		request.addHeader("Authorization", "Basic bHliang6bHliang1NDcwOTQ4OGRo");

		request.addHeader("Accept","application/vnd.github.hellcat-preview+json");
		request.addHeader("User-Agen","Awesome-Octocat-App");
		Connection con;
		int res1=-2;
		String sql="select * from github_tokenlist where status=1";
        //
        String driver = "com.mysql.jdbc.Driver";
        //URL
        String url = "jdbc:mysql://13.114.134.239:3306/app_tokenworm";
        //MySQL
        String user = "lybjx";
        //MySQ
        String password = "123456";
        ResultSet rs=null;
        //
        //ArrayList list=new ArrayList();
        try {
        	Class.forName(driver);
            //1.getConnection()
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            
            Statement statement = con.createStatement();
            rs = statement.executeQuery( sql );// 
            System.out.print(rs.getRow());
            int count=0;
            
            
        }catch(ClassNotFoundException e) {
        	
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void process(Page arg0) {
		// TODO Auto-generated method stub
		
	}

}
