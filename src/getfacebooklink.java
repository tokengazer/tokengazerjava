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

public class getfacebooklink implements PageProcessor{
	private Site site = new Site().setRetryTimes(3).setSleepTime(100).setDomain("www.icorating.com");
	public static void main(String[] args) {
		
		list();
	}
	public static void list() {
		String sql="select id,facebookurl from ico_Analysis where facebookurl<>'' and id >16730";
		Mysql ms=new Mysql();
		Connection con;
		int res1=-2;
        //椹卞姩绋嬪簭鍚�
        String driver = "com.mysql.jdbc.Driver";
        //URL鎸囧悜瑕佽闂殑鏁版嵁搴撳悕mydata
        String url = "jdbc:mysql://13.114.134.239:3306/app_tokenworm";
        //MySQL閰嶇疆鏃剁殑鐢ㄦ埛鍚�
        String user = "lybjx";
        //MySQL閰嶇疆鏃剁殑瀵嗙爜
        String password = "123456";
        ResultSet rs=null;
        //閬嶅巻鏌ヨ缁撴灉闆�
        try {
            //鍔犺浇椹卞姩绋嬪簭
            Class.forName(driver);
            //1.getConnection()鏂规硶锛岃繛鎺ySQL鏁版嵁搴擄紒锛�
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.鍒涘缓statement绫诲璞★紝鐢ㄦ潵鎵цSQL璇彞锛侊紒
            Statement statement = con.createStatement();
            rs = statement.executeQuery( sql );// sql涓哄緟鎵ц鐨剆ql
            /*String[] Urllist =new String[rs.getRow()];
            int index=0;
            while(rs.next()){//閬嶅巻缁撴灉闆�
            	Urllist[index]=rs.getString("telegramurl");
            }*/
            ArrayList<Request> list=new ArrayList<Request>();
            while(rs.next()){
            	Request request = new Request(rs.getString("facebookurl"));
            	request.addHeader("Connection", "close").addHeader("Cache-Control", "max-age=0").addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                request.addHeader("Origin", "https://www.facebook.com").addHeader("Upgrade-Insecure-Requests", "1").addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
                request.addHeader("Content-Type","application/x-www-form-urlencoded").addHeader("Referer", "https://www.facebook.com/").addHeader("Accept-Language", "zh-CN,zh;q=0.8");
                list.add(request);
            }
            Request[] strings = new Request[list.size()];
            list.toArray(strings);
           
            
            Spider.create(new getfacebookfans()).addRequest(strings).thread(4).run();
    		
            con.close();
            
        } catch(ClassNotFoundException e) {   
            //鏁版嵁搴撻┍鍔ㄧ被寮傚父澶勭悊
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //鏁版嵁搴撹繛鎺ュけ璐ュ紓甯稿鐞�
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            rs=null;
        }finally{
            System.out.println("鏁版嵁搴撴暟鎹垚鍔熻幏鍙栵紒锛�");
            
        }
        
		

		
	}
	

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		//System.out.println(page.getHeaders());
		Selectable selectable=html.xpath("//div[@class='tgme_page_extra'");
		String fans=selectable.toString();
		System.out.println(fans);
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}
	
}
