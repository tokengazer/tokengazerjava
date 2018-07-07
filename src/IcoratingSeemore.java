import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.cookie.Cookie;

import com.alibaba.fastjson.JSON;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.utils.HttpConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.Cookie;





public class IcoratingSeemore implements PageProcessor{
	public static String XSRFTOKEN=getcookie();
	 public static String icoratingsession="";
	 public static int isongoingcontinue=1;
	 public static int isupcomingcontinue=1;
	 public static int ispastcontinue=1;
	 public static int ispreicocontinue=1;
	 public static String stage="ongoing";
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
	public static  String getcookie() {
		Page page=new Page();
		Spider.create(new GetCookie()).addUrl("https://icorating.com/ico/").run();
		
		return null;
	}
	public static String getsession(HttpServletRequest request) {
		String key="XSRFTOKEN";
		HttpServletRequest httprequest = (HttpServletRequest)request;
		HttpSession session = httprequest.getSession();
		return session.getAttribute(key).toString();
	}
	public static void setsession(HttpServletRequest request,String value) {
		String key="XSRFTOKEN";
		HttpServletRequest httprequest = (HttpServletRequest)request;
		HttpSession session = httprequest.getSession();
		session.setAttribute(key, value);
		session.setMaxInactiveInterval(120*60);
		return;
	}
	public static void main(String[] args) {
//		Request request =new Request();
//		HttpSession session = ((HttpServletRequest) request).getSession();
//		XSRFTOKEN=session.getAttribute(key).toString();
		//XSRFTOKEN=getsession(null);
				//session.setAttribute(key, value);
		if(XSRFTOKEN == null) {
			getcookie();
			XSRFTOKEN=GetCookie.XSRFTOKEN;
			icoratingsession=GetCookie.icoratingsession;
			//setsession(null,XSRFTOKEN);
			}
		getlist("ongoing",isongoingcontinue);
		stage="upcoming";
		getlist("upcoming",isupcomingcontinue);
		stage="preico";
		getlist("preico",ispreicocontinue);
		stage="past";
		getlist("past",ispastcontinue);
		//new IcoratingSeemore().gethtml();
		
	}
	public static void getlist(String stage,int key) {
		for(int i=0;i<=100;i++) {
			if(key==1) {
		Request request1=new Request("https://icorating.com/ico/load/").addCookie("icoratingsession", icoratingsession).addCookie("XSRFTOKEN", XSRFTOKEN);
		request1.setMethod(HttpConstant.Method.POST);
		//设置POST参数
		Map<Object, Object> nameValuePair = new HashMap<Object, Object>();
		nameValuePair.put("sort", "rating");
		nameValuePair.put("sort_un", "rating");
		nameValuePair.put("filter", stage);
		nameValuePair.put("icoPage", i);
		nameValuePair.put("unassessed", "1");
		nameValuePair.put("_token", XSRFTOKEN);
			
		
		request1.setRequestBody(HttpRequestBody.json(JSON.toJSONString(nameValuePair), "UTF-8"));
// 开始执行
		Spider.create(new IcoratingSeemore()).addRequest(request1).thread(1).run();
			}
		}
		for(int i=0;i<=100;i++) {
			if(key==1) {
		Request request1=new Request("https://icorating.com/ico/load/").addCookie("icoratingsession", icoratingsession).addCookie("XSRFTOKEN", XSRFTOKEN);
		request1.setMethod(HttpConstant.Method.POST);
		//设置POST参数
		Map<Object, Object> nameValuePair = new HashMap<Object, Object>();
		nameValuePair.put("sort", "rating");
		nameValuePair.put("sort_un", "rating");
		nameValuePair.put("filter", stage);
		nameValuePair.put("icoPage", i);
		nameValuePair.put("unassessed", "0");
		nameValuePair.put("_token", XSRFTOKEN);
			
		
		request1.setRequestBody(HttpRequestBody.json(JSON.toJSONString(nameValuePair), "UTF-8"));
// 开始执行
		Spider.create(new IcoratingSeemore()).addRequest(request1).thread(1).run();
			}
		}
	}
	//@Override
	public void process(Page page) {
		
		// TODO Auto-generated method stub
		
		Html html=page.getHtml();
		int Code=page.getStatusCode();
		System.out.println(Code);
		if(Code==419) {
			System.out.println(Code);
			getcookie();
			
			main(null);
		}
//		JSON ht1=page.getRawText().toString().toJSONObject;
		Json jsonhtml=page.getJson();
		//List<String> selectable= jsonhtml.links().all();
		String LIST_URL="https://icorating.com/ico/([a-zA-z0-9]-*)";
		if (page.getUrl().regex(LIST_URL) != null) {
		     List<String> ids=page.getJson().jsonPath("html").regex("<a href=\".*?\"").replace("<a href=\"", "").replace("\"", "").all();
		     System.out.println(ids);
		     if (CollectionUtils.isNotEmpty(ids)) {
		         for (String id : ids) {
		        	 if(id!="https://icorating.com/ico/load/"||id!="https://icorating.com/ico/"){
		        		 //Spider.create(new icoratingmainpage()).addUrl(id).thread(5).run();
		        		 Spider.create(new icoratingdetail()).addUrl(id+"details/").thread(5).run();
		        	 }
		         }
		     }
		 }
		if(page.getJson().jsonPath("last_page.unassessed").toString()=="true") {
			if(stage=="ongogin") {
			isongoingcontinue=0;
			}
			if(stage=="upcoming") {
				isupcomingcontinue=0;
				}
			if(stage=="past") {
				ispastcontinue=0;
				}
			if(stage=="preico") {
				ispreicocontinue=0;
				}
		}
		//page.addTargetRequests(html.links().replace("\\", "").all());
		
//System.out.println(html.links().all());
//Html ht1 = new Html();
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}
	
	public static void gethtml() {
		
		
	}

}
