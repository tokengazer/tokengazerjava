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

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class icorating implements PageProcessor{
	private Site site = new Site().setRetryTimes(3).setSleepTime(100).setDomain("www.icorating.com");
	public static void main(String[] args) {
		Spider.create(new icorating()).addUrl("https://icorating.com/ico/").run();
		
	}


	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		//System.out.println(page.getHeaders());
		Selectable selectable=html.xpath("//div[@id='main_table']");
		Selectable selectable1=selectable.xpath("//tbody");
		Map<String, List<String>> headers=page.getHeaders();
		Object[]  setcookie=headers.get("Set-Cookie").toArray();
		String[] tmp=setcookie[0].toString().split("=|;");
		String XSRFTOKEN=tmp[1];
		String[] tmp1=setcookie[1].toString().split("=|;");
		String icoratingsession=tmp1[1];
		System.out.println(tmp1[1]);
//		String[] strings = new String[setcookie.size()];
//
//		setcookie.toArray(strings);
		//System.out.println(setcookie);
		
		//System.out.println(setcookie.get(0));
        List<String> questionList =  selectable1.xpath("tr/@data-href").all();
        for(Selectable tr1 : selectable1.xpath("//tr").nodes()) {
        	int i=0;
        	String name="";String IcoStartDate="";String Detailurl="";
        	for(Selectable td1 : tr1.xpath("td").nodes()) {
        		//System.out.println(td1.xpath("td").nodes());
        		//.xpath("td/text()").nodes()
        		if(i==1) {
		        name=td1.xpath("td/text()").get();
		        System.out.println(name);
        		}
        		else if(i==2) {
    		        IcoStartDate=td1.xpath("td/text()").get();
    		        System.out.println(IcoStartDate);
            	}
        		else if(i==3) {
    		        Detailurl=td1.xpath("td//").links().get();
    		        Spider.create(new icorating()).addUrl(Detailurl).run();
    		        
    		        System.out.println(Detailurl);
            	}
        		
		        		i++;
			        	//String a=td1.xpath("td").toString();
		     }
        	Mysql ms=new Mysql();
        	String[] name1=name.split("\\(");
        	name=name1[0].trim();
    		String sql="select count(*) from webmagic_ico_Analysis where name='"+name+"'";
    		int re=ms.Runsql(sql);
    		if(re==0) {
    			sql="insert into webmagic_ico_Analysis (name,ZHName,ticker)values('"+name+"','','');";
    			int res=ms.Excutesql(sql,"insert");
    			System.out.println(res);
    		}
    		else {
    		//System.out.println(ms.Runsql(sql));
    		}
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
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}
	
}
