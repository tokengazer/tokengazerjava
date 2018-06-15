import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
import java.io.*;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public  class Feixiaohao implements PageProcessor{
	public static void main(String[] args) throws SQLException {
		//Spider.create(new Feixiaohao()).addUrl("https://coinmarketcap.com/zh/all/views/all/").run();
		list();
		
	}
	@SuppressWarnings("deprecation")
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		Selectable selectable=html;
		Selectable tablelist=selectable.xpath("//tbody");
		Mysql mysql=new Mysql();
		
		Connection con = null;
		int res1=-2;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://13.114.134.239:3306/app_tokenworm";
        //MySQL配置时的用户名
        String user = "lybjx";
        //MySQL配置时的密码
        String password = "123456";
        String insertsql="";
		for(Selectable li:tablelist.xpath("//tr").nodes()) {
			int i=0;
			String name="";String ticker="";String volumn="";String Circulating_Supply="";String perhour="";String perday="";String perweek="";String price="";
			for(Selectable td:li.xpath("//td").nodes())
			{
				if(i==1) {
					name=td.xpath("//a").nodes().get(1).xpath("//a/text()").toString();
					//System.out.println(name);
				}if(i==2) {
					ticker=td.xpath("//td/text()").toString();
					//System.out.println(ticker);
				}if(i==3) {
					volumn=td.xpath("//td/text()").toString();
					//System.out.println(volumn);
				}if(i==4) {
					price=td.xpath("//a/text()").toString();
					//System.out.println(price);
				}
				if(i==5) {
					Circulating_Supply = td.xpath("//a/text()").toString();
					//System.out.println(Circulating_Supply);
				}if(i==6) {
					perhour=td.xpath("//a/text()").toString();
					//System.out.println(perhour);
				}
				if(i==7) {
					perday=td.xpath("//td/text()").toString();
					//System.out.println(perday);
				}if(i==8) {
					perweek=td.xpath("//td/text()").toString();
					//System.out.println(perweek);
				}
				
				i++;
			}
			Calendar now = Calendar.getInstance(); 
			Date date=new Date();
			String time1=now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH) + 1)+"-"+now.get(Calendar.DAY_OF_MONTH);
			//String sql="select * from coinmarketdata where Name='"+name+"'and Updatedate='"+time1+"';";
			ResultSet rs=null;
	        //遍历查询结果集
	        //try {
	        	
	            //加载驱动程序
	            try {
					Class.forName(driver);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            //1.getConnection()方法，连接MySQL数据库！！
	            //con = DriverManager.getConnection(url,user,password);
	            //2.创建statement类对象，用来执行SQL语句！！
	            
	            //Statement statement = con.createStatement();
	            int rowcount =0;

	            //ResultSet re = statement.executeQuery(sql);
	            //re.last();      //直接执行跳到结果集的最后一行

	            rowcount =0;//= re.getRow();   //这一句就能得到结果集的行数
//	            re.beforeFirst(); 
	            
	            if(rowcount==0) {
	            	insertsql+="insert into coinmarketdata values(null,'"+name+"','"+ticker+"','"+volumn+"','"+price+"','"+Circulating_Supply+"','"+perhour+"','"+perday+"','"+perweek+"','"+time1+"');";
	            	
	            }else {
	            	System.out.println(1);
	            }
	       // }
		//catch(SQLException e1) {
			//System.out.println(e1.getErrorCode());
		//}
			
		}
		System.out.println(insertsql);
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //1.getConnection()方法，连接MySQL数据库！！
        try {
			con = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Statement statement = null;
		try {
			statement = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			System.out.println(statement.executeUpdate(insertsql));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(1);
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}
	public static void list() {
		String sql="select * from ico_Analysis";
		Connection con=null;
		int res1=-2;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://13.114.134.239:3306/app_tokenworm";
        //MySQL配置时的用户名
        String user = "lybjx";
        //MySQL配置时的密码
        String password = "123456";
        //遍历查询结果集
        try {
        	Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery( sql );// sql为待执行的sql
            ArrayList<Request> list=new ArrayList<Request>();
            while(rs.next()){//遍历结果集
            	Request request = new Request("hhttps://www.feixiaohao.com/currencies/"+rs.getString("ticker")+"/");
            	list.add(request);   
            }
            Request[] strings = new Request[list.size()];
            list.toArray(strings);
           
            
            Spider.create(new gettelegramfans()).addRequest(strings).thread(100).run();
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
            ResultSet rs=null;
        }finally{
            System.out.println("数据库数据成功获取！！");
            
        }
        
		

		
	}
	

	
}
