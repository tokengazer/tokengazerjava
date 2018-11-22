import java.io.*;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
public  class GetHistoryChartsData implements PageProcessor {
	public static int App_api_id=220865;
	public static String App_api_hash="679fed61d1930a804eb6d153677792f3";
	public static String token="608015553:AAExwq5eLT7Uwy_qWnZ878MMPb-0Y1F-LJ8";
	private Site site = new Site().setRetryTimes(3).setSleepTime(100).setDomain("www.icorating.com");
	public static void main(String[] args) {
		//String url="https://api.telegram.org/bot"+token+"/getMessage?id=hashgraphnews";
		//String[]list= {"bitcoin","ethereum","ripple","bitcoin-cash","eos","litecoin","stellar","cardano","iota","golem-network-tokens","enigma","rlc","sonm","elastic","gridcoin","foldingcoin","tether"};
		//String[]list1= {"bitcoin","ethereum","XRP","bitcoin-cash","eos","litecoin","stellar","cardano","iota","golem","enigma","iExec RLC","sonm","elastic","gridcoin","FoldingCoin","Tether"};
		
      String url="https://graphs2.coinmarketcap.com/currencies/tezos/1528169350000/1536118150000/";
      Request request=new Request(url);
      
				Spider.create(new GethistoryChartsDatasToSql()).addRequest(request).run();
		
		//String url="https://coinmarketcap.com/currencies/enigma-project/historical-data/?start=20130428&end=20180621";
		
	}
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me().setDomain("https://graphs.coinmarketcap.com").setTimeOut(10000);

	}
	

}
