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

public class getgithubinfo implements PageProcessor{
	private Site site = new Site().setRetryTimes(3).setSleepTime(100).setDomain("www.icorating.com");
	public static void main(String[] args) {
		
		
	}
	public static void list() {
		String sql="select id, Github_url from ico_Analysis where Github_url <> '' and GithubCommits=0 and Github_url <>'https://github.com/' limit $start,$limit";
		String[]access_tokenlist= {"768d6cb5b16bc0c36b8e1e1541ea40d49871b525","9efccc763ea27c04585dd46711f62c5f45514035","7e5912faf6b29a5bd5ebd110e94760564c1998ec","494c04594fcf20b35e10f3f7346c40ff66dc6ddf","f0e0592524d4f6b20dd52423599a77c4ea6348eb","e27a40ee39b2b37d5d2b310048c26313473eb296","9958e1f4c805d24f330f23e06689fc869258444","939096382d431ebf5823b827a95f26e7a38b3a85","3136dad03299940abee5612c83cccf9897f05078","c82798d338965fbe78a9566888f2506467d2c04e","76be69c01eb9d3a882ec590ced8131797ecad191","954cbd68a872fedf20da7e08a536847947d3f8c0","5d26baeca9c681c7994679167f98fc169fca0b80","4b1d2407a52e7647a962e9a43661be79d84dc194","5e8d5cf0127c10d7939ff29f01229220c1ebdd7f","c87d0f17b5147cc5e907804f54c0384d909a3679","325dbe244e0939bbf09f0747a10d3c917fe7e529","811f4f94982dd895580022f5dcbaa4698d330205","1e16751e8dddd142f2ec058e9822522b796a2b30","4b956608b6a8e715450d250d0f11cfea162c4d37","6edcd5c07599ac5223ff063fc46ba4d092e47989","8ac1cc4acb41abb8e3b4b7bf2b7402ef9bd29363","70961f148c9570aec960b36b4153384c0cb55022","0295f21e4a7adc397ffec1fd1dbf9e35477a40b8","7a89cb7c701ad19720fda0defb6b17ae24379fec"};

		Mysql ms=new Mysql();
		Connection con;
		int res1=-2;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://13.114.134.239:3306/app_tokenworm";
        //MySQL配置时的用户名
        String user = "lybjx";
        //MySQL配置时的密码
        String password = "123456";
        ResultSet rs=null;
        //遍历查询结果集
        try {
        	
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
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
                    
            	Spider.create(new getgithubinfo()).addUrl(rs.getString("Github_url")).run();
    		}
            Request[] strings = new Request[list.size()];
            list.toArray(strings);
           
            
            Spider.create(new getfacebookfans()).addRequest(strings).thread(4).run();
            con.close();
            
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //数据库连接失败异常处理
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
		Spider.create(new getgithubinfo()).addUrl("https://icodrops.com/category/ended-ico/").run();
		
	}
	public static void upcoming() {
		Spider.create(new getgithubinfo()).addUrl("https://icodrops.com/category/upcoming-ico/").run();
		
	}

	@Override
	public void process(Page page) {
		Request request=page.getRequest();
		String url=request.getUrl();
		String[]access_tokenlist= {"768d6cb5b16bc0c36b8e1e1541ea40d49871b525","9efccc763ea27c04585dd46711f62c5f45514035","7e5912faf6b29a5bd5ebd110e94760564c1998ec","494c04594fcf20b35e10f3f7346c40ff66dc6ddf","f0e0592524d4f6b20dd52423599a77c4ea6348eb","e27a40ee39b2b37d5d2b310048c26313473eb296","9958e1f4c805d24f330f23e06689fc869258444","939096382d431ebf5823b827a95f26e7a38b3a85","3136dad03299940abee5612c83cccf9897f05078","c82798d338965fbe78a9566888f2506467d2c04e","76be69c01eb9d3a882ec590ced8131797ecad191","954cbd68a872fedf20da7e08a536847947d3f8c0","5d26baeca9c681c7994679167f98fc169fca0b80","4b1d2407a52e7647a962e9a43661be79d84dc194","5e8d5cf0127c10d7939ff29f01229220c1ebdd7f","c87d0f17b5147cc5e907804f54c0384d909a3679","325dbe244e0939bbf09f0747a10d3c917fe7e529","811f4f94982dd895580022f5dcbaa4698d330205","1e16751e8dddd142f2ec058e9822522b796a2b30","4b956608b6a8e715450d250d0f11cfea162c4d37","6edcd5c07599ac5223ff063fc46ba4d092e47989","8ac1cc4acb41abb8e3b4b7bf2b7402ef9bd29363","70961f148c9570aec960b36b4153384c0cb55022","0295f21e4a7adc397ffec1fd1dbf9e35477a40b8","7a89cb7c701ad19720fda0defb6b17ae24379fec"};
		int round=new Random().nextInt(25);  
    	String token=access_tokenlist[round];
		String pro=url.replace("https://api.github.com/users/", "").replace("/repos", "");
		String sql="update ico_Analysis set GithubWatches=0,GithubStars=0 where Github_url like '%/"+pro+"%'";
        Mysql ms=new Mysql();
        ms.Excutesql(sql, "update");
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
	        
	        //计算forks等数据
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
	        sql="update ico_Analysis set GithubWatches="+watchers+",GithubStars="+stars+",lastupdatetime='"+lastupdatetime+"' where Github_url like '%/"+pro+"%'";
	        List<String> ForksDatalist=ForksDataPath.selectList(text);
	        int forks =0;
	        
	        
	        for(String dta:ForksDatalist) {
	        	forks+=Integer.parseInt(dta);
	        }
	         sql="update ico_Analysis set GithubCommits=0 where Github_url like '%/"+pro+"%'";
	        
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
		return Site.me().setSleepTime(2000);
	}
	public Date Bijiao(Date date1,Date date2) {
		if(date1.before(date2)) {
			return date2;
		}else{
			return date1;
		}
		
		
	}
}
