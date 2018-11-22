import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.selector.Selectable;

public class GethistoryChartsDatasToSql implements PageProcessor {

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Json html=page.getJson();
				List<String> ids = new JsonPathSelector("$.market_cap_by_available_supply.*").selectList(page.getRawText());
				List<String> prices = new JsonPathSelector("$.price_usd.*").selectList(page.getRawText());
				List<String> volumes = new JsonPathSelector("$.volume_usd.*").selectList(page.getRawText());
				Connection con = null;
				int res1=-2;
		        
				String sqlurl = "jdbc:mysql://localhost:3306/tokengazer";

		        String user = "tokengazer";
		        Statement stat=null;
		        String password = "Token123";
		        //String insertsql="";
				//Selectable trlist=table.xpath("//tr");
		        int i=0;
				for(String trlist:ids) {
					String date=trlist.split("\\[")[1].split(",")[0];
					String dat=stampToDate(date);
					String Marketcap_y=trlist.split("\\[")[1].split(",")[1].split("\\]")[0];
					String price=prices.get(i).toString().split("\\[")[1].split(",")[1].split("\\]")[0];
					String volumn=volumes.get(i).toString().split("\\[")[1].split(",")[1].split("\\]")[0];
					String sql="";
					i++;
					 try {
							con = DriverManager.getConnection(sqlurl,user,password);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					try {
						Statement stat1=null;
						stat1=con.createStatement();
						//sql="insert into historychartsdata (name,datetime,openprice,highprice,lowprice,endprice,volumn,marketcap) values ('"+name+"','"+datetime+"','"+openprice+"','"+highprice+"','"+lowprice+"','"+endprice+"','"+volumn+"','"+marketcap+"');";
							sql="insert into historychartsdata (id,Date,Marketcap_x,Marketcap_y,price_usd,volume_usd) values(null,'"+dat+"','"+dat+"','"+Marketcap_y+"','"+price+"','"+volumn+"');";
						stat1.execute(sql);
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				//System.out.print(table);
				
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}
	/* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
