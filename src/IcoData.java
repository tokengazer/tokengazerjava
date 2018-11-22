import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

public class IcoData implements PageProcessor {
	private Site site = new Site().setRetryTimes(3).setSleepTime(100).setDomain("www.icorating.com");
	public static void main(String[] args) {
		active();
		//upcoming();
		//whitelist();
		//ended();
		}
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		Html html=page.getHtml();
		Selectable tbody=html.xpath("//tbody");
		List<String> linklist=tbody.links().all();
		ArrayList<Request> list=new ArrayList<Request>();
	       for(String url:linklist) {
	    	   Request request=new Request(url);
	    	   request.addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
	   		request.addHeader(":authority", "www.icodata.io");
	   		request.addHeader("accept-language", "zh-CN,zh;q=0.9");
	   		request.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36");
	   		request.addCookie("XSRF-TOKEN", "eyJpdiI6Ikd0YzZoWTJzUXBpajdBU2g5YzcrMkE9PSIsInZhbHVlIjoiQVN2TVpLeEk4TUJBMzRxeEtWMEFsdTNKQWNwamtHYlZQVUduVWM0UjhkTDRDeHBOYTFcL3E0RXlIUTJ3aFVUQXBTOFMwMFRpbDFnSllvUEhRUWVHMHR3PT0iLCJtYWMiOiJiZTE5ODE1MTY3ZjgwMzI2MmYzNDU5OTQzMTMzN2U0ZDM3YzhiOGY0YWY0OWI5ZDUyZjc3ZmQzOGI4YjZjNmQ1In0");
	   		request.addCookie("laravel_session", "eyJpdiI6InZNZENvSHRmRGdCeTBha3FzMUo2RFE9PSIsInZhbHVlIjoiYW56a2hmTkQyRjN2Zk5NcVhQdVRMR2NmYk9IZ0VuTVNKeFpuVWdRenV6bndrMXEyRkdtZlZ6eGF1bkNBNnkwbTZwUkFoZzRLY0Y1eXpXZHpsT1wvRXZnPT0iLCJtYWMiOiJjZjA0NmIwYjM3OTUyY2JmYTk1MTU0M2RhY2Y0NzhkYmYyYTJhMTc0MjZlMTIzNzg4OGRjNzAyOWI4ZWFjMjI2In0");
	   		
	    	   list.add(request);
	    	   //Spider.create(new Icodropsdetail()).addUrl(url).run();
	       }
	       Request[] strings = new Request[list.size()];
	       list.toArray(strings);
	       Spider.create(new IcoDatadetail()).addRequest(strings).thread(10).run();
		//System.out.println(html);
	}
	
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}

	public static void active() {
		getlist("active");
	}
	public static void upcoming() {
		getlist("upcoming");
	}
	public static void whitelist() {
		getlist("whitelist");
	}
	public static void ended() {
		getlist("ended");
	}
	public static void getlist(String stage) {
		String url="https://www.icodata.io/"+stage;
		Request request=new Request(url);
		request.setMethod("Get");
		Map<Object, Object> nameValuePair = new HashMap<Object, Object>();
		nameValuePair.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		nameValuePair.put(":authority", "www.icodata.io");
		request.addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		request.addHeader(":authority", "www.icodata.io");
		request.addHeader("accept-language", "zh-CN,zh;q=0.9");
		request.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36");
		request.addCookie("XSRF-TOKEN", "eyJpdiI6Ikd0YzZoWTJzUXBpajdBU2g5YzcrMkE9PSIsInZhbHVlIjoiQVN2TVpLeEk4TUJBMzRxeEtWMEFsdTNKQWNwamtHYlZQVUduVWM0UjhkTDRDeHBOYTFcL3E0RXlIUTJ3aFVUQXBTOFMwMFRpbDFnSllvUEhRUWVHMHR3PT0iLCJtYWMiOiJiZTE5ODE1MTY3ZjgwMzI2MmYzNDU5OTQzMTMzN2U0ZDM3YzhiOGY0YWY0OWI5ZDUyZjc3ZmQzOGI4YjZjNmQ1In0");
		request.addCookie("laravel_session", "eyJpdiI6InZNZENvSHRmRGdCeTBha3FzMUo2RFE9PSIsInZhbHVlIjoiYW56a2hmTkQyRjN2Zk5NcVhQdVRMR2NmYk9IZ0VuTVNKeFpuVWdRenV6bndrMXEyRkdtZlZ6eGF1bkNBNnkwbTZwUkFoZzRLY0Y1eXpXZHpsT1wvRXZnPT0iLCJtYWMiOiJjZjA0NmIwYjM3OTUyY2JmYTk1MTU0M2RhY2Y0NzhkYmYyYTJhMTc0MjZlMTIzNzg4OGRjNzAyOWI4ZWFjMjI2In0");
		//request.setRequestBody((HttpRequestBody.json(JSON.toJSONString(nameValuePair), "UTF-8")));
		Spider.create(new IcoData()).addRequest(request).run();
	}
}
