/**
 * 20180704对Prestartdate，Preenddate在pro内的赋值进行修改
 * */
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
		Spider.create(new icoratingdetail()).addUrl("https://icorating.com/ico/casper-api/").run();
		
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
		List<Selectable> tablelist=html.xpath("//div[@class='c-card-info c-card-info--border-grey mb-brand']").nodes();
		String Prestartdate="";
		String Preenddate="";
		String Presalesupply="";
		String Icostartdate="";
		String Icoenddate="";
		String Icotokensupply="";
		String Type="";
		String Softcap="";
		String Hardcap="";
		String Icoraisedmoney="";
		String ticker="";
		String TokenType="";
		String AdditionalTokenEmission="";
		String AcceptedCurrencies="";
		String Tokendistribution="";
		String BonusProgram="";
		String FundsAllocation="";
		String FacebookUrl="";
		String TwitterUrl="";
		String GithubUrl="";
		String TelegramUrl="";
		String LinkedUrl="";
		String TokenpriceinUSD="";
		String MVP="";
		String Whitepaper="";
		String logo="https://icorating.com/"+html.xpath("//div[@class='o-media c-card-media mt40 mb40'").toString().split("src=\"")[1].split("\"")[0];
		String Industry=html.xpath("//p[@class='c-card-media__status c-card-media__status--live']").toString().replace("<p class=\"c-card-media__status c-card-media__status--live\">", "").replace("</p>", "").trim();
		String Icoraisemoney=html.xpath("//p[@class='c-card-info__goal']").toString().replace("<p class=\"c-card-info__goal\">", "").replace("</p>", "").split("<")[0];
		String Description=html.xpath("//div[@class='mb15'").toString().replace("<p>", "").replace("</p>", "");
		String website=html.xpath("//a[@class='c-button c-button--teal c-button--block mt15 cp'").toString().split("href=\"")[1].split("\"")[0];
        int i1=0;
        List<Selectable> PriceHtml=html.xpath("//table[@class='c-card-info__table']").xpath("//tr").nodes();
        
        List<Selectable> SocialHtml=html.xpath("//div[@class='c-social-icons c-social-icons--grey c-social-icons--center mb15 mt10']").xpath("//a").nodes();
    	
    	for(Selectable tb:tablelist) {
    		//有些项目中有presale ，有些没有，多个table共用一个class 名
    		if(tb.toString().contains("Pre-sale")) {
    				List<Selectable> tr1=tb.xpath("//tr").nodes();
    				for(Selectable td:tr1) {
    					if(td.toString().contains("Pre-sale start date")) {
    						Prestartdate=td.xpath("//td[@class='c-info-table__cell tar']/text()").toString();
    						System.out.print(3);
    					}
    					if(td.toString().contains("Pre-sale end date")) {
    						Preenddate=td.xpath("//td[@class='c-info-table__cell tar']/text()").toString();
    						System.out.print(3);
    					}
    					if(td.toString().contains("Pre-sale token supply")) {
    						Presalesupply=td.xpath("//td[@class='c-info-table__cell tar']/text()").toString();
    						System.out.print(3);
    					}
    					System.out.println(td.toString());
    					System.out.println(4);
    				}
    				
    		}
    		else if(tb.xpath("//caption[@class='c-info-table__caption']").toString().contains("ICO")) {
    			List<Selectable> tr1=tb.xpath("//tr").nodes();
    			for(Selectable td:tr1) {
					if(td.toString().contains("ICO start date")) {
						Icostartdate=td.xpath("//td[@class='c-info-table__cell tar']/text()").toString();
						
					}
					if(td.toString().contains("ICO end date")) {
						Icoenddate=td.xpath("//td[@class='c-info-table__cell tar']/text()").toString();
					}
					if(td.toString().contains("ICO token supply")) {
						Icotokensupply=td.xpath("//td[@class='c-info-table__cell tar']/text()").toString();
					}
					if(td.toString().contains("Soft cap")) {
						Softcap=td.xpath("//td[@class='c-info-table__cell tar']/text()").toString();
					}
					if(td.toString().contains("Hard cap size")) {
						Hardcap=td.xpath("//td[@class='c-info-table__cell tar']/text()").toString();
					}
					if(td.toString().contains("Raised")) {
						Icoraisedmoney=td.xpath("//td[@class='c-info-table__cell tar']/text()").toString();
					}
					
				}		
    		}
    		
    	}
    	Selectable tokendetailhtml=html.xpath("//div[@class='c-card-info c-card-info--border-grey']");
			List<Selectable> tr1=tokendetailhtml.xpath("//tr").nodes();
			for(Selectable td:tr1) {
				if(td.toString().contains("Ticker")) {
					ticker=td.xpath("//td[@class='c-info-table__cell remove-indents tar']/text()").toString();
					
				}
				if(td.toString().contains("Type")) {
					Type=TokenType=td.xpath("//td[@class='c-info-table__cell remove-indents tar']/text()").toString();
				}
				if(td.toString().contains("Additional Token Emission")) {
					AdditionalTokenEmission=td.xpath("//td[@class='c-info-table__cell remove-indents tar']/text()").toString();
				}
				if(td.toString().contains("Accepted Currencies")) {
					AcceptedCurrencies=td.xpath("td[@class='c-info-table__cell remove-indents tar']").toString().replace("<td class=\"c-info-table__cell remove-indents tar\"> <p>", "").replace("</td>", "").replace("</p> <p>", ",").replace("<p>", "").replace("</p>", "");
				}
				if(td.toString().contains("Token distribution")) {
					Tokendistribution=td.xpath("//td[@class=\"c-info-table__cell tar remove-indents\"]").toString().replace("<td class=\"c-info-table__cell tar remove-indents\"> <p>", "").replace("</td>", "").replace("<br>", ",");
				}
				if(td.toString().contains("Raised")) {
					Icoraisedmoney=td.xpath("//td[@class='c-info-table__cell remove-indents tar']/text()").toString();
				}
				if(td.toString().contains("Bonus Program")) {
					BonusProgram=td.xpath("//td[@class='c-info-table__cell remove-indents tar']/text()").toString();
				}
				if(td.toString().contains("Funds allocation")) {
					FundsAllocation=td.xpath("//td[@class='c-info-table__cell remove-indents tar']/text()").toString();
				}
				if(td.toString().contains("Raised")) {
					Icoraisedmoney=td.xpath("//td[@class='c-info-table__cell remove-indents tar']/text()").toString();
				}
				
			}		
		
    	
    	//获取社媒部分
    	
    	for(Selectable socialtr:SocialHtml) {
    		Selectable links=socialtr.links();
    		if(socialtr.toString().contains("facebook")) {
    			FacebookUrl=socialtr.links().get(); 
    		}
    		if(socialtr.toString().contains("twitter")) {
    			TwitterUrl=socialtr.links().get(); 
    		}
    		if(socialtr.toString().contains("linkedin")) {
    			LinkedUrl=socialtr.links().get(); 
    		}
    		if(socialtr.toString().contains("github")) {
    			GithubUrl=socialtr.links().get(); 
    		}
    		if(socialtr.toString().contains("telegram")||socialtr.toString().contains("t.me")) {
    			TelegramUrl=socialtr.links().get(); 
    		}
    	}
    	//获取price mvp等部分
    	
    	List<Selectable> tr2=PriceHtml.get(0).nodes();
    	for(Selectable tr3:tr2) {
    		if(tr3.toString().contains("Price")) {
    			TokenpriceinUSD=tr3.toString().split("=")[1].split("USD")[0];
    		}
    		if(tr3.toString().contains("MVP")) {
    			MVP=tr3.xpath("td[@class='c-card-info__cell tar']").toString().replace("<td class=\"c-card-info__cell tar\">", "").replace("</td>", "");
    			if(MVP.contains("no")) {
    				MVP="无";
    			}
    			else {
    				MVP="有";
    			}
    		}
    		if(tr3.toString().contains("Whitepaper")) {
    			Whitepaper=tr3.links().get();
    		}
    	}
    	
        Project pro=new Project();
        
        pro.setPrestartdate(Prestartdate);
        pro.setPresaledate(Preenddate);
        pro.setIco_start_time(Icostartdate);
        pro.setIco_end_time(Icoenddate);
        pro.setICO_Total_Amount(Icotokensupply);
        pro.setICO_HardCap(Hardcap);
        pro.setPlatform(Type);
        //pro.setTokenStandard(Tokenstandard);
        pro.setAdditionalTokenEmission(AdditionalTokenEmission);
        pro.setIco_Price_Usd(TokenpriceinUSD);
        pro.setAcceptedCurrencies(AcceptedCurrencies);
        pro.setTokenDistribution(Tokendistribution);
        pro.setMVP(MVP);
        pro.setDataSource("icorating");
        pro.setDescription(Description);
        pro.setFacebookurl(FacebookUrl);
        pro.setFundsAllocation(FundsAllocation);
        pro.setGithuburl(GithubUrl);
        pro.setICO_Raise_money(Icoraisemoney);
        pro.setICO_Raised_money(Icoraisedmoney);
        pro.setIndustry(Industry);
        pro.setLinkedin(LinkedUrl);
        pro.setLogo(logo);
        pro.setMVP(MVP);
        pro.setTelegramurl(TelegramUrl);
        pro.setTwitterurl(TwitterUrl);
        pro.setWebsite(website);
        pro.setWhitepaper(Whitepaper);
        	
        	
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
        
        

    	
    	
       
       
	

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me().setTimeOut(20000);
	}
	
}
	