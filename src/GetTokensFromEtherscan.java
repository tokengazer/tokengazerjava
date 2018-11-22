import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

public class GetTokensFromEtherscan  implements PageProcessor{

	public static void main(String[] args) {
		//Spider.create(new GetTokensFromEtherscan()).addUrl("https://etherscan.io/tokens?p=1").run();
		Spider.create(new GetTokensFromEtherscan()).addUrl("https://etherscan.io/tokens?p=4").run();
	}
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		int j=151;
		Connection con=null;
		String driver = "com.mysql.jdbc.Driver";

        String sqlurl = "jdbc:mysql://rm-bp1kq68i5h4493twvzo.mysql.rds.aliyuncs.com:3306/app_tokenworm";

        String user = "lybjx";
        Statement stat=null;
        String password = "Lybjx54709488dh";
		List<String> TrHtml=page.getHtml().xpath("//tbody").xpath("//td[@class='hidden-xs']").xpath("//h5").xpath("//a/text()").all();
		for(String na:TrHtml) {
			String name=na.split("\\(")[0];
			String ticker=na.split("\\(")[1].split("\\)")[0];
			String sql="insert into EtherScantoken (id,name,ticker,rank) values(null,'"+name+"','"+ticker+"',"+j+");";
			try {
				con = DriverManager.getConnection(sqlurl,user,password);
				stat=con.createStatement();
				stat.execute(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			j++;
		}
		System.out.println(TrHtml);
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}

}
