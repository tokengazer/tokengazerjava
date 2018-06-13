import java.io.*;
import java.sql.Array;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
public  class Telegram {
	public static int App_api_id=220865;
	public static String App_api_hash="679fed61d1930a804eb6d153677792f3";
	public static String token="608015553:AAExwq5eLT7Uwy_qWnZ878MMPb-0Y1F-LJ8";
	public static void main(String[] args) {
		String url="https://api.telegram.org/bot"+token+"/getMessage?id=hashgraphnews";
		String body = "{}";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpget = new HttpGet(url);
            
            HttpResponse response = null;
			try {
				response = httpclient.execute(httpget);
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            HttpEntity entity = response.getEntity();
            try {
				body = EntityUtils.toString(entity);
			} catch (org.apache.http.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        System.out.println(body);
	}
	

}
