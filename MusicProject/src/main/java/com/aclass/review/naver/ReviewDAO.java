package com.aclass.review.naver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;



//몽고디비 project3 - naver 테이블에 네이버 리뷰 인설트 
@Repository
public class ReviewDAO {
	
	private MongoClient mc;
	private DB db;
	private DBCollection dbc2;

	
	
	
	   public static void main(String[] arg)
	   {
		   ReviewDAO dao = new ReviewDAO();
   
		   ReviewManager mgr = new ReviewManager();
		   List<SongVO> list = mgr.songData();
		   
		   try
	         {
	        	   for(SongVO vo :list)
	        	   {
			            	//dao.naverReviewData(vo);  
	        	   }
	              
	        }catch(Exception ex){}   
	   }
	
	
	//제목+가수 주고 네이버 리뷰긁기 
		public void naverReviewData(String song,String singer){
	    	
			SongVO vo = new SongVO();
			
			String clientId = "5985WeuqC2kMdJplYe9_";//빅데이터 마스터 애플리케이션 클라이언트 아이디값";
	        String clientSecret = "KVL8liFyya";//빅데이터 마스터 애플리케이션 클라이언트 시크릿값";
	       
	        
	      try {
	        	  //String song2= vo.getSong()+vo.getSinger();	
	    	  		String song2=song+singer;
	            String text = URLEncoder.encode(song2, "UTF-8");
	           // String apiURL = "https://openapi.naver.com/v1/search/blog?query="+ text; // json 결과
	        
	            String apiURL = "https://openapi.naver.com/v1/search/blog.xml?display=100&query="+text; // xml 결과 //100개가지고옴
	           
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
	            
/*	      //폴더에 저장된 파일이름 전체 다읽기      
	             File ff=new File("/home/sist/music_review_xml");
	             File[] fff=ff.listFiles();
	             for(File f:fff)
	             {
	            	 String s=f.getName();
	            	 System.out.println(s.substring(0,s.lastIndexOf(".")));
	             }
*/	 
	            
	           //엑스엠엘 저장 시작 
	            File dir2=new File("/home/sist/music_review_xml");
	            if(!dir2.exists()){
	            	dir2.mkdirs();
	            }
	            
	            FileWriter fw=new FileWriter("/home/sist/music_review_xml/naver_review"+10101+".xml");
	            fw.write(response.toString());
	            fw.close();

	          //위에나온 엑스엠엘을 텍스트자르기

		   		 JAXBContext jc=JAXBContext.newInstance(Rss.class);
		   		 Unmarshaller un=jc.createUnmarshaller();
		   		 File file=new File("/home/sist/music_review_xml/naver_review"+10101+".xml");
		   		 Rss rss=(Rss)un.unmarshal(file);
		   		 List<Item> list=rss.getChannel().getItem();
		   		 String data="";
		   		 for(Item item:list)
		   		 {
		   			 data+=item.getDescription()+"\n"; //데이터에 리뷰들어가있음 
		   		 }
		   		 vo.setNaverReview(data);
		   		 

		   		
		   		 
		   	//몽고몽고 인설트(노래 클릭할때마다 몽고디비에 다른아이디로 저장된다)
		   		 
		   		mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38",27017)));
				db=mc.getDB("project3");
				//dbc=db.getCollection("song"); // 테이블 지정
				dbc2=db.getCollection("naverClick"); 
				
				
				
	    			BasicDBObject obj=new BasicDBObject();
	    			
	    				obj.put("song", song.trim());
	    				obj.put("singer", singer.trim());
	    				obj.put("review", vo.getNaverReview().trim());
	    				
	    			dbc2.insert(obj);
	    			
	    			 //System.out.println(song2+"=="+vo.getNaverReview());

	        }catch (Exception e) {
	            System.out.println(e);
	        }   
	      
		}
		
		//제목+가수 주고 네이버 리뷰긁기 
		public void naverReviewData2(String song,String singer){
	    	
			SongVO vo = new SongVO();
			
			String clientId = "5985WeuqC2kMdJplYe9_";//빅데이터 마스터 애플리케이션 클라이언트 아이디값";
	        String clientSecret = "KVL8liFyya";//빅데이터 마스터 애플리케이션 클라이언트 시크릿값";
	       
	        
	      try {
	        	  //String song2= vo.getSong()+vo.getSinger();	
	    	  		String song2=song+singer;
	            String text = URLEncoder.encode(song2, "UTF-8");
	           // String apiURL = "https://openapi.naver.com/v1/search/blog?query="+ text; // json 결과
	        
	            String apiURL = "https://openapi.naver.com/v1/search/cafearticle.xml?display=100&query="+text; // xml 결과 //100개가지고옴
	           
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
	            
/*	      //폴더에 저장된 파일이름 전체 다읽기      
	             File ff=new File("/home/sist/music_review_xml");
	             File[] fff=ff.listFiles();
	             for(File f:fff)
	             {
	            	 String s=f.getName();
	            	 System.out.println(s.substring(0,s.lastIndexOf(".")));
	             }
*/	 
	            
	           //엑스엠엘 저장 시작 
	            File dir2=new File("/home/sist/music_review_xml2");
	            if(!dir2.exists()){
	            	dir2.mkdirs();
	            }
	            
	            FileWriter fw=new FileWriter("/home/sist/music_review_xml2/naver_review"+10101+".xml");
	            fw.write(response.toString());
	            fw.close();

	          //위에나온 엑스엠엘을 텍스트자르기

		   		 JAXBContext jc=JAXBContext.newInstance(Rss.class);
		   		 Unmarshaller un=jc.createUnmarshaller();
		   		 File file=new File("/home/sist/music_review_xml2/naver_review"+10101+".xml");
		   		 Rss rss=(Rss)un.unmarshal(file);
		   		 List<Item> list=rss.getChannel().getItem();
		   		 String data="";
		   		 for(Item item:list)
		   		 {
		   			 data+=item.getDescription()+"\n"; //데이터에 리뷰들어가있음 
		   		 }
		   		 vo.setNaverReview(data);
		   		 

		   		
		   		 
		   //몽고몽고 인설트(노래 클릭할때마다 몽고디비에 다른아이디로 저장된다)
		   		 
		   		mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38",27017)));
				db=mc.getDB("project3");
				//dbc=db.getCollection("song"); // 테이블 지정
				dbc2=db.getCollection("naverClick2"); 
				
				
				
	    			BasicDBObject obj=new BasicDBObject();
	    			
	    				obj.put("song", song.trim());
	    				obj.put("singer", singer.trim());
	    				obj.put("review", vo.getNaverReview().trim());
	    				
	    			dbc2.insert(obj);
	    			
	    			 //System.out.println(song2+"=="+vo.getNaverReview());

	        }catch (Exception e) {
	            System.out.println(e);
	        }   
	      
		}
		
		
		
		
		
		
}

		
	
		
		
			   

