import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
import java.io.*;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public  class Coinmarket implements PageProcessor{
	public static void main(String[] args) {
		Spider.create(new Coinmarket()).addUrl("https://coinmarketcap.com/zh/all/views/all/").run();
		
	}
	@SuppressWarnings("deprecation")
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		Selectable selectable=html;
		Selectable tablelist=selectable.xpath("//tbody");
		Mysql mysql=new Mysql();
		Connection con = null ;
		int res1=-2;
        //椹卞姩绋嬪簭鍚�
		String driver = "com.mysql.jdbc.Driver";

        String sqlurl = "jdbc:mysql://rm-bp1kq68i5h4493twvzo.mysql.rds.aliyuncs.com:3306/app_tokenworm";

        String user = "lybjx";

        String password = "Lybjx54709488dh";
        String insertsql="";
        PreparedStatement pstmt =null;
        try {
			con = DriverManager.getConnection(sqlurl,user,password);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "insert into coinmarketdata values(null,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = (PreparedStatement) con.prepareStatement(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
			int month=now.get(Calendar.MONTH)+1;
			if(month<10) {
				String mon="0"+""+month;
			}
			else {
				String mon=month+"";
			}
			String time1=now.get(Calendar.YEAR)+"-"+month+"-"+now.get(Calendar.DAY_OF_MONTH);
			//String sql="select * from coinmarketdata where Name='"+name+"'and Updatedate='"+time1+"';";
			ResultSet rs=null;
	        //閬嶅巻鏌ヨ缁撴灉闆�
	        //try {
	        	
	            //鍔犺浇椹卞姩绋嬪簭
	            try {
					Class.forName(driver);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            //1.getConnection()鏂规硶锛岃繛鎺ySQL鏁版嵁搴擄紒锛�
	            //con = DriverManager.getConnection(url,user,password);
	            //2.鍒涘缓statement绫诲璞★紝鐢ㄦ潵鎵цSQL璇彞锛侊紒
	            
	            //Statement statement = con.createStatement();
	            int rowcount =0;

	            //ResultSet re = statement.executeQuery(sql);
	            //re.last();      //鐩存帴鎵ц璺冲埌缁撴灉闆嗙殑鏈�鍚庝竴琛�

	            rowcount =0;//= re.getRow();   //杩欎竴鍙ュ氨鑳藉緱鍒扮粨鏋滈泦鐨勮鏁�
//	            re.beforeFirst(); 
	            
	            if(rowcount==0) {
	            	try {
						pstmt.setString(1 ,name);
						pstmt.setString(2 ,ticker);
		            	pstmt.setString(3 ,volumn);
		            	pstmt.setString(4 ,price);
		            	pstmt.setString(5 ,Circulating_Supply);
		            	pstmt.setString(6 ,perhour);
		            	pstmt.setString(7 ,perday);
		            	pstmt.setString(8 ,perweek);
		            	pstmt.setString(9 ,time1);
		            	pstmt.addBatch();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	
	            	insertsql+="insert into coinmarketdata values(null,'"+name+"','"+ticker+"','"+volumn+"','"+price+"','"+Circulating_Supply+"','"+perhour+"','"+perday+"','"+perweek+"','"+time1+"');";
	            	
	            }else {
	            	System.out.println(1);
	            }
	       // }
		//catch(SQLException e1) {
			//System.out.println(e1.getErrorCode());
		//}
			
		}
		int[] rows;
		try {
			rows = pstmt.executeBatch();
			int row = rows.length;
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

}
