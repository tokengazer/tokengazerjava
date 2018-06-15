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
public  class Feixiaohaodetail implements PageProcessor{
	public static void main(String[] args) throws SQLException {
		Spider.create(new Feixiaohaodetail()).addUrl("https://www.feixiaohao.com/currencies/populous/").run();
		
		
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
        String zongfaxingliang="0";
		if(!html.xpath("title").match()) {
			String url1=html.xpath("script").toString().split("\"")[1].split("\"")[0];
			System.out.println(url1);
			Spider.create(new Feixiaohaodetail()).addUrl(url1);
		}else {
			List<String> Lilist=html.xpath("//li").all();
			for(String str:Lilist) {
				System.out.println(str);
				if(str.contains("中文名")) {
					String ZHName=str.toString().split("<span class=\"value\">")[1].split("</span>")[0];
				}else if(str.contains("上架交易所")) {
					String Alreadyon=str.toString().split("<span class=\"value\">")[1].split("</span>")[0].replace("家", "");
				}else if(str.contains("白皮书")) {
					String Whitepaper=str.toString().split("<span class=\"value\">")[1].split("</span>")[0].split("\"")[1].split("\"")[0];
				}
			}
			Selectable iCOtable=html.xpath("//table[@class='iCOtable']");
			List<String> thlist=iCOtable.xpath("//th").all();
			List<String> tdlist=iCOtable.xpath("//td").all();
			int i=0;
			int j=0;
			List<Selectable> nodeslist=html.xpath("//div[@class='cell']").nodes();
			for(Selectable cells:nodeslist) {
				List<String> titlist=cells.xpath("//div[@class='tit']").all();
				List<String> valuelist=cells.xpath("//div[@class='value']").all();
				int k=0;
				for(String tit:titlist) {
					if(tit.contains("总发行量")) {
						zongfaxingliang=valuelist.get(k).toString().split(" ")[0].toString().replaceAll(",", "").replaceAll("<div class=\"value\">", "").replace("<div", "").replaceAll("\"","");
					}
				}
				k++;
			}
			System.out.println(nodeslist);
			for(String str:thlist) {
				if(str.contains("ICO总量")) {
					String ico_total_amount=tdlist.get(i).toString().replace("<td>", "").replace("</td>", "");
					System.out.println(ico_total_amount);
					DecimalFormat df=new DecimalFormat("0.00");
					int a = Integer.parseInt(ico_total_amount);
					System.out.println(zongfaxingliang);
					int b = Integer.parseInt(zongfaxingliang);
					String presale=df.format((float)a/b) ;
				}
				i++;
			}
			
			
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
            	Request request = new Request("hhttps://www.feixiaohao.com/currencies/"+rs.getString("ticker")+"/");
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
