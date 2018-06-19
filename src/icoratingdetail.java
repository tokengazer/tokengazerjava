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

public class icoratingdetail implements PageProcessor{
	private Site site = new Site().setRetryTimes(3).setSleepTime(100).setDomain("www.icorating.com");
	public static void main(String[] args) {
		//Spider.create(new icoratingdetail()).addUrl("https://icorating.com/ico/casper-api/details/").run();
		
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
        Selectable mainpage =  html.xpath("//div[@class='uk-section switcher-block-unchangeable']");
        List<String> trlist= mainpage.xpath("tr").all();
        //System.out.println(trlist);
        
        for(String tr:trlist) {
        	int i=0;
        	if(tr.contains("Pre-ICO start date")) {
        		String Prestartdate=(tr.replace("<td>Pre-ICO start date:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}else if(tr.contains("Pre-ICO end date")) {
        		String Preenddate=(tr.replace("<td>Pre-ICO end date:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}else if(tr.contains("Pre-ICO Token Supply:")) {
        		String Preicotokensupply=(tr.replace("<td>Pre-ICO Token Supply:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}else if(tr.contains("ICO start date")) {
        		String Icostartdate=(tr.replace("<td>ICO start date:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}else if(tr.contains("ICO end date")) {
        		String Icoenddate=(tr.replace("<td>ICO end date:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}else if(tr.contains("ICO Token Supply")) {
        		String Icotokensupply=(tr.replace("<td>ICO Token Supply:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}else if(tr.contains("Soft cap")) {
        		String Softcap=(tr.replace("<td>Soft cap:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}else if(tr.contains("Hard cap")) {
        		String Hardcap=(tr.replace("<td>Hard cap:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}else if(tr.contains("Type")) {
        		String Softcap=(tr.replace("<td>Type:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}else if(tr.contains("Hard cap")) {
        		String Softcap=(tr.replace("<td>Hard cap:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}else if(tr.contains("Token Standard")) {
        		String Tokenstandard=(tr.replace("<td>Token Standard:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}else if(tr.contains("Additional Token Emission")) {
        		String AdditionalTokenEmission=(tr.replace("<td>Additional Token Emission:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}else if(tr.contains("Token price in USD")) {
        		String TokenpriceinUSD=(tr.replace("<td>Token price in USD:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}else if(tr.contains("Accepted Currencies")) {
        		String AcceptedCurrencies=(tr.replace("<td>Accepted Currencies:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}else if(tr.contains("Bonus Program")) {
        		String BonusProgram=(tr.replace("<td>Bonus Program:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}else if(tr.contains("Token distribution")) {
        		String Tokendistribution=(tr.replace("<td>Token distribution:</td>", "").split("<td>")[1]).split("</td>")[0].replaceAll("<p .*?>", "\r\n").replaceAll("<br\\s*/?>", "\r\n").replaceAll("<p>", "").replaceAll("</p>", "");
        		System.out.println(Tokendistribution);
            	}else if(tr.contains("ICO Platform")) {
        		String ICOPlatform=(tr.replace("<td>ICO Platform:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}else if(tr.contains("Bounty")) {
        		String Bounty=(tr.replace("<td>Bounty:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}else if(tr.contains("Social Media")) {
        		String SocialMedia=(tr.replace("<td>Social Media:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}else if(tr.contains("Bitcointalk Signature Campaign:")) {
        		String BitcointalkSignatureCampaign=(tr.replace("<td>Bitcointalk Signature Campaign:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}else if(tr.contains("Other")) {
        		String Other=(tr.replace("<td>Other:</td>", "").split("<td>")[1]).split("</td>")[0];
        	}
        	/*if(tr.xpath("td/text()").nodes().get(0).toString().contains("ICO date")) {
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
        	}*/
        }
       
        }
        //List<String> nameList =selectable1.nodes().get(0).$("<tr").$("td");
        String[] jsdkf = new String[56];
        String[] arr=new String[56];
//        if(questionList != null && questionList.size() > 1)
//        {
//            //i=0鏄垪鍚嶇О锛屾墍浠浠�1寮�濮�
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
	