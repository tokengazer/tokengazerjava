import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class icoratingmainpage implements PageProcessor{
	private Site site = new Site().setRetryTimes(3).setSleepTime(100).setDomain("www.icorating.com");
	public static void main(String[] args) {
		Spider.create(new icoratingmainpage()).addUrl("https://icorating.com/ico/casper-api/").run();
		
	}


	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		//System.out.println(page.getHeaders());
		
//		String[] strings = new String[setcookie.size()];
//
//		setcookie.toArray(strings);
		//System.out.println(setcookie);
		
		//System.out.println(setcookie.get(0));
        Selectable mainpage =  html.xpath("//div[@class='mt30']");
        Selectable imghtml=mainpage.xpath("//div[@class='img']");
        String logo=imghtml.regex("/cache/logos/([a-zA-z0-9]{40})").toString();
        if(logo!=null) {
        	logo="https://icorating.com/cache/logos/"+logo+"-151x151.jpeg";
        	System.out.println(logo);
        }
        String namehtml=mainpage.xpath("h1/text()").toString();
        Selectable trlist=mainpage.xpath("//tbody");
        
        for(Selectable tr:trlist.xpath("//tr").nodes()) {
        	int i=0;
        	if(tr.xpath("td/text()").nodes().get(0).toString().contains("ICO date")) {
        		String icodate=tr.xpath("td/text()").nodes().get(1).toString();
        	}else if(tr.xpath("td/text()").nodes().get(0).toString().contains("Product Type")) {
        		String Producttype=tr.xpath("td/text()").nodes().get(1).toString();
        	}else if(tr.xpath("td/text()").nodes().get(0).toString().contains("Industry")) {
        		String Industry=tr.xpath("td/text()").nodes().get(1).toString();
        	}else if(tr.xpath("td/text()").nodes().get(0).toString().contains("Description")) {
        		String Description=tr.xpath("td/text()").nodes().get(1).toString();
        	}
        	else if(tr.xpath("td/text()").nodes().get(0).toString().contains("Website")) {
        		String website=tr.xpath("td").links().toString();
        	}else if(tr.xpath("td/text()").nodes().get(0).toString().contains("Whitepaper")) {
        		String whitepaper=tr.xpath("td").links().toString();
        	}
        }
        //社交部分链接
        Selectable socialhtml=mainpage.xpath("//div[@class=\"uk-child-width-expand uk-grid-small uk-text-center\"]/div");
        List<String> firstsocialhtml=socialhtml.xpath("//a").all();
        for(String str : firstsocialhtml) {//其内部实质上还是调用了迭代器遍历方式，这种循环方式还有其他限制，不建议使用。
            if(str.contains("Website")) {
            	String website=str.split("href=\"")[1];
            	website=website.split("\"")[0];
            	System.out.println(website);
            }else if(str.contains("Twitter")) {
            	String Twitterurl=str.split("href=\"")[1];
            	Twitterurl=Twitterurl.split("\"")[0];
            	System.out.println(Twitterurl);
            }else if(str.contains("Facebook")) {
            	String Facebookurl=str.split("href=\"")[1];
            	Facebookurl=Facebookurl.split("\"")[0];
            	System.out.println(Facebookurl);
            }else if(str.contains("Telegram")) {
            	String Telegramurl=str.split("href=\"")[1];
            	Telegramurl=Telegramurl.split("\"")[0];
            	System.out.println(Telegramurl);
            }else if(str.contains("Github")) {
            	String Githuburl=str.split("href=\"")[1];
            	Githuburl=Githuburl.split("\"")[0];
            	System.out.println(Githuburl);
            }
        }
        System.out.println(firstsocialhtml);
        String name=namehtml;
        	Mysql ms=new Mysql();
        	String[] name1=name.split("\\(");
        	name=name1[0].trim();
        	String ticker=name1[1].split("\\)")[0].trim();
    		/*String sql="select count(*) from webmagic_ico_Analysis where name='"+name+"'";
    		int re=ms.Runsql(sql);
    		if(re==0) {
    			sql="insert into webmagic_ico_Analysis (name,ZHName,ticker,logo,description)values('"+name+"','','"+ticker+"','"+logo+"','"+description+"');";
    			int res=ms.Excutesql(sql);
    			System.out.println(res);
    		}
    		else {
    		System.out.println(ms.Runsql(sql));
    		}*/
        }
        //List<String> nameList =selectable1.nodes().get(0).$("<tr").$("td");
        String[] jsdkf = new String[56];
        String[] arr=new String[56];
//        if(questionList != null && questionList.size() > 1)
//        {
//            //i=0是列名称，所以i从1开始
//            for( int i = 1 ; i < questionList.size(); i++)
//            {
//            	String[] list = new String[56];
//            	String name=questionList.get(i).regex("<td>",1);
//            	list[1]="cctv";
//            	arr[i]=list[1];
//            }
//        }
	

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}
	
}
	