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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public  class GetDataFromETH implements PageProcessor{
	public static void main(String[] args) {
		//Spider.create(new GetDataFromEtherscan()).addUrl("http://www.c-stone.com/product/stone.html").run();
		//String url="https://etherscan.io/token/generic-tokenholders2?a=0xa74476443119A942dE498590Fe1f2454d7D4aC0d&s=1E%2b27&p=1";
		//Spider.create(new GetDataFromETH()).addUrl(url).run();
		
	}
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		Html html=page.getHtml();
		Selectable table=html.xpath("//tbody");
		int i=0;
		Connection con=null;
		
        String driver = "com.mysql.jdbc.Driver";
        
        String url = "jdbc:mysql://rm-bp1kq68i5h4493twvzo.mysql.rds.aliyuncs.com:3306/app_tokenworm";
        
        String user = "lybjx";
        Statement stat=null;
        String password = "Lybjx54709488dh";
        try {
			con=DriverManager.getConnection(url, user, password);
			stat=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List tem=table.xpath("//tr").nodes();
		for(Selectable tr1 : table.xpath("//tr").nodes()) {
			if(i==0) {
				i++;
        		continue;
        	}
			int y=0;
        	String rank="";
        	String address="";
        	System.out.println(i);
        	
        	System.out.println(i);
        	List tmp=tr1.xpath("//td").nodes();
        	for(Selectable td1 : tr1.xpath("//td").nodes()) {
	        	if(y==0) {
	        		rank=td1.xpath("td/text()").get();
	        	}
	        	if(y==1) {
	        		//System.out.println(address);
	        		address=td1.xpath("td").toString().split("a href=\"/token/")[1].split("\"")[0].split("a=")[1];
	        	}
	        	y++;
        	}
        	int rank1=Integer.parseInt(rank.trim());
        	String sql="insert into etherscan_draw (pid,address,rank) values(9999999,'"+address+"',"+rank1+"); ";
        	System.out.println(sql);
        	try {
				stat.addBatch(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	i++;//y++;
		}
		try {
			stat.executeBatch();
			con.setAutoCommit(false);
			con.commit();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}

}
