import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.JsonPathSelector;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class getgithubcommits extends GithubAccessTokenList implements PageProcessor{
	private Site site = new Site().setRetryTimes(3).setSleepTime(100).setDomain("www.icorating.com");
	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.trustStore", "C:\\Program Files\\Java\\jre1.8.0_171\\lib\\security\\cacerts");
		//System.out.print( System.getProperty("javax.net.ssl.trustStore"));
		//list();
		
	}
	public static void list() {
		String sql="select id, Github_url from ico_Analysis where Github_url <> '' and Github_url <>'https://github.com/'";
		//String[]access_tokenlist= {"afc61616719a89cbdbed28cc32e93c9d9a93e6bf","87683e1fa2f3e8ccff66d61193b948a858135ea6","237919e8c6d885d559e4eca11de1f17877e90ea7","1d645462cc4205d099a6a09a03f1a35f9a40aacd","dca768ed31563a2de80e61b5ce224972c94d967a","d3c8d7c2c86d4687acbdc3deea96c5e63a87e280","fcedcd7967bd57fb1694866e26eb3de915e338a7","807ac8745da0c11b8d4faec02a3d6ef47612b8d7","643b845668a201695235fa2b8440a22620ca0869","deea6e0052c10297e3099868cca476f0d2b00231","2a2ed47aeef3e2754246eb89fb9d1d6a4dbba897","df196a1db556073db536b7d5142b1afd0d39fa0d","439849ac657d929dc79f3f9b5942a97d1dc6d46f","778d9a79c04f4755b279332859c4db244b10c97e","c2fbde240f1032c0cc361137cedbebee815d8e88","0976a41854430be48f2ac8267b6b6db400d43306","921b21fde0d1c635488f3e73ab608dc10cdf7669","7e74b3905cd24fc19e4ca06376cf12173333bbdb","923b5ed4340fca2e11bc10e1c53f18a8f339fc4e","225867cc8489a5b289daec388f2efef38a26c592","fdef8ec4e59f61238adb260b9e823dd03675ad2a","216063a05f488bf109411cf725421019fe773513","1633b743ccf24bdfcbe21c73e3299ae538fba556","03381daf9a4ce96a9ddfd4529680eedf6a166b03","2cb8bde0bdaeb0a5077e404f66f824159a9ba361"};
		String[]access_tokenlist= {};
		try {
			ArrayList<String> tokenlist=GithubAccessTokenList.getTokenlist();
			int i=0;
			for(String token:tokenlist) {
				access_tokenlist[i]=token;
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Mysql ms=new Mysql();
		Connection con;
		int res1=-2;
		//驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://rm-bp1kq68i5h4493twvzo.mysql.rds.aliyuncs.com:3306/app_tokenworm";
        //MySQL配置时的用户名
        String user = "lybjx";
        //MySQL配置时的密码
        String password = "Lybjx54709488dh";
        ResultSet rs=null;
        //遍历查询结果集
        try {
        	
        	 //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed()) {}
                //System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            
            Statement statement = con.createStatement();
            rs = statement.executeQuery( sql );// sql为待执行的sql
            ArrayList<Request> list=new ArrayList<Request>();
            while(rs.next()){//遍历结果集
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
            	baseurl=baseurl.replace(",", "");
            	baseurl = "https://api.github.com/users/"+baseurl+"/repos";
            	Request request = new Request(baseurl.replace("//repos", "/repos"));
            	request.addHeader("Authorization","token "+ token).addHeader("Accept", "application/vnd.github.hellcat-preview+json").addHeader("User-Agent", "Awesome-Octocat-App");
            	list.add(request);   
            }
            Request[] strings = new Request[list.size()];
            list.toArray(strings);
           
            
           // Spider.create(new getgithubinfo()).addRequest(strings).thread(5).run();
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
            System.out.println("数据库数据成功获取！！");
            
        }
        
		

		
	}
	public static void ended() {
		Spider.create(new getgithubcommits()).addUrl("https://icodrops.com/category/ended-ico/").run();
		
	}
	public static void upcoming() {
		Spider.create(new getgithubcommits()).addUrl("https://icodrops.com/category/upcoming-ico/").run();
		
	}

	@Override
	public void process(Page page) {
		Request request=page.getRequest();
		String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        //MySQL配置时的用户名
        String user = "lybjx";
        //MySQL配置时的密码
        String password = "Lybjx54709488dh";
		String url=request.getUrl();
		String linkurl="jdbc:mysql://rm-bp1kq68i5h4493twvzo.mysql.rds.aliyuncs.com:3306/app_tokenworm";
		Connection con = null;
		String[]access_tokenlist= {"afc61616719a89cbdbed28cc32e93c9d9a93e6bf","87683e1fa2f3e8ccff66d61193b948a858135ea6","237919e8c6d885d559e4eca11de1f17877e90ea7","1d645462cc4205d099a6a09a03f1a35f9a40aacd","dca768ed31563a2de80e61b5ce224972c94d967a","d3c8d7c2c86d4687acbdc3deea96c5e63a87e280","fcedcd7967bd57fb1694866e26eb3de915e338a7","807ac8745da0c11b8d4faec02a3d6ef47612b8d7","643b845668a201695235fa2b8440a22620ca0869","deea6e0052c10297e3099868cca476f0d2b00231","2a2ed47aeef3e2754246eb89fb9d1d6a4dbba897","df196a1db556073db536b7d5142b1afd0d39fa0d","439849ac657d929dc79f3f9b5942a97d1dc6d46f","778d9a79c04f4755b279332859c4db244b10c97e","c2fbde240f1032c0cc361137cedbebee815d8e88","0976a41854430be48f2ac8267b6b6db400d43306","921b21fde0d1c635488f3e73ab608dc10cdf7669","7e74b3905cd24fc19e4ca06376cf12173333bbdb","923b5ed4340fca2e11bc10e1c53f18a8f339fc4e","225867cc8489a5b289daec388f2efef38a26c592","fdef8ec4e59f61238adb260b9e823dd03675ad2a","216063a05f488bf109411cf725421019fe773513","1633b743ccf24bdfcbe21c73e3299ae538fba556","03381daf9a4ce96a9ddfd4529680eedf6a166b03","2cb8bde0bdaeb0a5077e404f66f824159a9ba361"};

		int round=new Random().nextInt(25);  
    	String token=access_tokenlist[round];
		String pro=url.replace("https://api.gethub.com/users/", "").replace("/repos", "");
		int code=page.getStatusCode();
		Html html = page.getHtml();
		if(page.getStatusCode()==200) {
			String text=html.xpath("//body/text()").toString();
			//System.out.println(text);
			JsonPathSelector jsonPathSelector = new JsonPathSelector("$.data.repository.refs.edges[*]");
			JsonPathSelector totalCountSelector = new JsonPathSelector("$.data.repository.refs.edges[*].node.target.history.totalCount");
			JsonPathSelector resourcepath=new JsonPathSelector("$.data.repository.resourcePath");
			JsonPathSelector namepath=new JsonPathSelector("$.data.repository.name");
			String name=namepath.select(text);
			String pro1=resourcepath.select(text);
			String realpro=pro1.split("/"+name)[0];
			JsonPathSelector namelistpath = new JsonPathSelector("$.data.repository.refs.edges[*].node.name");
			List<String> namelist=namelistpath.selectList(text);
			List<String> totalcountlist=totalCountSelector.selectList(text);
	        String select = jsonPathSelector.select(text);
	        List<String> list = jsonPathSelector.selectList(text);
	        int i=0;int c=0;
	        int count=0;
	        for(String li:namelist) {
	        	if(li=="master") {
	        		c=i;
	        	}
	        	i++;
	        }
	        
	       i=-0;int d=0;
	       for(String total:totalcountlist) {
	        	if(i==c) {
	        	count=Integer.parseInt(total);
	        	}
	        	i++;
	        }
	        System.out.println(list);
	        System.out.println(count);
	        String sql="update ico_analysis set GithubCommits=GithubCommits+"+count+" where Github_url like '%"+realpro+"%' and Github_url<>'';";
	        String sql1="insert into sqllog (id,`sql`) values(NULL,'"+sql.replaceAll("'", "")+"')";
	        Mysql ms=new Mysql();
	        ms.Excutesql(sql, "update");
	        System.out.print(sql);
	        String sqlurl = "jdbc:mysql://rm-bp1kq68i5h4493twvzo.mysql.rds.aliyuncs.com:3306/app_tokenworm";
	        try {
				con = DriverManager.getConnection(sqlurl,user,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
			Statement stat=null;
			stat=con.createStatement();
			//sql="insert into history (name,datetime,openprice,highprice,lowprice,endprice,volumn,marketcap) values ('Enigma','"+datetime+"','"+openprice+"','"+highprice+"','"+lowprice+"','"+endprice+"','"+volumn+"','"+marketcap+"');";
			stat.execute(sql);
			stat.execute(sql1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		};
		
		
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me().setSleepTime(2000);
	}
	
}
