import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
public  class GetDataFromEtherscan implements PageProcessor{
	public static void main(String[] args) {
		//Spider.create(new GetDataFromEtherscan()).addUrl("http://www.c-stone.com/product/stone.html").run();
		int i=1;
		ArrayList<Request> Li=new ArrayList<Request>();
		for(i=1;i<=20;i++) {
			String url="https://etherscan.io/token/generic-tokenholders2?a=0xaf30d2a7e90d7dc361c8c4585e9bb7d2f6f15bc7&p="+i;
			Request request=new Request(url);
			Li.add(request);
		}
		Request[] strings = new Request[Li.size()];
        Li.toArray(strings);
        Spider.create(new GetDataFromETH()).addRequest(strings).thread(5).run();
	}
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}

}
