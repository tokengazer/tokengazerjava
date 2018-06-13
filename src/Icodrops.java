import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Icodrops implements PageProcessor{
	private Site site = new Site().setRetryTimes(3).setSleepTime(100).setDomain("www.icorating.com");
	public static void main(String[] args) {
		start();
		
	}
	public static void start() {
		Spider.create(new Icodrops()).addUrl("https://icodrops.com/category/active-ico/").run();
		
	}
	public static void ended() {
		Spider.create(new Icodrops()).addUrl("https://icodrops.com/category/ended-ico/").run();
		
	}
	public static void upcoming() {
		Spider.create(new Icodrops()).addUrl("https://icodrops.com/category/upcoming-ico/").run();
		
	}

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		//System.out.println(page.getHeaders());
		Selectable selectable=html.xpath("//div[@class='category-desk justify-content-center'");
		List<String> links=selectable.links().all();
       for(String url:links) {
    	   Spider.create(new Icodropsdetail()).addUrl(url).run();
       }
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}
	
}
