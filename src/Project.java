import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Project {
	public int id;
	/**
	 * @name
	 * 项目名称
	 */
	public String name;
	public String ticker;
	public String Prestartdate;
	public String Preenddate;
	public String logo;
	public String description;
	public String industry;
	public String website;
	public String whitepaper;
	public String whitelist;
	public String githuburl;
	public String twitterurl;
	public String facebookurl;
	public String telegramurl;
	public String Mvp;
	public String ZHname;
	public String stage;
	public String DataSource;
	public String Alreadyon;
	public String Current_market_value;
	public String Volumn;
	public String Current_Single_Price;
	public String Current_Circulation;
	public String coinmarketrank;
	public String Total_Count;
	public String perhour;
	public String perday;
	public String perweek;
	public int Twitter_Fanscount;
	public int Facebook_Friends;
	public int Telegram_fans;
	public int GithubCommits;
	public int GithubStars;
	public int GithubWatchers;
	public int Githubforks;
	public String Github_lastupdatetime;
	public String getIco_start_time() {
		return Ico_start_time;
	}
	public void setIco_start_time(String ico_start_time) {
		Ico_start_time = ico_start_time;
	}
	public String getIco_end_time() {
		return Ico_end_time;
	}
	public void setIco_end_time(String ico_end_time) {
		Ico_end_time = ico_end_time;
	}
	public String Ico_time;
	public String Ico_start_time;
	public String Ico_end_time;
	public String Ico_Price_Usd;
	public String Ico_Price_Eth;
	public String ICO_Distribution_Ratio;
	public String Presales;
	public String ICO_Total_Amount;
	public String ICO_TotalCount;
	public String ICO_HardCap;
	public String ICO_Raise_money;
	public String ICO_Raised_money;
	public String presaledate;
	public String presaleprice;
	public String origin;
	public String cannotareas;
	public String Platform;
	public String icolink;
	public String linkedin;
	public String AcceptedCurrencies;
	public String TokenStandard;
	public String TokenType;
	public String TokenDistribution;
	public String FundsAllocation;
	public String AdditionalTokenEmission;
	public String AvailableforTokenSale;
	
	
	
	
	public String getZHname() {
		return ZHname;
	}
	public void setZHname(String zHname) {
		ZHname = zHname;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getDataSource() {
		return DataSource;
	}
	public void setDataSource(String dataSource) {
		DataSource = dataSource;
	}
	public String getAlreadyon() {
		return Alreadyon;
	}
	public void setAlreadyon(String alreadyon) {
		Alreadyon = alreadyon;
	}
	public String getCurrent_market_value() {
		return Current_market_value;
	}
	public void setCurrent_market_value(String current_market_value) {
		Current_market_value = current_market_value;
	}
	public String getVolumn() {
		return Volumn;
	}
	public void setVolumn(String volumn) {
		Volumn = volumn;
	}
	public String getCurrent_Single_Price() {
		return Current_Single_Price;
	}
	public void setCurrent_Single_Price(String current_Single_Price) {
		Current_Single_Price = current_Single_Price;
	}
	public String getCurrent_Circulation() {
		return Current_Circulation;
	}
	public void setCurrent_Circulation(String current_Circulation) {
		Current_Circulation = current_Circulation;
	}
	public String getCoinmarketrank() {
		return coinmarketrank;
	}
	public void setCoinmarketrank(String coinmarketrank) {
		this.coinmarketrank = coinmarketrank;
	}
	public String getTotal_Count() {
		return Total_Count;
	}
	public void setTotal_Count(String total_Count) {
		Total_Count = total_Count;
	}
	public String getPerhour() {
		return perhour;
	}
	public void setPerhour(String perhour) {
		this.perhour = perhour;
	}
	public String getPerday() {
		return perday;
	}
	public void setPerday(String perday) {
		this.perday = perday;
	}
	public String getPerweek() {
		return perweek;
	}
	public void setPerweek(String perweek) {
		this.perweek = perweek;
	}
	public int getTwitter_Fanscount() {
		return Twitter_Fanscount;
	}
	public void setTwitter_Fanscount(int twitter_Fanscount) {
		Twitter_Fanscount = twitter_Fanscount;
	}
	public int getFacebook_Friends() {
		return Facebook_Friends;
	}
	public void setFacebook_Friends(int facebook_Friends) {
		Facebook_Friends = facebook_Friends;
	}
	public int getTelegram_fans() {
		return Telegram_fans;
	}
	public void setTelegram_fans(int telegram_fans) {
		Telegram_fans = telegram_fans;
	}
	public int getGithubCommits() {
		return GithubCommits;
	}
	public void setGithubCommits(int githubCommits) {
		GithubCommits = githubCommits;
	}
	public int getGithubStars() {
		return GithubStars;
	}
	public void setGithubStars(int githubStars) {
		GithubStars = githubStars;
	}
	public int getGithubWatchers() {
		return GithubWatchers;
	}
	public void setGithubWatchers(int githubWatchers) {
		GithubWatchers = githubWatchers;
	}
	public int getGithubforks() {
		return Githubforks;
	}
	public void setGithubforks(int githubforks) {
		Githubforks = githubforks;
	}
	public String getGithub_lastupdatetime() {
		return Github_lastupdatetime;
	}
	public void setGithub_lastupdatetime(String github_lastupdatetime) {
		Github_lastupdatetime = github_lastupdatetime;
	}
	public String getIco_time() {
		return Ico_time;
	}
	public void setIco_time(String ico_time) {
		Ico_time = ico_time;
	}
	public String getIco_Price_Usd() {
		return Ico_Price_Usd;
	}
	public void setIco_Price_Usd(String ico_Price_Usd) {
		Ico_Price_Usd = ico_Price_Usd;
	}
	public String getIco_Price_Eth() {
		return Ico_Price_Eth;
	}
	public void setIco_Price_Eth(String ico_Price_Eth) {
		Ico_Price_Eth = ico_Price_Eth;
	}
	public String getICO_Distribution_Ratio() {
		return ICO_Distribution_Ratio;
	}
	public void setICO_Distribution_Ratio(String iCO_Distribution_Ratio) {
		ICO_Distribution_Ratio = iCO_Distribution_Ratio;
	}
	public String getPresales() {
		return Presales;
	}
	public void setPresales(String presales) {
		Presales = presales;
	}
	public String getICO_Total_Amount() {
		return ICO_Total_Amount;
	}
	public void setICO_Total_Amount(String iCO_Total_Amount) {
		ICO_Total_Amount = iCO_Total_Amount;
	}
	public String getICO_TotalCount() {
		return ICO_TotalCount;
	}
	public void setICO_TotalCount(String iCO_TotalCount) {
		ICO_TotalCount = iCO_TotalCount;
	}
	public String getICO_HardCap() {
		return ICO_HardCap;
	}
	public void setICO_HardCap(String iCO_HardCap) {
		ICO_HardCap = iCO_HardCap;
	}
	public String getICO_Raise_money() {
		return ICO_Raise_money;
	}
	public void setICO_Raise_money(String iCO_Raise_money) {
		ICO_Raise_money = iCO_Raise_money;
	}
	public String getICO_Raised_money() {
		return ICO_Raised_money;
	}
	public void setICO_Raised_money(String iCO_Raised_money) {
		ICO_Raised_money = iCO_Raised_money;
	}
	public String getPresaledate() {
		return presaledate;
	}
	public void setPresaledate(String presaledate) {
		this.presaledate = presaledate;
	}
	public String getPresaleprice() {
		return presaleprice;
	}
	public void setPresaleprice(String presaleprice) {
		this.presaleprice = presaleprice;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getCannotareas() {
		return cannotareas;
	}
	public void setCannotareas(String cannotareas) {
		this.cannotareas = cannotareas;
	}
	public String getPlatform() {
		return Platform;
	}
	public void setPlatform(String platform) {
		Platform = platform;
	}
	public String getIcolink() {
		return icolink;
	}
	public void setIcolink(String icolink) {
		this.icolink = icolink;
	}
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	public String getAcceptedCurrencies() {
		return AcceptedCurrencies;
	}
	public void setAcceptedCurrencies(String acceptedCurrencies) {
		AcceptedCurrencies = acceptedCurrencies;
	}
	public String getTokenStandard() {
		return TokenStandard;
	}
	public void setTokenStandard(String tokenStandard) {
		TokenStandard = tokenStandard;
	}
	public String getTokenType() {
		return TokenType;
	}
	public void setTokenType(String tokenType) {
		TokenType = tokenType;
	}
	public String getTokenDistribution() {
		return TokenDistribution;
	}
	public void setTokenDistribution(String tokenDistribution) {
		TokenDistribution = tokenDistribution;
	}
	public String getFundsAllocation() {
		return FundsAllocation;
	}
	public void setFundsAllocation(String fundsAllocation) {
		FundsAllocation = fundsAllocation;
	}
	public String getAdditionalTokenEmission() {
		return AdditionalTokenEmission;
	}
	public void setAdditionalTokenEmission(String additionalTokenEmission) {
		AdditionalTokenEmission = additionalTokenEmission;
	}
	public String getAvailableforTokenSale() {
		return AvailableforTokenSale;
	}
	public void setAvailableforTokenSale(String availableforTokenSale) {
		AvailableforTokenSale = availableforTokenSale;
	}
	public String getPrestartdate() {
		return Prestartdate;
	}
	public void setPrestartdate(String prestartdate) {
		Prestartdate = prestartdate;
	}
	public String getPreenddate() {
		return Preenddate;
	}
	public void setPreenddate(String preenddate) {
		Preenddate = preenddate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getWhitepaper() {
		return whitepaper;
	}
	public void setWhitepaper(String whitepaper) {
		this.whitepaper = whitepaper;
	}
	public String getWhitelist() {
		return whitelist;
	}
	public void setWhitelist(String whitelist) {
		this.whitelist = whitelist;
	}
	public String getGithuburl() {
		return githuburl;
	}
	public void setGithuburl(String githuburl) {
		this.githuburl = githuburl;
	}
	public String getTwitterurl() {
		return twitterurl;
	}
	public void setTwitterurl(String twitterurl) {
		this.twitterurl = twitterurl;
	}
	public String getFacebookurl() {
		return facebookurl;
	}
	public void setFacebookurl(String facebookurl) {
		this.facebookurl = facebookurl;
	}
	public String getTelegramurl() {
		return telegramurl;
	}
	public void setTelegramurl(String telegramurl) {
		this.telegramurl = telegramurl;
	}
	
	
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static void main(String[] args) {
		
	}
	public String getMvp() {
		return Mvp;
	}
	public void setMvp(String mvp) {
		Mvp = mvp;
	}

	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	
}
