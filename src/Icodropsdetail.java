import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

public class Icodropsdetail implements PageProcessor {
	public static void main(String[] args) {
		Spider.create(new Icodropsdetail()).addUrl("https://icodrops.com/omnitude/").run();
	}
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		Html html=page.getHtml();
		String name=html.xpath("//h3/text()").toString();
		List<String> logohtml=html.xpath("//meta").xpath("[@name='twitter:image']").all();
		String logo=logohtml.get(1).toString().split("content=\"")[1].split("\"")[0];
		String datehtml=html.xpath("//div[@class=\"col-12 title-h4\"]").xpath("//h4/text()").toString().replace("Token Sale:", "");
		String startdate=datehtml.split("–")[0].trim();
		String enddate=datehtml.split("–")[1].trim();
		String description=html.xpath("//div[@class='ico-description']/text()").toString().trim();
		List<String> lilist=html.xpath("//div[@class='row list']").xpath("//li").all();
		for(String str:lilist) {
			if(str.contains("Ticker")) {
				String ticker=str.split("</span>")[1].split("</li>")[0];
			}else if(str.contains("Token type")) {
				String type=str.split("</span>")[1].split("</li>")[0];
			}else if(str.contains("ICO Token Price")) {
				String ICOTokenPrice=str.split("</span>")[1].split("</li>")[0];
			}else if(str.contains("Fundraising Goal")) {
				String Fundraising=str.split("</span>")[1].split("</li>")[0];
			}else if(str.contains("Sold on pre-sale")) {
				String Soldonpresale=str.split("</span>")[1].split("</li>")[0];
			}else if(str.contains("Total Tokens")) {
				String icototalcount=str.split("</span>")[1].split("</li>")[0];
			}else if(str.contains("Available for Token Sale")) {
				String AvailableforTokenSale=str.split("</span>")[1].split("</li>")[0];
			}else if(str.contains("Whitelist")) {
				String Whitelist=str.split("</span>")[1].split("</li>")[0];
			}else if(str.contains("Know Your Customer")) {
				String KnowYourCustomer=str.split("</span>")[1].split("</li>")[0];
			}else if(str.contains("Whitelist")) {
				String Whitelist=str.split("</span>")[1].split("</li>")[0];
			}else if(str.contains("Min/Max Personal Cap")) {
				String MinMaxPersonalCap=str.split("</span>")[1].split("</li>")[0];
			}else if(str.contains("Token Issue")) {
				String TokenIssue=str.split("</span>")[1].split("</li>")[0];
			}else if(str.contains("Accepts")) {
				String Accepts=str.split("</span>")[1].split("</li>")[0];
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
				System.out.println(linkstr);
			}else if(i==1) {
				String whitepaper=linkstr;
			}else if(linkstr.contains("facebook")) {
				String Facebookurl=linkstr;
			}else if(linkstr.contains("reddit")) {
				String redditurl=linkstr;
			}else if(linkstr.contains("facebook")) {
				String Facebookurl=linkstr;
			}else if(linkstr.contains("github.com")) {
				String Github_url=linkstr;
			}else if(linkstr.contains("twitter.com")) {
				String Twitterurl=linkstr;
			}else if(linkstr.contains("twitter.com")) {
				String Twitterurl=linkstr;
			}else if(linkstr.contains("t.me")) {
				String telegramurl=linkstr;
			}else if(linkstr.contains("linkedin.com")) {
				String linkedinurl=linkstr;
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
