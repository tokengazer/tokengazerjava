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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mysql.jdbc.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;










public  class GetCoinList implements PageProcessor{
	public static void main(String[] args) {
		//Spider.create(new GetCoinList()).addUrl("https://www.cryptocompare.com/api/data/coinlist/").run();
		
	}
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		String html = page.getRawText();
		String url=page.getRequest().getUrl();
		String id=url.split("id=")[1].split("&")[0];
		String ticker=url.split("ticker=")[1].split("&")[0];
		CoinInfo icoInfo = JSON.parseObject(html.toString(), new TypeReference<CoinInfo>(){});
		String name1=url.split("name=")[1];
		String sql="select *  from ico_Analysis where ticker='"+ticker+"' and name='"+name1+"';";
		Connection con=null;
		String driver = "com.mysql.jdbc.Driver";

        String sqlurl = "jdbc:mysql://rm-bp1kq68i5h4493twvzo.mysql.rds.aliyuncs.com:3306/app_tokenworm";

        String user = "lybjx";

        String password = "Lybjx54709488dh";
        try {
			con=DriverManager.getConnection(sqlurl, user, password);
			Statement stat=con.createStatement();
			ResultSet rs=stat.executeQuery(sql);
			int rowCount = 0; 
			if(rs.next()) { 
			  rowCount+=1; 
			}
			con.close();
			String enddate=icoInfo.getData().getICO().getEndDate();
			String Name = null;
			String startdate = "";
			String startdt = "";
			String enddt = "";
			String satrtdt = "";
			String icoprice = "";
			String icotokensupply = "";
			String ico_raised_money = "";
			String tokentype = "";
			String Accept = "";
			if(!StringUtils.isNullOrEmpty(enddate)) {
				
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			enddt=sdf.format(new Date(Long.valueOf(enddate.trim()+"000")));
			startdate=icoInfo.getData().getICO().getDate();
			startdt=sdf.format(new Date(Long.valueOf(startdate.trim()+"000")));
			icoprice=icoInfo.getData().getICO().getStartPrice();
			icotokensupply=icoInfo.getData().getICO().getICOTokenSupply();
			ico_raised_money=icoInfo.getData().getICO().getFundingCap();
			tokentype=icoInfo.getData().getICO().getTokenType();
			Accept=icoInfo.getData().getICO().getStartPriceCurrency();
			Name=icoInfo.getData().getGeneral().getName();
			
			
			}
			String insertsql="";
			if(rowCount==0) {
				if(!StringUtils.isNullOrEmpty(enddate)) {
				insertsql="insert into ico_Analysis (id,name,ticker,ICO_Price_Usd,Ico_time,Ico_endtime,ICO_Total_Amount,ICO_Raised_money,TokenType,AcceptedCurrencies) values (null,'"+Name+"','"+ticker+"','"+icoprice+"','"+startdt+"','"+enddt+"','"+icotokensupply+"','"+ico_raised_money+"','"+tokentype+"','"+Accept+"')";
				}
			}else {
				
				if(!StringUtils.isNullOrEmpty(enddate)) {
					String updatesql="set ticker='"+ticker+"'";
					if(icoprice!="") {
						updatesql+=",ICO_Price_Usd='"+icoprice+"'";
					}if(startdt!="") {
						updatesql+=",Ico_time='"+startdt+"'";
					}if(enddt!="") {
						updatesql+=",Ico_endtime='"+enddt+"'";
					}if(icotokensupply!="") {
						updatesql+=",ICO_Total_Amount='"+icotokensupply+"'";
					}if(tokentype!="") {
						updatesql+=",TokenType='"+tokentype+"'";
					}if(Accept!="") {
						updatesql+=",AcceptedCurrencies='"+Accept+"'";
					}
				insertsql="update ico_Analysis "+updatesql+" where ticker='"+ticker+"'";
				System.out.println(insertsql);
				}
			}
			con=DriverManager.getConnection(sqlurl, user, password);
			Statement stat1=con.createStatement();
			if(insertsql!="")
			stat1.executeUpdate(insertsql);
			con.close();
			System.out.print(rowCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(icoInfo.getData().getICO().getStatus());
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me();
	}
	public static class CoinInfo{
		private Data Data;

		public Data getData() {
			return Data;
		}

		public void setData(Data data) {
			Data = data;
		}
		
	}
	public static class Data{
		private IcoInfo ICO;
		private Icogeneral General;
		public Icogeneral getGeneral() {
			return General;
		}

		public void setGeneral(Icogeneral general) {
			General = general;
		}

		public IcoInfo getICO() {
			return ICO;
		}

		public void setICO(IcoInfo iCO) {
			ICO = iCO;
		}
	}
	public static class Icogeneral{
		public String Name;
		//Public String 

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}
	}
	public static class IcoInfo {
		
		private String Status;
		private String WhitePaper;
		private String StartPrice;//
		private String TokenReserveSplit;
		private String Blog;
		private String BlogLink;
		private String Date;
		private String Description;
		private String EndDate;//
		private String Features;
		private String FundingCap;//
		private String FundingTarget;
		private String FundsRaisedList;
		private String FundsRaisedUSD;
		private String ICOTokenSupply;//
		private String Jurisdiction;
		private String LegalAdvisers;
		private String LegalForm;
		private String PaymentMethod;
		private String PublicPortfolioId;
		private String PublicPortfolioUrl;
		private String SecurityAuditCompany;
		//private String StartPrice;
		private String StartPriceCurrency;//
		private String ToeknPercentageForInvestors;//
		private String TokenSupplyPostICO;
		private String TokenType;//
		private String Website;
		public String getStatus() {
			return Status;
		}
		public void setStatus(String status) {
			Status = status;
		}
		public String getWhitePaper() {
			return WhitePaper;
		}
		public void setWhitePaper(String whitePaper) {
			WhitePaper = whitePaper;
		}
		public String getStartPrice() {
			return StartPrice;
		}
		public void setStartPrice(String startPrice) {
			StartPrice = startPrice;
		}
		public String getTokenReserveSplit() {
			return TokenReserveSplit;
		}
		public void setTokenReserveSplit(String tokenReserveSplit) {
			TokenReserveSplit = tokenReserveSplit;
		}
		public String getBlog() {
			return Blog;
		}
		public void setBlog(String blog) {
			Blog = blog;
		}
		public String getBlogLink() {
			return BlogLink;
		}
		public void setBlogLink(String blogLink) {
			BlogLink = blogLink;
		}
		public String getDate() {
			return Date;
		}
		public void setDate(String date) {
			Date = date;
		}
		public String getDescription() {
			return Description;
		}
		public void setDescription(String description) {
			Description = description;
		}
		public String getEndDate() {
			return EndDate;
		}
		public void setEndDate(String endDate) {
			EndDate = endDate;
		}
		public String getFeatures() {
			return Features;
		}
		public void setFeatures(String features) {
			Features = features;
		}
		public String getFundingCap() {
			return FundingCap;
		}
		public void setFundingCap(String fundingCap) {
			FundingCap = fundingCap;
		}
		public String getFundingTarget() {
			return FundingTarget;
		}
		public void setFundingTarget(String fundingTarget) {
			FundingTarget = fundingTarget;
		}
		public String getFundsRaisedList() {
			return FundsRaisedList;
		}
		public void setFundsRaisedList(String fundsRaisedList) {
			FundsRaisedList = fundsRaisedList;
		}
		public String getFundsRaisedUSD() {
			return FundsRaisedUSD;
		}
		public void setFundsRaisedUSD(String fundsRaisedUSD) {
			FundsRaisedUSD = fundsRaisedUSD;
		}
		public String getICOTokenSupply() {
			return ICOTokenSupply;
		}
		public void setICOTokenSupply(String iCOTokenSupply) {
			ICOTokenSupply = iCOTokenSupply;
		}
		public String getJurisdiction() {
			return Jurisdiction;
		}
		public void setJurisdiction(String jurisdiction) {
			Jurisdiction = jurisdiction;
		}
		public String getLegalAdvisers() {
			return LegalAdvisers;
		}
		public void setLegalAdvisers(String legalAdvisers) {
			LegalAdvisers = legalAdvisers;
		}
		public String getLegalForm() {
			return LegalForm;
		}
		public void setLegalForm(String legalForm) {
			LegalForm = legalForm;
		}
		public String getPaymentMethod() {
			return PaymentMethod;
		}
		public void setPaymentMethod(String paymentMethod) {
			PaymentMethod = paymentMethod;
		}
		public String getPublicPortfolioId() {
			return PublicPortfolioId;
		}
		public void setPublicPortfolioId(String publicPortfolioId) {
			PublicPortfolioId = publicPortfolioId;
		}
		public String getPublicPortfolioUrl() {
			return PublicPortfolioUrl;
		}
		public void setPublicPortfolioUrl(String publicPortfolioUrl) {
			PublicPortfolioUrl = publicPortfolioUrl;
		}
		public String getSecurityAuditCompany() {
			return SecurityAuditCompany;
		}
		public void setSecurityAuditCompany(String securityAuditCompany) {
			SecurityAuditCompany = securityAuditCompany;
		}
		public String getStartPriceCurrency() {
			return StartPriceCurrency;
		}
		public void setStartPriceCurrency(String startPriceCurrency) {
			StartPriceCurrency = startPriceCurrency;
		}
		public String getToeknPercentageForInvestors() {
			return ToeknPercentageForInvestors;
		}
		public void setToeknPercentageForInvestors(String toeknPercentageForInvestors) {
			ToeknPercentageForInvestors = toeknPercentageForInvestors;
		}
		public String getTokenSupplyPostICO() {
			return TokenSupplyPostICO;
		}
		public void setTokenSupplyPostICO(String tokenSupplyPostICO) {
			TokenSupplyPostICO = tokenSupplyPostICO;
		}
		public String getTokenType() {
			return TokenType;
		}
		public void setTokenType(String tokenType) {
			TokenType = tokenType;
		}
		public String getWebsiteSite() {
			return Website;
		}
		public void setWebsiteSite(String websiteSite) {
			Website = websiteSite;
		}
		
		
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
        private Json ICO;
        public Json getICO() {
			return ICO;
		}

		public void setICO(Json iCO) {
			ICO = iCO;
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
