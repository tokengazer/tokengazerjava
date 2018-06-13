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

public class getgithubcommits implements PageProcessor{
	private Site site = new Site().setRetryTimes(3).setSleepTime(100).setDomain("www.icorating.com");
	public static void main(String[] args) {
		list();
		
	}
	public static void list() {
		String sql="select id, Github_url from ico_Analysis where Github_url <> '' and GithubCommits=0 and Github_url <>'https://github.com/'";
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
            	int round=new Random().nextInt(25);  
            	String token=access_tokenlist[round];
            	System.out.println(token);
            	String Github_url=rs.getString("Github_url").replace(" ", "").replace("http://github.com/", "https://github.com/");
            	String baseurl=Github_url.replace("https://github.com/", "");//str_replace(",","",$list[$k]['Github_url']);
            	if(baseurl.substring(baseurl.length()-1,baseurl.length())==",") {
            		baseurl=baseurl.substring(0,baseurl.length() - 1);
            	}if(baseurl.substring(baseurl.length()-1,baseurl.length())=="/") {
            		baseurl=baseurl.substring(0,baseurl.length() - 1);
            	}
            	baseurl=baseurl.replace(",", "");
            	baseurl = "https://api.github.com/users/"+baseurl+"/repos";
            	Request request = new Request(baseurl);
            	request.addHeader("Authorization","token "+ token).addHeader("Accept", "application/vnd.github.hellcat-preview+json").addHeader("User-Agent", "Awesome-Octocat-App");
            	list.add(request);   
            }
            Request[] strings = new Request[list.size()];
            list.toArray(strings);
           
            
            Spider.create(new getgithubinfo()).addRequest(strings).thread(3).run();
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
		Spider.create(new getgithubcommits()).addUrl("https://icodrops.com/category/ended-ico/").run();
		
	}
	public static void upcoming() {
		Spider.create(new getgithubcommits()).addUrl("https://icodrops.com/category/upcoming-ico/").run();
		
	}

	@Override
	public void process(Page page) {
		Request request=page.getRequest();
		String url=request.getUrl();
		String[]access_tokenlist= {"768d6cb5b16bc0c36b8e1e1541ea40d49871b525","9efccc763ea27c04585dd46711f62c5f45514035","7e5912faf6b29a5bd5ebd110e94760564c1998ec","494c04594fcf20b35e10f3f7346c40ff66dc6ddf","f0e0592524d4f6b20dd52423599a77c4ea6348eb","e27a40ee39b2b37d5d2b310048c26313473eb296","9958e1f4c805d24f330f23e06689fc869258444","939096382d431ebf5823b827a95f26e7a38b3a85","3136dad03299940abee5612c83cccf9897f05078","c82798d338965fbe78a9566888f2506467d2c04e","76be69c01eb9d3a882ec590ced8131797ecad191","954cbd68a872fedf20da7e08a536847947d3f8c0","5d26baeca9c681c7994679167f98fc169fca0b80","4b1d2407a52e7647a962e9a43661be79d84dc194","5e8d5cf0127c10d7939ff29f01229220c1ebdd7f","c87d0f17b5147cc5e907804f54c0384d909a3679","325dbe244e0939bbf09f0747a10d3c917fe7e529","811f4f94982dd895580022f5dcbaa4698d330205","1e16751e8dddd142f2ec058e9822522b796a2b30","4b956608b6a8e715450d250d0f11cfea162c4d37","6edcd5c07599ac5223ff063fc46ba4d092e47989","8ac1cc4acb41abb8e3b4b7bf2b7402ef9bd29363","70961f148c9570aec960b36b4153384c0cb55022","0295f21e4a7adc397ffec1fd1dbf9e35477a40b8","7a89cb7c701ad19720fda0defb6b17ae24379fec"};

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
	        String sql="update ico_analysis set GithubCommits=GithubCommits+"+count+" where Github_url like '%"+realpro+"';";
	        Mysql ms=new Mysql();
	        ms.Excutesql(sql, "update");
		};
		
		
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me().setSleepTime(2000);
	}
	
}
