import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
public  class Hellowworld implements PageProcessor{

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    private static int count =0;
    public  static String url;


    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
    	//System.setProperty("https.protocols", "TLSv1.2");
    	
        // 部分二：定义如何抽取页面信息，并保存下来
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }
        
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
        Selectable selectable=page.getHtml().xpath("//div[@id='main_table']");
        System.out.println(selectable);
        // 部分三：从页面发现后续的url地址来抓取
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
        count ++;
    }

    @Override
    public Site getSite() {
        return site;
    }
    public void SetUrl(String Url) {
        url=Url;
    }

    public static void main(String[] args) {
    	/*System.out.println(System.getProperty("user.home"));  
    	  System.out.println(System.getProperty("java.version"));  
    	  System.out.println(System.getProperty("os.name"));  
    	  System.out.println(System.getProperty("java.vendor.url"));*/
    	  //System.setProperty("https.protocols", "TLSv1");
    	
    	}
    public  static void gethtml() {
    	long startTime, endTime;
    startTime = System.currentTimeMillis();
   	 String url=new Hellowworld().url;
   	 System.out.println(url);
Spider.create(new Hellowworld())
               //从"https://github.com/code4craft"开始抓
               .addUrl(url)
               //开启5个线程抓取
               .thread(5)
               //启动爬虫
               .run();
endTime = System.currentTimeMillis();
       System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒，抓取了"+count+"条记录");
   
    }
}