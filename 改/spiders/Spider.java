package com.rain.spiders;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.rain.dao.BookDao;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

/**
 * 
 * 爬虫类
 *
 */
public class Spider {

    //private FoodDao foodDao = new FoodDao();

    private BookDao bookDao = null;
    
    public Spider(){
    	bookDao = new BookDao();
    }

    /**
     * 抓取链接信息
     * @param page 页数
     * @throws IOException
     */
    public void startLj(Integer page) throws IOException{
    	
    	String agent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.10 Safari/537.36";
    	
    	String baseUri = "https://nj.fang.lianjia.com/loupan/pg";
    	String next_url = baseUri + page;
    			
    	Document doc = Jsoup.connect(next_url)
    			 .userAgent(agent)
    			 .timeout(3000)
    			 .get();
    	
    	Elements descs = doc.getElementsByClass("resblock-desc-wrapper");
    	for(Element element : descs){
    		Element names = element.select(".resblock-name>a").first();
    		String href = "https://nj.fang.lianjia.com"+names.attr("href");
    		String title = names.text();
    		Element areas = element.select(".resblock-area>span").first();
    		String area = areas.text().substring(areas.text().indexOf("-")+1).replace("㎡", "");
    		if (area == null || "".equals(area))
    			continue;
    		//System.out.println(area);
    		
    		Element price = element.select(".main-price>span").first();
    		//System.out.println(price.text());
    		System.out.println(title+","+area+","+href+","+price.text());
    		bookDao.addBook("链家", title, area, href, "", Integer.parseInt(price.text()));
    	}
    }
    
    /**
     * 抓取房天下信息
     * @param page 页数
     * @throws IOException
     */
    public void startFtx(Integer page) throws IOException{
    	
    	
    	String agent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.10 Safari/537.36";
    	
    	String baseUri = "https://nanjing.newhouse.fang.com/house/s/b9";
    	String next_url = baseUri + page;
    			
    	Document doc = Jsoup.connect(next_url)
    			 .userAgent(agent)
    			 .timeout(3000)
    			 .get();
    	
    	Elements descs = doc.getElementsByClass("nlc_details");
    	for(Element element : descs){
    		try{
    			Element names = element.select(".nlcd_name>a").first();
        		String href = "https:"+names.attr("href");
        		String title = names.text();
        		Element areas = element.select(".house_type").first();
        		String area = areas.text().substring(areas.text().indexOf("~")+1).replace("平米", "");
        		if (area == null || "".equals(area))
        			continue;
        		//System.out.println(area);
        		Integer price = Integer.parseInt(element.select(".nhouse_price>span").first().text());
        		if("价格待定".equals(price))
        			continue;
        		System.out.println(title+","+area+","+href+","+price);
        		bookDao.addBook("房天下", title, area, href, "", price);
    		}catch (Exception e) {
				System.out.println(e);
			}
    	}
    }

}
