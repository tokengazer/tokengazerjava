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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public  class Feixiaohaodescription implements PageProcessor{
	public static void main(String[] args) throws SQLException {
		//Spider.create(new Feixiaohaodescription()).addUrl("https://www.feixiaohao.com/coindetails/populous/").run();
		
		
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
        //椹卞姩绋嬪簭鍚�
        String driver = "com.mysql.jdbc.Driver";
        //URL鎸囧悜瑕佽闂殑鏁版嵁搴撳悕mydata
        String url = "jdbc:mysql://13.114.134.239:3306/app_tokenworm";
        //MySQL閰嶇疆鏃剁殑鐢ㄦ埛鍚�
        String user = "lybjx";
        //MySQL閰嶇疆鏃剁殑瀵嗙爜
        String password = "123456";
        String insertsql="";
        String description="";
        Project pro=new Project();
		if(!html.xpath("title").match()) {
			String url1=html.xpath("script").toString().split("\"")[1].split("\"")[0];
			System.out.println(url1);
			Spider.create(new Feixiaohaodescription()).addUrl(url1);
		}else {
			String Html=html.xpath("//div[@class='artBox']").xpath("//p").all().get(1).replace("<p>", "").replace("</p>", "").toString();
			System.out.println(Html);
			new Project().setDescription(Html);
		}
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
        //椹卞姩绋嬪簭鍚�
        String driver = "com.mysql.jdbc.Driver";
        //URL鎸囧悜瑕佽闂殑鏁版嵁搴撳悕mydata
        String url = "jdbc:mysql://13.114.134.239:3306/app_tokenworm";
        //MySQL閰嶇疆鏃剁殑鐢ㄦ埛鍚�
        String user = "lybjx";
        //MySQL閰嶇疆鏃剁殑瀵嗙爜
        String password = "123456";
        //閬嶅巻鏌ヨ缁撴灉闆�
        try {
        	Class.forName(driver);
            //1.getConnection()鏂规硶锛岃繛鎺ySQL鏁版嵁搴擄紒锛�
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.鍒涘缓statement绫诲璞★紝鐢ㄦ潵鎵цSQL璇彞锛侊紒
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery( sql );// sql涓哄緟鎵ц鐨剆ql
            ArrayList<Request> list=new ArrayList<Request>();
            while(rs.next()){//閬嶅巻缁撴灉闆�
            	Request request = new Request("https://www.feixiaohao.com/currencies/"+rs.getString("ticker")+"/");
            	list.add(request);   
            }
            Request[] strings = new Request[list.size()];
            list.toArray(strings);
           
            
            Spider.create(new gettelegramfans()).addRequest(strings).thread(100).run();
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
            ResultSet rs=null;
        }finally{
            System.out.println("鏁版嵁搴撴暟鎹垚鍔熻幏鍙栵紒锛�");
            
        }
        
		

		
	}
	

	
}
