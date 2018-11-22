import java.io.*;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
public  class Gethistory implements PageProcessor {
	public static int App_api_id=220865;
	public static String App_api_hash="679fed61d1930a804eb6d153677792f3";
	public static String token="608015553:AAExwq5eLT7Uwy_qWnZ878MMPb-0Y1F-LJ8";
	public static void main(String[] args) {
		ArrayList<project1> List=new ArrayList<project1>();
		project1 pro=new project1();
		/*pro.setTicker("BTC");
		pro.setName("bitcoin");
		List.add(pro);
		//pro=new project1();
		pro=new project1();
		pro.setTicker("ETH");
		pro.setName("ethereum");
		List.add(pro);//pro=new project1();
		pro=new project1();
		pro.setTicker("XRP");
		pro.setName("ripple");
		List.add(pro);pro=new project1();
		pro=new project1();
		pro.setTicker("BCH");
		pro.setName("bitcoin-cash");
		List.add(pro);pro=new project1();
		pro=new project1();
		pro.setTicker("EOS");
		pro.setName("eos");
		List.add(pro);*/
		/*pro=new project1();
		pro.setTicker("XLM");
		pro.setName("stellar");
		List.add(pro);*/
		/*pro=new project1();
		pro.setTicker("LTC");
		pro.setName("litecoin");
		List.add(pro);pro=new project1();
		pro.setTicker("ADA");
		pro.setName("cardano");
		List.add(pro);pro=new project1();
		pro.setTicker("XMR");
		pro.setName("monero");
		List.add(pro);pro=new project1();
		pro.setTicker("TRX");
		pro.setName("tron");
		List.add(pro);pro=new project1();
		pro.setTicker("MIOTA");
		pro.setName("iota");
		List.add(pro);pro=new project1();
		pro.setTicker("DASH");
		pro.setName("dash");
		List.add(pro);pro=new project1();
		pro.setTicker("BNB");
		pro.setName("binance-coin");
		List.add(pro);pro=new project1();
		pro.setTicker("NEO");
		pro.setName("neo");
		List.add(pro);pro=new project1();
		pro.setTicker("ETC");
		pro.setName("ethereum-classic");
		List.add(pro);pro=new project1();
		pro.setTicker("XEM");
		pro.setName("nem");
		List.add(pro);pro=new project1();
		pro.setTicker("VET");
		pro.setName("vechain");
		List.add(pro);pro=new project1();
		pro.setTicker("ZEC");
		pro.setName("zcash");
		List.add(pro);pro=new project1();
		pro.setTicker("HT");
		pro.setName("huobi-token");
		List.add(pro);pro=new project1();
		pro.setTicker("MKR");
		pro.setName("maker");
		List.add(pro);pro=new project1();
		pro.setTicker("BTG");
		pro.setName("bitcoin-gold");
		List.add(pro);pro=new project1();
		pro.setTicker("OMG");
		pro.setName("omisego");
		List.add(pro);pro=new project1();
		pro.setTicker("ZRX");
		pro.setName("0x");
		List.add(pro);pro=new project1();
		pro.setTicker("DOGE");
		pro.setName("dogecoin");
		List.add(pro);pro=new project1();
		pro.setTicker("DCR");
		pro.setName("decred");
		List.add(pro);pro=new project1();
		//List.add("okex?ticker=OKB");
		pro.setTicker("QTUM");
		pro.setName("qtum");
		List.add(pro);pro=new project1();
		pro.setTicker("LSK");
		pro.setName("lisk");
		List.add(pro);pro=new project1();
		pro.setTicker("AE");
		pro.setName("aeternity");
		List.add(pro);pro=new project1();
		pro.setTicker("NANO");
		pro.setName("nano");
		List.add(pro);pro=new project1();
		pro.setTicker("SC");
		pro.setName("Siacoin");
		List.add(pro);pro=new project1();
		pro.setTicker("BAT");
		pro.setName("basic-attention-token");
		List.add(pro);pro=new project1();
		pro.setTicker("XUC");
		pro.setName("exchange-union");
		List.add(pro);pro=new project1();
		pro.setTicker("BTS");
		pro.setName("bitshares");
		List.add(pro);pro=new project1();
		pro.setTicker("ICX");
		pro.setName("icon");
		List.add(pro);pro=new project1();
		pro.setTicker("BCN");
		pro.setName("bytecoin-bcn");
		List.add(pro);pro=new project1();
		pro.setTicker("DGB");
		pro.setName("digibyte");
		List.add(pro);pro=new project1();
		pro.setTicker("STEEM");
		pro.setName("steem");
		List.add(pro);pro=new project1();
		pro.setTicker("XVG");
		pro.setName("verge");
		List.add(pro);pro=new project1();
		pro.setTicker("BTM");
		pro.setName("bytom");
		List.add(pro);pro=new project1();
		pro.setTicker("WAVES");
		pro.setName("waves");
		List.add(pro);pro=new project1();
		pro.setTicker("GNT");
		pro.setName("golem-network-tokens");
		List.add(pro);pro=new project1();
		pro.setTicker("START");
		pro.setName("startcoin");
		List.add(pro);pro=new project1();
		pro.setTicker("ETP");
		pro.setName("metaverse");
		List.add(pro);pro=new project1();
		pro.setTicker("KMD");
		pro.setName("komodo");
		List.add(pro);pro=new project1();
		pro.setTicker("REP");
		pro.setName("augur");
		List.add(pro);pro=new project1();
		pro.setTicker("LINK");
		pro.setName("chainlink");
		List.add(pro);pro=new project1();
		pro.setTicker("HOT");
		pro.setName("holo");
		List.add(pro);pro=new project1();
		pro.setTicker("PPT");
		pro.setName("populous");
		List.add(pro);pro=new project1();
		pro.setTicker("SNT");
		pro.setName("status");
		List.add(pro);pro=new project1();
		pro.setTicker("WTC");
		pro.setName("waltonchain");
		List.add(pro);pro=new project1();
		pro.setTicker("CNX");
		pro.setName("cryptonex");
		List.add(pro);pro=new project1();
		pro.setTicker("ETN");
		pro.setName("ekectroneum");
		List.add(pro);pro=new project1();
		pro.setTicker("RVN");
		pro.setName("ravencoin");
		List.add(pro);pro=new project1();
		pro.setTicker("ARDR");
		pro.setName("ardor");
		List.add(pro);pro=new project1();
		pro.setTicker("AION");
		pro.setName("aion");
		List.add(pro);pro=new project1();
		pro.setTicker("IOST");
		pro.setName("iostoken");
		List.add(pro);pro=new project1();
		pro.setTicker("KCS");
		pro.setName("kucoin-shares");
		List.add(pro);pro=new project1();
		pro.setTicker("NEXO");
		pro.setName("nexo");
		List.add(pro);pro=new project1();
		pro.setTicker("MAIN");
		pro.setName("maidsafecoin");
		List.add(pro);pro=new project1();
		pro.setTicker("DGTX");
		pro.setName("digitex-futures");
		List.add(pro);pro=new project1();
		pro.setTicker("VERI");
		pro.setName("veritaseum");
		List.add(pro);pro=new project1();
		pro.setTicker("LRC");
		pro.setName("loopring");
		List.add(pro);*//*pro=new project1();
		/*pro.setTicker("RDD");
		pro.setName("reddcoin");
		List.add(pro);/*pro=new project1();
		pro.setTicker("ELF");
		pro.setName("aelf");
		List.add(pro);pro=new project1();
		pro.setTicker("GXS");
		pro.setName("gxchain");
		List.add(pro);pro=new project1();
		pro.setTicker("ARK");
		pro.setName("ARK");
		List.add(pro);pro=new project1();
		pro.setTicker("HC");
		pro.setName("hypercash");
		List.add(pro);pro=new project1();
		pro.setTicker("DGD");
		pro.setName("digixdao");
		List.add(pro);pro=new project1();
		pro.setTicker("R");
		pro.setName("revain");
		List.add(pro);pro=new project1();
		pro.setTicker("QASH");
		pro.setName("qash");
		List.add(pro);pro=new project1();
		pro.setTicker("PIVX");
		pro.setName("pivx");
		List.add(pro);pro=new project1();
		pro.setTicker("MANA");
		pro.setName("decentraland");
		List.add(pro);pro=new project1();
		pro.setTicker("MCO");
		pro.setName("crypto-com");
		List.add(pro);pro=new project1();
		pro.setTicker("MGO");
		pro.setName("mobilego");
		List.add(pro);pro=new project1();
		pro.setTicker("BNT");
		pro.setName("bancor");
		List.add(pro);pro=new project1();
		pro.setTicker("MONA");
		pro.setName("monacoin");
		List.add(pro);pro=new project1();
		pro.setTicker("POLY");
		pro.setName("polymath-network");
		List.add(pro);pro=new project1();
		pro.setTicker("loom");
		pro.setName("loom-network");
		List.add(pro);pro=new project1();
		pro.setTicker("FUN");
		pro.setName("funfair");
		List.add(pro);pro=new project1();
		pro.setTicker("DCN");
		pro.setName("dentacoin");
		List.add(pro);pro=new project1();
		pro.setTicker("CMT");
		pro.setName("cybermiles");
		List.add(pro);pro=new project1();
		pro.setTicker("ZEN");
		pro.setName("zencash");
		List.add(pro);pro=new project1();
		pro.setTicker("NAS");
		pro.setName("nebulas-token");
		List.add(pro);pro=new project1();
		pro.setTicker("KNC");
		pro.setName("kyber-network");
		List.add(pro);pro=new project1();
		pro.setTicker("DAI");
		pro.setName("dai");
		List.add(pro);pro=new project1();
		pro.setTicker("GVT");
		pro.setName("genesis-vision");
		List.add(pro);pro=new project1();
		pro.setTicker("POWER");
		pro.setName("power-ledger");
		List.add(pro);pro=new project1();
		pro.setTicker("DROP");
		pro.setName("dropil");
		List.add(pro);pro=new project1();
		pro.setTicker("PAY");
		pro.setName("tenx");
		List.add(pro);pro=new project1();
		pro.setTicker("XZC");
		pro.setName("zcoin");
		List.add(pro);pro=new project1();
		pro.setTicker("NXT");
		pro.setName("nxt");
		List.add(pro);pro=new project1();
		pro.setTicker("SALT");
		pro.setName("salt");
		List.add(pro);pro=new project1();
		pro.setTicker("BITB");
		pro.setName("bean-cash");
		List.add(pro);pro=new project1();
		pro.setTicker("SYS");
		pro.setName("syscoin");
		List.add(pro);pro=new project1();
		pro.setTicker("GAS");
		pro.setName("gas");
		List.add(pro);pro=new project1();
		pro.setTicker("ELA");
		pro.setName("elastos");
		List.add(pro);pro=new project1();
		pro.setTicker("GO");
		pro.setName("gochain");
		List.add(pro);pro=new project1();
		pro.setTicker("OCN");
		pro.setName("odyssey");
		List.add(pro);pro=new project1();
		pro.setTicker("ENG");
		pro.setName("enigma-project");
		List.add(pro);pro=new project1();
		pro.setTicker("DRNG");
		pro.setName("dragonchain");
		List.add(pro);pro=new project1();
		pro.setTicker("NULS");
		pro.setName("nuls");
		List.add(pro);pro=new project1();
		pro.setTicker("BIX");
		pro.setName("bibox-token");
		List.add(pro);pro=new project1();
		pro.setTicker("NXS");
		pro.setName("nexus");
		List.add(pro);pro=new project1();
		pro.setTicker("CTXC");
		pro.setName("cortex");
		List.add(pro);pro=new project1();
		pro.setTicker("CVC");
		pro.setName("civic");
		List.add(pro);pro=new project1();
		pro.setTicker("EMC");
		pro.setName("emercoin");
		List.add(pro);pro=new project1();
		pro.setTicker("STORJ");
		pro.setName("storj");
		List.add(pro);pro=new project1();
		pro.setTicker("NEC");
		pro.setName("nectar");
		List.add(pro);pro=new project1();
		pro.setTicker("DENT");
		pro.setName("DENT");
		List.add(pro);pro=new project1();
		pro.setTicker("SUB");
		pro.setName("substratum");
		List.add(pro);pro=new project1();*/
		pro=new project1();
		pro.setTicker("ZIL");
		pro.setName("Zilliqa");
		List.add(pro);
		pro=new project1();
		pro.setTicker("QKC");
		pro.setName("QuarkChain");
		List.add(pro);
		int i=0;
		for(project1 s:List) {
			String name=s.getName();
			String ticker=s.getTicker();
			Spider.create(new Gethistory()).addUrl("https://coinmarketcap.com/currencies/"+name+"/historical-data/?start=20130428&end=20181027&name="+name+"&ticker="+ticker).run();
		i++;
		}
	}
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		Html html=page.getHtml();
		Selectable table=html.xpath("//table[@class='table']").xpath("//tbody");
		Connection con = null;
		int res1=-2;
        
		String sqlurl = "jdbc:mysql://localhost:3306/tokengazer";

        String user = "tokengazer";
        Statement stat=null;
        String password = "Token123";
        //String insertsql="";
		//Selectable trlist=table.xpath("//tr");
		for(Selectable trlist:table.xpath("//tr").nodes()) {
			int i=0;
			String datetime="";
			String openprice="";
			String highprice="";
			String lowprice="";
			String endprice="";
			String volumn="";
			String marketcap="";
			String sql="";
			//String ticker="";
			String name=page.getRequest().getUrl().split("name=")[1].split("&")[0];
			String ticker=page.getRequest().getUrl().split("ticker=")[1];
			for(Selectable tdlist:trlist.xpath("//td").nodes()) {
				//datetime=tdlist.nodes().get(1).toString();
				if(i==0) {
					datetime=tdlist.xpath("//td/text()").nodes().get(0).toString();
				}
				if(i==1) {
					openprice=tdlist.xpath("//td/text()").nodes().get(0).toString();
				}
				if(i==2) {
					highprice=tdlist.xpath("//td/text()").nodes().get(0).toString();
				}
				if(i==3) {
					lowprice=tdlist.xpath("//td/text()").nodes().get(0).toString();
				}
				if(i==4) {
					endprice=tdlist.xpath("//td/text()").nodes().get(0).toString();
				}
				if(i==5) {
					volumn=tdlist.xpath("//td/text()").nodes().get(0).toString();
				}
				if(i==6) {
					marketcap=tdlist.xpath("//td/text()").nodes().get(0).toString();
				}
				i++;
				//datetime=tdlist.toString();
				
			}
			 try {
					con = DriverManager.getConnection(sqlurl,user,password);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			try {
				Statement stat1=null;
				stat1=con.createStatement();
				
				sql="insert into history (name,ticker,datetime,openprice,highprice,lowprice,endprice,volumn,marketcap) values ('"+name+"','"+ticker+"','"+datetime+"','"+openprice+"','"+highprice+"','"+lowprice+"','"+endprice+"','"+volumn+"','"+marketcap+"');";
							
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
		return Site.me().setTimeOut(10000);
	}
	public static class project1 {
		public String ticker;
		public String name;
		public String getTicker() {
			return ticker;
		}
		public void setTicker(String ticker) {
			this.ticker = ticker;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}

}
