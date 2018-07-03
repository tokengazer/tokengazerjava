import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.selector.Selectable;
import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;




public  class GetCoinlistFromCryptocompare implements PageProcessor{
	public static void main(String[] args) {
		Spider.create(new GetCoinlistFromCryptocompare()).addUrl("https://www.cryptocompare.com/api/data/coinlist/").run();
		
	}
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		String html = page.getRawText();
		CoinList coinList = JSON.parseObject(html, new TypeReference<CoinList>(){});
		
         Set<String> set=coinList.getData().keySet();
         ArrayList<Request> list=new ArrayList<Request>();
         for(String key:set) {
        	 DataContainer data=coinList.getData().get(key);
        	int id= Integer.parseInt(data.getId());
        	String url="https://www.cryptocompare.com/api/data/coinsnapshotfullbyid/?id="+id+"&ticker="+data.getSymbol()+"&name="+data.getCoinName();
        	Request request=new Request(url);
        	list.add(request);
        	
         }
         Request[] strings = new Request[list.size()];
         list.toArray(strings);
         Spider.create(new GetCoinList()).addRequest(strings).thread(30).run();
        DataContainer id=new DataContainer();
		String id1=id.Id;
		System.out.print(id1);
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}
	public static class CoinList implements PageProcessor{
        private Map<String, DataContainer> Data;

        public Map<String, DataContainer> getData() {
            return Data;
        }

        public void setData(Map<String, DataContainer> data) {
            Data = data;
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

    public static class DataContainer{
        private String Id;
        private String Name;
        private String Symbol;
        public String getSymbol() {
			return Symbol;
		}

		public void setSymbol(String symbol) {
			Symbol = symbol;
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		private String CoinName;
        public String getCoinName() {
			return CoinName;
		}

		public void setCoinName(String coinName) {
			CoinName = coinName;
		}

		public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }
    }

}
