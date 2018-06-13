import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
import java.io.*;
import java.sql.Array;
import java.util.List;
public  class Cstone implements PageProcessor{
	public static void main(String[] args) {
		Spider.create(new Cstone()).addUrl("http://www.c-stone.com/product/stone.html").run();
		
	}
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		Selectable selectable=html.xpath("//div [@class=\"all-list product-list\"]");
		List<String> selectable1=selectable.xpath("//a").all();
		List<String> links=selectable.regex("/product/detail/id/[\\w\\-]+.html'").all();
		List<String> img=selectable.regex("http://7xjm3j.com1.z0.glb.clouddn.com/[\\w\\-]+").all();
		List<String> price=selectable.xpath("//div [@class=\"i-footer\"]").xpath("//span").all();
		System.out.println(selectable);
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}

}
