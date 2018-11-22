import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

public class GetGlobalmarketcapHistory implements PageProcessor {

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		String sqlurl = "jdbc:mysql://localhost:3306/tokengazer";
		String sql="";
        String user = "tokengazer";
        Statement stat=null;
        String password = "Token123";
        Connection con = null;
		
		String marketcapHtml = page.getRawText().toString().split("\"market_cap_by_available_supply\": \\[")[1]
				.split(", \"volume_usd\":")[0];
		String[] timestrap = marketcapHtml.split("\\[");
		int i = 0;
		List times=new ArrayList();
		List totalvolumeList=new ArrayList();
		for(String tm:timestrap) {
			if(i==0) {
				i++;
				continue;
			}
			String time1=tm.split(",")[0];
			String time2=stampToDate(time1);
			times.add(time2);
			String volume=tm.split(",")[1].split("\\]")[0];
			totalvolumeList.add(volume);
			Statement stat1=null;
			try {
				con = DriverManager.getConnection(sqlurl,user,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stat1=con.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sql="insert into historyMarketCap (time,volume) values ('"+time2+"','"+volume+"');";
						
			try {
				stat1.execute(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		System.out.println(marketcapHtml);
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}

	public static void main(String[] args) {
		Spider.create(new GetGlobalmarketcapHistory())
				.addUrl("https://graphs2.coinmarketcap.com/global/marketcap-total/1451577600000/1542866838000/").run();

	}
	public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
