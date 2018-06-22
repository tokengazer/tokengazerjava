import java.io.*;
import java.sql.Array;
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
public  class Gethistorydata implements PageProcessor {
	public static int App_api_id=220865;
	public static String App_api_hash="679fed61d1930a804eb6d153677792f3";
	public static String token="608015553:AAExwq5eLT7Uwy_qWnZ878MMPb-0Y1F-LJ8";
	public static void main(String[] args) {
		//String url="https://api.telegram.org/bot"+token+"/getMessage?id=hashgraphnews";
		String url="https://coinmarketcap.com/currencies/enigma-project/historical-data/?start=20130428&end=20180621";
		Spider.create(new Gethistory()).addUrl(url).run();
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
