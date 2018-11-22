import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

public class IcoDatadetail implements PageProcessor {

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		Html html=page.getHtml();
		Project pro=new Project();
		String logohtml=html.xpath("//div[@class='coin-img']").xpath("//img").toString().split("src=\"")[1].split("\"")[0];
		pro.setLogo(logohtml);
		String NameHtml=html.xpath("//h1[@class='coin-name']/text()").toString().trim();
		pro.setName(NameHtml);
		String DescriptionHtml=html.xpath("//div[@class='other-info']").xpath("//p/text()").toString().trim();
		pro.setDescription(DescriptionHtml);
		String WebSite=html.xpath("//a[@class='website']").links().toString();
		pro.setWebsite(WebSite);
		System.out.println(html);
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}

}
