package com.aclass.mongo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import com.aclass.daum.Channel;
import com.aclass.daum.Item;
import com.aclass.news.*;
@Component
public class MusicManager {
	public String daumReview(String title){
		   StringBuffer sb=new StringBuffer();
		   try {
			   String key="9820e72e62604e2c319b1321b7de301d";
			   String strUrl="https://apis.daum.net/search/blog?output=xml&result=20&pageno=3"
			                +"&q="+URLEncoder.encode(title, "UTF-8")+"&apikey="+key;
			   URL url=new URL(strUrl);
			   JAXBContext jc=JAXBContext.newInstance(Channel.class);
			   Unmarshaller un=jc.createUnmarshaller();
			   Channel channel=(Channel)un.unmarshal(url);
			   List<Item> list=channel.getItem();
			   for(Item item:list) {
				   sb.append(item.getDescription()+"\n");
			   }
		} catch (Exception ex) {
			System.out.println("daumReview : "+ex.getMessage());
		}
		   return sb.toString();
	   }
	   public void naverReviewFileSave(String title){
		   StringBuffer sb=new StringBuffer();
		   String clientId = "5985WeuqC2kMdJplYe9_";//애플리케이션 클라이언트 아이디값";
	       String clientSecret = "KVL8liFyya";//애플리케이션 클라이언트 시크릿값";
	       try {
	           String text = URLEncoder.encode(title, "UTF-8");
	          // String apiURL = "https://openapi.naver.com/v1/search/blog?query="+ text; // json 결과
	           String apiURL = "https://openapi.naver.com/v1/search/blog.xml?display=100&query="+ text; // xml 결과 //100개가지고옴
	           URL url = new URL(apiURL);
	           HttpURLConnection con = (HttpURLConnection)url.openConnection();
	           con.setRequestMethod("GET");
	           con.setRequestProperty("X-Naver-Client-Id", clientId);
	           con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	           int responseCode = con.getResponseCode();
	           BufferedReader br;
	           if(responseCode==200) { // 정상 호출
	               br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	           } else {  // 에러 발생
	               br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	           }
	           String inputLine;
	           StringBuffer response = new StringBuffer();
	           while ((inputLine = br.readLine()) != null) {
	               response.append(inputLine);
	           }
	           br.close();
	           
	           FileWriter fw=new FileWriter("/home/sist/feel-data/naver_review.xml");
	           fw.write(response.toString());
	           fw.close();
	       } catch (Exception e) {
	           System.out.println(e);
	       }
	   }
	   public String naverReview(){
		   StringBuffer sb=new StringBuffer();
		   try
		   	 {
		   		 JAXBContext jc=JAXBContext.newInstance(Rss.class);
		   		 Unmarshaller un=jc.createUnmarshaller();
		   		 File file=new File("/home/sist/feel-data/naver_review.xml");
		   		 Rss rss=(Rss)un.unmarshal(file);
		   		 List<com.aclass.news.Item> list=rss.getChannel().getItem();
		   		 for(com.aclass.news.Item item:list)
		   		 {
		   			 sb.append(item.getDescription()+"\n");
		   		 }
		   	 }catch(Exception ex)
		   	 {
		   		 System.out.println(ex.getMessage());
		   	 }
		   return sb.toString();
	   }
	   public void reviewData(String title){
		   String data=daumReview(title)+"\n";
		   naverReviewFileSave(title);
		   data+=naverReview();
		   try {
			   File file=new File("/home/sist/feel-data/review.txt");
			   if(file.exists())
				   file.delete();
			   
			   FileWriter fw=new FileWriter("/home/sist/feel-data/review.txt");
			   fw.write(data);
			   fw.close();
		} catch (Exception ex) {
			System.out.println("reviewData : "+ex.getMessage());
		}
	   }
}
