import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

public class Icodropsdetail implements PageProcessor {
	public static void main(String[] args) {
		Spider.create(new Icodropsdetail()).addUrl("https://icodrops.com/dav-network/").run();
	}
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		Html html=page.getHtml();
		String name=html.xpath("//h3/text()").toString();
		List<String> logohtml=html.xpath("//meta").xpath("[@name='twitter:image']").all();
		String logo=logohtml.get(1).toString().split("content=\"")[1].split("\"")[0];
		String datehtml=html.xpath("//div[@class=\"col-12 title-h4\"]").xpath("//h4/text()").toString().replace("Token Sale:", "");
		String startdate=datehtml.split("-")[0].trim();
		String[] tmp=datehtml.split("–");
		String enddate=tmp[1].trim();
		String industry=html.xpath("//div[@class='ico-category-name']/text()").toString().replace("(", "").replace(")", "");
		
		String description=html.xpath("//div[@class='ico-description']/text()").toString().trim();
		List<String> lilist=html.xpath("//div[@class='row list']").xpath("//li").all();
		Project pro=new Project();
		pro.setIndustry(industry);
		pro.setLogo(logo);
		pro.setIco_start_time(startdate);
		pro.setIco_end_time(enddate);
		String descriptionhtml=html.xpath("//div[@class='ico-description']/text()").toString();
		pro.setDescription(descriptionhtml);
		if(html.toString().contains("Registered Company:")) {
			String origin=html.toString().split("Registered Company:")[1].split("</li")[0];
			pro.setOrigin(origin);
		}
		for(String str:lilist) {
			if(str.contains("Ticker")) {
				String ticker=str.split("</span>")[1].split("</li>")[0];
				pro.setTicker(ticker);
				
			}else if(str.contains("Total Tokens:")) {
				String total_count=str.split("</span>")[1].split("</li>")[0];
				pro.setICO_TotalCount(total_count);
			}
			else if(str.contains("Token type")) {
				String type=str.split("</span>")[1].split("</li>")[0];
				pro.setTokenStandard(type);
			}else if(str.contains("ICO Token Price")) {
				String ICOTokenPrice=str.split("</span>")[1].split("</li>")[0].split("=")[1].split("USD")[0].trim();
				pro.setIco_Price_Usd(ICOTokenPrice);
			}else if(str.contains("Fundraising Goal")) {
				String Fundraising=str.split("</span>")[1].split("</li>")[0].split("USD")[0].replaceAll(",", "").trim();
				pro.setICO_HardCap(Fundraising);
			}else if(str.contains("Sold on pre-sale")) {
				String Soldonpresale=str.split("</span>")[1].split("</li>")[0];
			}else if(str.contains("Total Tokens")) {
				String icototalcount=str.split("</span>")[1].split("</li>")[0].replaceAll(",", "");
				pro.setICO_TotalCount(icototalcount);
			}else if(str.contains("Available for Token Sale")) {
				String AvailableforTokenSale=str.split("</span>")[1].split("</li>")[0];
				pro.setAvailableforTokenSale(AvailableforTokenSale);
			}else if(str.contains("Know Your Customer")) {
				String KnowYourCustomer=str.split("</span>")[1].split("</li>")[0];
				
			}else if(str.contains("Whitelist")) {
				String Whitelist=str.split("</span>")[1].split("</li>")[0];
				if(Whitelist.contains("PERIOD ISN\'T SET")) {
					Whitelist="YES";
				}
				pro.setWhitelist(Whitelist);
			}else if(str.contains("Min/Max Personal Cap")) {
				String MinMaxPersonalCap=str.split("</span>")[1].split("</li>")[0];
			}else if(str.contains("Token Issue")) {
				String TokenIssue=str.split("</span>")[1].split("</li>")[0];
				
			}else if(str.contains("Accepts")) {
				String Accepts=str.split("</span>")[1].split("</li>")[0];
				pro.setPlatform(Accepts);
			}else if(str.contains("Сan't participate:")) {
				String cannotareas=str.split("</span>")[1].split("</li>")[0];
				pro.setCannotareas(cannotareas);
			}
		}
		List<String> links=html.xpath("//div[@class='ico-right-col']").links().all();
		int i=0;
		String icorasiemoney=html.xpath("//div[@class='blue money-goal']/text()").toString().replace("$", "").replaceAll(",", "");
		String raisehtml=html.xpath("//div[@class='goal']").toString();
		if(raisehtml.contains("RECEIVED")) {
			String raisemoney="RECEIVED";
		}else if(raisehtml.contains("OF")) {
			String raisemoney=raisehtml.replace("OF <br>", "").split("\\(")[0].replaceAll(",", "");
		}
		for(String linkstr:links) {
			if(linkstr.contains("?utm_source=icodrops")) {
				String website=linkstr;
				pro.setWebsite(website);
				System.out.println(linkstr);
			}else if(i==1) {
				String whitepaper=linkstr;
				pro.setWhitepaper(whitepaper);
			}else if(linkstr.contains("facebook")) {
				String Facebookurl=linkstr;
				pro.setFacebookurl(Facebookurl);
			}else if(linkstr.contains("reddit")) {
				String redditurl=linkstr;
				
			}else if(linkstr.contains("facebook")) {
				String Facebookurl=linkstr;
				pro.setFacebookurl(Facebookurl);
			}else if(linkstr.contains("github.com")) {
				String Github_url=linkstr;
				pro.setGithuburl(Github_url);
			}else if(linkstr.contains("twitter.com")) {
				String Twitterurl=linkstr;
				pro.setTwitterurl(Twitterurl);
			}else if(linkstr.contains("twitter.com")) {
				String Twitterurl=linkstr;
				pro.setTwitterurl(Twitterurl);
			}else if(linkstr.contains("t.me")) {
				String telegramurl=linkstr;
				pro.setTelegramurl(telegramurl);
			}else if(linkstr.contains("linkedin.com")) {
				String linkedinurl=linkstr;
				pro.setLinkedin(linkedinurl);
			}
			i++;
		}
		System.out.println(1);
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}

}
