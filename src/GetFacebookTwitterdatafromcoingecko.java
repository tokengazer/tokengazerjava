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
import us.codecraft.webmagic.selector.Selectable;
public  class GetFacebookTwitterdatafromcoingecko implements PageProcessor {
	public static int App_api_id=220865;
	public static String App_api_hash="679fed61d1930a804eb6d153677792f3";
	public static String token="608015553:AAExwq5eLT7Uwy_qWnZ878MMPb-0Y1F-LJ8";
	public static void main(String[] args) {
		list();
	}
	@Override
	public void process(Page page) {
	}
	/**
	 * 获取二级市场货币列表
	 * @return 
	 * */
	public static boolean list() {
		String sql="select id,name from ico_Analysis;";
		Connection con=null;
		String driver = "com.mysql.jdbc.Driver";
        //URL鎸囧悜瑕佽闂殑鏁版嵁搴撳悕mydata
        String sqlurl = "jdbc:mysql://rm-bp1kq68i5h4493twvzo.mysql.rds.aliyuncs.com:3306/app_tokenworm";
        //MySQL閰嶇疆鏃剁殑鐢ㄦ埛鍚�
        String user = "lybjx";
        //MySQL閰嶇疆鏃剁殑瀵嗙爜
        String password = "Lybjx54709488dh";
        ResultSet rs=null;
        try {
			con=DriverManager.getConnection(sqlurl, user, password);
			Statement stat=con.createStatement();
			rs=stat.executeQuery(sql);
			ArrayList<Request> list=new ArrayList<Request>();
            while(rs.next()){//閬嶅巻缁撴灉闆�
            	String targeturl=rs.getString("name").toLowerCase().replace(" ", "-").trim();
            	if(targeturl.contains("binance-coin")) {
            		targeturl="binancecoin";
            	}
            	if(targeturl.contains("byteball-bytes")) {
            		targeturl="byteball";
            	}
            	if(targeturl.contains("gxshares")) {
            		targeturl="gxchain";
            	}
            	if(targeturl.contains("raiden-network-token")) {
            		targeturl="raiden-network";
            	}
            	if(targeturl.contains("po.et")) {
            		targeturl="poet";
            	}
            	if(targeturl.contains("experience-points")){
            		targeturl="XP";
            	}
            	if(targeturl.contains("oyster")) {
            		targeturl="oyster-pearl";
            	}
            	if(targeturl.contains("u.cash")) {
            		targeturl="ucash";
            	}
            	if(targeturl.contains("coindash")) {
            		continue;
            	}
            	
            	if(targeturl.contains("pepe-cash")) {
            		targeturl="pepecash";
            	}
            	if(targeturl.contains("bitcny")) {
            		targeturl="bitCNY";
            	}
            	if(targeturl.contains("agoras-tokens")) {
            		targeturl="agoras-tauchain";
            	}
            	if(targeturl.contains("int-chain")) {
            		targeturl="internet-node-token";
            	}
            	if(targeturl.contains("i/o-coin")) {
            		targeturl="iocoin";
            	}
            	if(targeturl.contains("hempcoin")) {
            		targeturl="hempcoin-thc";
            	}
            	if(targeturl.contains("pascal-coin")) {
            		targeturl="pascalcoin";
            	}
            	if(targeturl.contains("cofound.it")) {
            		targeturl="cofound-it";
            	}
            	if(targeturl.contains("voxels")) {
            		targeturl="revolutionvr";
            	}
            	if(targeturl.contains("firstblood")) {
            		targeturl="first-blood";
            	}
            	if(targeturl.contains("gridcoin")) {
            		targeturl="gridcoin-research";
            	}
            	if(targeturl.contains("ormeus-coin")) {
            		targeturl="ormeuscoin";
            	}
            	if(targeturl.contains("kickcoin")) {
            		targeturl="kickico";
            	}
            	if(targeturl.contains("ixledger")) {
            		targeturl="insurex";
            	}
            	if(targeturl.contains("myriad")) {
            		targeturl="myriadcoin";
            	}
            	
            	if(targeturl.contains("oax")) {
            		targeturl="openanx";
            	}
            	if(targeturl.contains("blackmoon")) {
            		targeturl="blackmoon-crypto";
            	}
            	if(targeturl.contains("FlorinCoin")) {
            		targeturl="flo";
            	}
            	if(targeturl.contains("nvo")) {
            		continue;
            	}
            	if(targeturl.contains("dubaicoin")) {
            		targeturl="dubaicoin-dbix";
            	}
            	targeturl="https://api.coingecko.com/api/v3/coins/"+targeturl+"?id="+rs.getInt("id");
            	
            	Request request = new Request(targeturl);
            	list.add(request);   
            }
            Request[] strings = new Request[list.size()];
            list.toArray(strings);
           
            
            Spider.create(new GetFacebookTwitterdata()).addRequest(strings).thread(10).run();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return true;
		
	}
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}
	

}
