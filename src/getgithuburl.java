/*
 * list()中去掉了ids的限制
 * 
 * **/
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
import java.util.Random;

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

public class getgithuburl implements PageProcessor{
	private Site site = new Site().setRetryTimes(3).setSleepTime(100).setDomain("www.icorating.com");
	public static void main(String[] args) {
		list();
		
	}
	public static void list() {
		//String ids="1175,1176,1177,1178,1179,1180,1181,1183,1186,1190,1191,1192,5760,5761,5762,5763,5764,5765,5766,5770,5771,8115,8117,8152,8153,8154,16288,16289,16328,16329,16330,16478,16479,16480,16538,16564,16571,16579,16587,16590,16591,16592,16593,16594,16595,16596,16597,16598,16599,16600,16601,16602,16603,16604,16605,16606,16607,16608,16609,16610,16611,16612,16613,16614,16615,16616,16617,16618,16619,16620,16621,16622,16623,16624,16625,16626,16627,16628,16629,16630,16631,16632,16633,16634,16635,16636,16637,16638,16639,16640,16641,16642,16643,16644,16645,16646,16647,16648,16649,16650,16651,16652,16653,16654,16655,16656,16657,16658,16659,16660,16661,16662,16663,16664,16665,16666,16667,16668,16669,16670,16671,16672,16673,16674,16675,16676,16677,16678,16679,16680,16681,16682,16683,16684,16685,16686,16687,16688,16689,16690,16691,16692,16693,16694,16695,16696,16697,16698,16699,16700,16701,16702,16703,16704,16705,16706,16707,16708,16709,16710,16711,16712,16713,16714,16715,16716,16717,16718,16719,16720,16721,16722,16723,16724,16725,16726,16727,16728,16729,16730,16731,16732,16733,16734,16735,16736,16737,16738,16739,16740,16741,16742,16743,16744,16745,16746,16747,16750,16755,16784,16819,16821,16825,16827,16841,16853,16865,16882,16964,16979,17313,17327,17375,17398,17459,17461,17559,17592,17643,17648,17657,17785,17818,17828,17830,17846,17878,17929,18012,18036,18102,18143,18153,18256,18298,18309,18314,18344,18350,18358,18456,18477,18515,18570,18588,18604,18610,18623,18636,5773,18451,18656,18854,18898,18950,18469";
//ids="15";
		String sql="select id, Github_url from ico_Analysis where Github_url <> '' and Github_url <>'https://github.com/';";
		//String[]access_tokenlist= {"afc61616719a89cbdbed28cc32e93c9d9a93e6bf","87683e1fa2f3e8ccff66d61193b948a858135ea6","237919e8c6d885d559e4eca11de1f17877e90ea7","1d645462cc4205d099a6a09a03f1a35f9a40aacd","dca768ed31563a2de80e61b5ce224972c94d967a","d3c8d7c2c86d4687acbdc3deea96c5e63a87e280","fcedcd7967bd57fb1694866e26eb3de915e338a7","807ac8745da0c11b8d4faec02a3d6ef47612b8d7","643b845668a201695235fa2b8440a22620ca0869","deea6e0052c10297e3099868cca476f0d2b00231","2a2ed47aeef3e2754246eb89fb9d1d6a4dbba897","df196a1db556073db536b7d5142b1afd0d39fa0d","439849ac657d929dc79f3f9b5942a97d1dc6d46f","778d9a79c04f4755b279332859c4db244b10c97e","c2fbde240f1032c0cc361137cedbebee815d8e88","0976a41854430be48f2ac8267b6b6db400d43306","921b21fde0d1c635488f3e73ab608dc10cdf7669","7e74b3905cd24fc19e4ca06376cf12173333bbdb","923b5ed4340fca2e11bc10e1c53f18a8f339fc4e","225867cc8489a5b289daec388f2efef38a26c592","fdef8ec4e59f61238adb260b9e823dd03675ad2a","216063a05f488bf109411cf725421019fe773513","1633b743ccf24bdfcbe21c73e3299ae538fba556","03381daf9a4ce96a9ddfd4529680eedf6a166b03","2cb8bde0bdaeb0a5077e404f66f824159a9ba361"};
		String[]access_tokenlist= {};
		String[] tokenlist=GithubAccessTokenList.getTokenlist();
		int i=0;
		access_tokenlist=tokenlist;
		Mysql ms=new Mysql();
		Connection con;
		int res1=-2;
        String driver = "com.mysql.jdbc.Driver";
        
        String url = "jdbc:mysql://rm-bp1kq68i5h4493twvzo.mysql.rds.aliyuncs.com:3306/app_tokenworm";
        
        String user = "lybjx";
        
        String password = "Lybjx54709488dh";
        ResultSet rs=null;
        
        try {
        	
             Class.forName(driver);
             con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed()) {}
                //System.out.println("Succeeded connecting to the Database!");
            
            Statement statement = con.createStatement();
            rs = statement.executeQuery( sql );
            ArrayList<Request> list=new ArrayList<Request>();
            while(rs.next()){
            	int round=new Random().nextInt(25);  
            	String token=access_tokenlist[round];
            	//System.out.println(token);
            	String Github_url=rs.getString("Github_url").replace(" ", "").replace("http://github.com/", "https://github.com/");
            	String baseurl=Github_url.replace("https://github.com/", "");//str_replace(",","",$list[$k]['Github_url']);
            	if(baseurl.substring(baseurl.length()-1,baseurl.length())==",") {
            		baseurl=baseurl.substring(0,baseurl.length() - 1);
            	}if(baseurl.substring(baseurl.length()-1,baseurl.length())=="/") {
            		baseurl=baseurl.substring(0,baseurl.length() - 1);
            	}
            	baseurl=baseurl.split("/")[0].replace(",", "");
            	baseurl = "https://api.github.com/users/"+baseurl+"/repos";
            	Request request = new Request(baseurl.replace("//", "/").replace(":/", "://"));
            	request.addHeader("Authorization","token "+ token).addHeader("Accept", "application/vnd.github.hellcat-preview+json").addHeader("User-Agent", "Awesome-Octocat-App");
            	list.add(request);   
            }
            Request[] strings = new Request[list.size()];
            list.toArray(strings);
           
            
            Spider.create(new getgithubinfo()).addRequest(strings).thread(5).run();
            con.close();
            
        } catch(ClassNotFoundException e) {   
            
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            rs=null;
        }finally{
            //System.out.println("连接数据库成功");
            
        }
        
		

		
	}
	public static void ended() {
		Spider.create(new getgithuburl()).addUrl("https://icodrops.com/category/ended-ico/").run();
		
	}
	public static void upcoming() {
		Spider.create(new getgithuburl()).addUrl("https://icodrops.com/category/upcoming-ico/").run();
		
	}

	@Override
	public void process(Page page) {
		
		
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me().setSleepTime(2000);
	}
	
}
