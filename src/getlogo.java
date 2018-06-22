import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.downloader.*;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.processor.PageProcessor;

public class getlogo implements PageProcessor {

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		String url=page.getRequest().getUrl();
		String[] splitlist=url.split("/");
		int c=splitlist.length;
		url=splitlist[c-1];
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
		String uri="C:\\Users\\jack\\Desktop\\logos\\"+url;
		try {
		URL url1 = new URL(page.getRequest().getUrl());  
        DataInputStream dataInputStream = new DataInputStream(url1.openStream());  
        String imageName = url;  
        File file=new File(uri);    //ÉèÖÃÏÂÔØÂ·¾¶  
        if(!file.exists()){  
            file.createNewFile();  
        }  
        FileOutputStream fileOutputStream = new FileOutputStream(new File(uri.replaceAll("\\\\", "/")));  
        byte[] buffer = new byte[1024];  
        int length;  
        while ((length = dataInputStream.read(buffer)) > 0) {  
            fileOutputStream.write(buffer, 0, length);  
        }  
        dataInputStream.close();  
        fileOutputStream.close();  
		}
		//catch(FileNotFoundException e) {
			
		//}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}

}
