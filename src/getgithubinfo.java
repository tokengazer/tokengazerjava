import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant;

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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import java.util.Map;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class getgithubinfo extends GithubAccessTokenList implements PageProcessor{
	private Site site = new Site().setRetryTimes(3).setSleepTime(100).setDomain("www.icorating.com");
	public static void main(String[] args) {
		
		
	}
	public static void list() {
		String sql="select id, Github_url from ico_Analysis where Github_url <> ''  and Github_url <>'https://github.com/' and id=15";
		String[]access_tokenlist= {"afc61616719a89cbdbed28cc32e93c9d9a93e6bf","87683e1fa2f3e8ccff66d61193b948a858135ea6","237919e8c6d885d559e4eca11de1f17877e90ea7","1d645462cc4205d099a6a09a03f1a35f9a40aacd","dca768ed31563a2de80e61b5ce224972c94d967a","d3c8d7c2c86d4687acbdc3deea96c5e63a87e280","fcedcd7967bd57fb1694866e26eb3de915e338a7","807ac8745da0c11b8d4faec02a3d6ef47612b8d7","643b845668a201695235fa2b8440a22620ca0869","deea6e0052c10297e3099868cca476f0d2b00231","2a2ed47aeef3e2754246eb89fb9d1d6a4dbba897","df196a1db556073db536b7d5142b1afd0d39fa0d","439849ac657d929dc79f3f9b5942a97d1dc6d46f","778d9a79c04f4755b279332859c4db244b10c97e","c2fbde240f1032c0cc361137cedbebee815d8e88","0976a41854430be48f2ac8267b6b6db400d43306","921b21fde0d1c635488f3e73ab608dc10cdf7669","7e74b3905cd24fc19e4ca06376cf12173333bbdb","923b5ed4340fca2e11bc10e1c53f18a8f339fc4e","225867cc8489a5b289daec388f2efef38a26c592","fdef8ec4e59f61238adb260b9e823dd03675ad2a","216063a05f488bf109411cf725421019fe773513","1633b743ccf24bdfcbe21c73e3299ae538fba556","03381daf9a4ce96a9ddfd4529680eedf6a166b03","2cb8bde0bdaeb0a5077e404f66f824159a9ba361"};
		//String[]access_tokenlist= {};
		String[] tokenlist=GithubAccessTokenList.getTokenlist();
		//int i=0;
		access_tokenlist=tokenlist;
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
        	
            //鍔犺浇椹卞姩绋嬪簭
            Class.forName(driver);
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
            	int round=(int)Math.random()*(25-1+1);
            	String token=access_tokenlist[round];
            	
            	String Github_url=rs.getString("Github_url").replace(" ", "").replace("http://github.com/", "https://github.com/");
            	String baseurl=Github_url.replace("https://github.com/", "");//str_replace(",","",$list[$k]['Github_url']);
            	if(baseurl.substring(baseurl.length()-1,baseurl.length())==",") {
            		baseurl=baseurl.substring(0,baseurl.length() - 1);
            	}if(baseurl.substring(baseurl.length()-1,baseurl.length())=="/") {
            		baseurl=baseurl.substring(0,baseurl.length() - 1);
            	}
            	baseurl = "https://api.github.com/users/"+baseurl+"/repos";
            	Request request = new Request(baseurl.replace("//", "/"));
            	request.addHeader("Authorization:token  ", token).addHeader("Accept", "application/vnd.github.hellcat-preview+json").addHeader("User-Agent", "Awesome-Octocat-App");
                    
            	//Spider.create(new getgithubinfo()).addUrl(rs.getString("Github_url")).run();
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
            //System.out.println("数据库数据成功获取！！");
            
        }
        
		

		
	}
	public static void ended() {
		Spider.create(new getgithubinfo()).addUrl("https://icodrops.com/category/ended-ico/").run();
		
	}
	public static void upcoming() {
		Spider.create(new getgithubinfo()).addUrl("https://icodrops.com/category/upcoming-ico/").run();
		
	}

	@Override
	public void process(Page page) {
		Request request=page.getRequest();
		String url=request.getUrl();
		String driver = "com.mysql.jdbc.Driver";
		String sqlurl="jdbc:mysql://rm-bp1kq68i5h4493twvzo.mysql.rds.aliyuncs.com:3306/app_tokenworm";
        //URL指向要访问的数据库名mydata
        //MySQL配置时的用户名
        String user = "lybjx";
        //MySQL配置时的密码
        String password = "Lybjx54709488dh";
		//String url=request.getUrl();
		Connection con = null;
		//String[]access_tokenlist= {"afc61616719a89cbdbed28cc32e93c9d9a93e6bf","87683e1fa2f3e8ccff66d61193b948a858135ea6","237919e8c6d885d559e4eca11de1f17877e90ea7","1d645462cc4205d099a6a09a03f1a35f9a40aacd","dca768ed31563a2de80e61b5ce224972c94d967a","d3c8d7c2c86d4687acbdc3deea96c5e63a87e280","fcedcd7967bd57fb1694866e26eb3de915e338a7","807ac8745da0c11b8d4faec02a3d6ef47612b8d7","643b845668a201695235fa2b8440a22620ca0869","deea6e0052c10297e3099868cca476f0d2b00231","2a2ed47aeef3e2754246eb89fb9d1d6a4dbba897","df196a1db556073db536b7d5142b1afd0d39fa0d","439849ac657d929dc79f3f9b5942a97d1dc6d46f","778d9a79c04f4755b279332859c4db244b10c97e","c2fbde240f1032c0cc361137cedbebee815d8e88","0976a41854430be48f2ac8267b6b6db400d43306","921b21fde0d1c635488f3e73ab608dc10cdf7669","7e74b3905cd24fc19e4ca06376cf12173333bbdb","923b5ed4340fca2e11bc10e1c53f18a8f339fc4e","225867cc8489a5b289daec388f2efef38a26c592","fdef8ec4e59f61238adb260b9e823dd03675ad2a","216063a05f488bf109411cf725421019fe773513","1633b743ccf24bdfcbe21c73e3299ae538fba556","03381daf9a4ce96a9ddfd4529680eedf6a166b03","2cb8bde0bdaeb0a5077e404f66f824159a9ba361"};
		int round=new Random().nextInt(25); 
		String[]access_tokenlist= {};
		String[] tokenlist=GithubAccessTokenList.getTokenlist();
		int i=0;
		access_tokenlist=tokenlist;
    	String token=access_tokenlist[round];
		String pro=url.replace("https://api.github.com/users/", "").replace("/repos", "");
		String sql="update ico_Analysis set GithubWatches=0,GithubStars=0 where Github_url like '%"+pro+"%'";
        Mysql ms=new Mysql();
        try {
			con = DriverManager.getConnection(sqlurl,user,password);
			Statement stat=con.createStatement();
			stat.executeUpdate(sql);
			con.close();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
        
        //ms.Excutesql(sql, "update");
		int code=page.getStatusCode();
		int stars=0;
		int watchers=0;
		if(page.getStatusCode()==200) {
			Html html = page.getHtml();
			String text=html.xpath("//body/text()").toString();
			JsonPathSelector jsonPathSelector = new JsonPathSelector("$.[*].name");
	        String select = jsonPathSelector.select(text);
	        List<String> list = jsonPathSelector.selectList(text);
	        ArrayList<Request> list1=new ArrayList<Request>();
	        
	        //璁＄畻forks绛夋暟鎹�
	        JsonPathSelector ForksDataPath = new JsonPathSelector("$.[*].forks");
	        JsonPathSelector UpdatetimeDataPath = new JsonPathSelector("$.[*].updated_at");
	        List<String> Urllist=new JsonPathSelector("$.[*].url").selectList(text);
	        List<String> Updatetimelist=UpdatetimeDataPath.selectList(text);
	        String lastupdatetime="2000-04-10 00:00:00";
	        Date d=new Date();

	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	        Date bijiaolastupdatetime = null;
			try {
				bijiaolastupdatetime = df.parse("2000-04-10 00:00:00");
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	        for(String updatetime:Updatetimelist) {
	        	Date date2 = null;
				try {
					date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(updatetime.replace("T", " ").replace("Z", ""));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		        bijiaolastupdatetime=Bijiao(bijiaolastupdatetime,date2);
	        }
	        lastupdatetime=bijiaolastupdatetime.toString();
	        for(String url1:Urllist) {
	        	int rand1=(int)Math.random()*(10-1+1);
            	String token1=access_tokenlist[round];
	        	String body = "{}";
	            DefaultHttpClient httpclient = new DefaultHttpClient();
	            try {
	                HttpGet httpget = new HttpGet(url1);
	                httpget.addHeader("Authorization", "token "+token1);
	                httpget.addHeader("Accept","application/vnd.github.hellcat-preview+json");
	                httpget.addHeader("User-Agen","Awesome-Octocat-App");
	                HttpResponse response = null;
					try {
						response = httpclient.execute(httpget);
					} catch (ClientProtocolException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                HttpEntity entity = response.getEntity();
	                try {
						body = EntityUtils.toString(entity);
					} catch (org.apache.http.ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            } finally {
	                httpclient.getConnectionManager().shutdown();
	            }
	            JSONObject jsStr = JSONObject.parseObject(body); 
	            stars+=Integer.parseInt(jsStr.getString("stargazers_count"));
	            watchers+=Integer.parseInt(jsStr.getString("subscribers_count"));
	            
	            System.out.println(body);
	        }
	        System.out.print(lastupdatetime);
	        List<String> ForksDatalist=ForksDataPath.selectList(text);
	        int forks =0;
	        
	        
	        for(String dta:ForksDatalist) {
	        	forks+=Integer.parseInt(dta);
	        }
	        sql="update ico_Analysis set GithubWatches="+watchers+",GithubStars="+stars+",GithubForks="+forks+",Github_lastupdatetime='"+lastupdatetime+"' where Github_url like '%"+pro+"%' and Github_url<>''";
	        System.out.print(sql);
	        try {
				con = DriverManager.getConnection(sqlurl,user,password);
				Statement stat=con.createStatement();
				stat.executeUpdate(sql);
				con.close();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
	        
	         sql="update ico_Analysis set GithubCommits=0 where Github_url like '%"+pro+"%' and Github_url<>''";
	         try {
	 			con = DriverManager.getConnection(sqlurl,user,password);
	 			Statement stat=con.createStatement();
	 			stat.executeUpdate(sql);
	 			con.close();
	 		} catch (SQLException e3) {
	 			// TODO Auto-generated catch block
	 			e3.printStackTrace();
	 		}
	        ms.Excutesql(sql, "update");
	        for(String name:list) {
	        	
	        	String data_string="{\"query\":\"{repository(owner:\\\""+pro+"\\\", name: \\\""+name+"\\\") {\\n    name\\n   resourcePath refs(first: 100, refPrefix: \\\"refs/heads/\\\") {   edges {    node {        name        target {       ... on Commit {           id             history(first: 0) {              totalCount            }         }       }      }    }   }  }}\",\"variables\":{},\"operationName\":null}";
	        	String query= "query\":\"query { repository(owner: \"wso2-extensions\", name:\"identity-inbound-auth-oauth\") { object(expression: \"83253ce50f189db30c54f13afa5d99021e2d7ece\") { ... on Commit { blame(path: \"components/org.wso2.carbon.identity.oauth.endpoint/src/main/java/org/wso2/carbon/identity/oauth/endpoint/authz/OAuth2AuthzEndpoint.java\") { ranges { startingLine endingLine age commit { message url history(first: 2) login { edges { node { message url } } } author { name email } } } } } } } }";

	        	Request request1 = new Request("https://api.github.com/graphql?anon=1000");
	        	Map<String, Object> hm=new HashMap<String,Object>();
	        	hm.put("query",data_string);
	        	String url2 = "https://api.github.com/graphql?anon=1000";
	        	
	        	request1.addHeader("Authorization","token "+ token).addHeader("Accept", "application/vnd.github.hellcat-preview+json").addHeader("User-Agent", "Awesome-Octocat-App");
            	request1.setMethod(HttpConstant.Method.POST);
            	request1.setRequestBody(HttpRequestBody.json(data_string,"UTF-8"));
            	list1.add(request1);
	        }
	        Request[] strings = new Request[list1.size()];
            list1.toArray(strings);
           
            
            Spider.create(new getgithubcommits()).addRequest(strings).thread(4).run();
            
		};
		System.out.println(111);
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me().setSleepTime(1000);
	}
	public Date Bijiao(Date date1,Date date2) {
		if(date1.before(date2)) {
			return date2;
		}else{
			return date1;
		}
		
		
	}
}
