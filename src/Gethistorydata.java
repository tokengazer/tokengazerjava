import java.io.*;
import java.sql.Array;
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
public  class Gethistorydata implements PageProcessor {
	public static int App_api_id=220865;
	public static String App_api_hash="679fed61d1930a804eb6d153677792f3";
	public static String token="608015553:AAExwq5eLT7Uwy_qWnZ878MMPb-0Y1F-LJ8";
	public static void main(String[] args) {
		//String url="https://api.telegram.org/bot"+token+"/getMessage?id=hashgraphnews";
		//String[]list= {"bitcoin","ethereum","ripple","bitcoin-cash","eos","litecoin","stellar","cardano","iota","golem-network-tokens","enigma","rlc","sonm","elastic","gridcoin","foldingcoin","tether"};
		//String[]list1= {"bitcoin","ethereum","XRP","bitcoin-cash","eos","litecoin","stellar","cardano","iota","golem","enigma","iExec RLC","sonm","elastic","gridcoin","FoldingCoin","Tether"};
		String[]list= {"firstblood"};
		String[]list1= {"firstblood"};
		int i=0;
		ArrayList<Request> list2=new ArrayList<Request>();
		for(String li:list) {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//
			String da=df.format(new Date());
			String url="https://coinmarketcap.com/currencies/"+li+"/historical-data/?start=20130428&end="+da+"&name="+list1[i];
			Request request=new Request(url);
			list2.add(request);
			//Spider.create(new Gethistory()).addUrl(url).run();
			i++;
		}
		Request[] strings = new Request[list2.size()];
        list2.toArray(strings);
        Spider.create(new Gethistory()).addRequest(strings).thread(30).run();
		//String url="https://coinmarketcap.com/currencies/enigma-project/historical-data/?start=20130428&end=20180621";
		
	}
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}
	

}
