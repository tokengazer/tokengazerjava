import java.io.*;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
public  class Gethistory implements PageProcessor {
	public static int App_api_id=220865;
	public static String App_api_hash="679fed61d1930a804eb6d153677792f3";
	public static String token="608015553:AAExwq5eLT7Uwy_qWnZ878MMPb-0Y1F-LJ8";
	public static void main(String[] args) {
	}
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		Html html=page.getHtml();
		Selectable table=html.xpath("//table[@class='table']").xpath("//tbody");
		Connection con = null;
		int res1=-2;
        //椹卞姩绋嬪簭鍚�
        String driver = "com.mysql.jdbc.Driver";
        //URL鎸囧悜瑕佽闂殑鏁版嵁搴撳悕mydata
        String url = "jdbc:mysql://rm-bp1kq68i5h4493twvzo.mysql.rds.aliyuncs.com:3306/app_tokenworm";
        //MySQL閰嶇疆鏃剁殑鐢ㄦ埛鍚�
        String user = "lybjx";
        //MySQL閰嶇疆鏃剁殑瀵嗙爜
        String password = "Lybjx54709488dh";
        //String insertsql="";
		//Selectable trlist=table.xpath("//tr");
		for(Selectable trlist:table.xpath("//tr").nodes()) {
			int i=0;
			String datetime="";
			String openprice="";
			String highprice="";
			String lowprice="";
			String endprice="";
			String volumn="";
			String marketcap="";
			String sql="";
			for(Selectable tdlist:trlist.xpath("//td").nodes()) {
				//datetime=tdlist.nodes().get(1).toString();
				if(i==0) {
					datetime=tdlist.xpath("//td/text()").nodes().get(0).toString();
				}
				if(i==1) {
					openprice=tdlist.xpath("//td/text()").nodes().get(0).toString();
				}
				if(i==2) {
					highprice=tdlist.xpath("//td/text()").nodes().get(0).toString();
				}
				if(i==3) {
					lowprice=tdlist.xpath("//td/text()").nodes().get(0).toString();
				}
				if(i==4) {
					endprice=tdlist.xpath("//td/text()").nodes().get(0).toString();
				}
				if(i==5) {
					volumn=tdlist.xpath("//td/text()").nodes().get(0).toString();
				}
				if(i==6) {
					marketcap=tdlist.xpath("//td/text()").nodes().get(0).toString();
				}
				i++;
				//datetime=tdlist.toString();
				
			}
			 try {
					con = DriverManager.getConnection(url,user,password);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			try {
				Statement stat=null;
				stat=con.createStatement();
				sql="insert into history (name,datetime,openprice,highprice,lowprice,endprice,volumn,marketcap) values ('Enigma','"+datetime+"','"+openprice+"','"+highprice+"','"+lowprice+"','"+endprice+"','"+volumn+"','"+marketcap+"');";
				
				stat.execute(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print(sql);
		}
		//System.out.print(table);
		
	}
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}
	

}
