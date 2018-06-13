import java.util.List;
import java.util.Map;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

public class GetCookie implements PageProcessor{
public static String XSRFTOKEN="";
public static String icoratingsession="";
private Site site = new Site().setRetryTimes(3).setSleepTime(100).setDomain("www.icorating.com");
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Html html = page.getHtml();
				XSRFTOKEN=html.xpath("//meta[@name=\"csrf-token\"]").replace("<meta name=\"csrf-token\" content=\"", "").replace("\">","").toString();
				//System.out.println(page.getHeaders());
				Selectable selectable=html.xpath("//div[@id='main_table']");
				Selectable selectable1=selectable.xpath("//tbody");
				Map<String, List<String>> headers=page.getHeaders();
				Object[]  setcookie=headers.get("Set-Cookie").toArray();
				String[] tmp=setcookie[0].toString().split("=|;");
			 //XSRFTOKEN=tmp[1];
			 System.out.println(setcookie);
				String[] tmp1=setcookie[1].toString().split("=|;");
				 icoratingsession=tmp1[1];
				
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}
public static void main(String[] args) {
	Spider.create(new GetCookie()).addUrl("https://icorating.com/ico/").run();
}
}
