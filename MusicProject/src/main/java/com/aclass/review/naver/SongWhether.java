package com.aclass.review.naver;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLEncoder;


@Component
public class SongWhether {
	
	private MongoClient mc;
	private DB db;
	private DBCollection dbc;

	 //실행기 
	public static void main(String[] args){
		
		String song = "팔레트 (Feat. G-DRAGON)";
		SongWhether rm = new SongWhether();
		rm.SongWhether(song);
	}
	
	 public List<SongVO> songData(String song)
	    {
	    	List<SongVO> list = new ArrayList<SongVO>();
	    	try
	    	{
	    		mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38",27017)));
				db=mc.getDB("project3");
				dbc=db.getCollection("naverClick");  
	  
				//노래에 대한 리뷰커서들 가져옴 
				BasicDBObject where=new BasicDBObject();
				//where.put("song", "팔레트 (Feat. G-DRAGON)");
				where.put("song", song);
				
	    		DBCursor cursor =dbc.find(where); 
	    	
			 	//커서가 끝나는만큼 반복 
			 	while(cursor.hasNext())
			 	{
			 		BasicDBObject obj = (BasicDBObject)cursor.next();
			 		
			 		SongVO vo = new SongVO();
			 		vo.setSong(obj.getString("song"));
			 		vo.setSinger(obj.getString("singer"));
			 		vo.setNaverReview(obj.getString("review"));
			 		list.add(vo);
			 		
			 		//System.out.println("ddd"+vo.getSong()+vo.getSinger());
			 	}
				  cursor.close();
	    	
	    	}catch(Exception ex)
	    	{
	    		System.out.println(ex.getMessage());
	    	}
	    	
	   	 return list;
	    }
	 
	 public void SongWhether(String song)
	 {
			try
			{
				SongWhether rm = new SongWhether();
				List<SongVO> list = rm.songData(song); //리뷰가져오기
				String data2="";
				
				//리뷰모아서 텍스트 저장하기 
				for(SongVO vo:list)
				{	
					FileWriter fw=new FileWriter("/home/sist/recommend-data/naver_review");
					data2+=vo.getNaverReview();
					
					fw.write(data2);
					fw.close();
				}
				
				//날씨사전 불러오기 
				FileReader fr=new FileReader("/home/sist/recommend-data/weather_data");
				String weatherdata="";
				int i=0;
				while((i=fr.read())!=-1)
				{
					weatherdata+=String.valueOf((char)i);
				}
				fr.close();
				String food[]=weatherdata.split("\n"); //날씨사전을 가져온것
				Pattern[] p=new Pattern[food.length];
				for(i=0;i<p.length;i++)
				{
					p[i]=Pattern.compile(food[i]);
				}
				Matcher[] m=new Matcher[food.length];
				System.out.println("날씨사전");
				
				//리뷰텍스트 가져오기 
							fr=new FileReader("/home/sist/recommend-data/naver_review");
							String data3="";
							i=0;
							while((i=fr.read())!=-1)
							{
								data3+=String.valueOf((char)i);
							}
							fr.close();
				
							String[] reviewData=data3.split("\n"); // naverReview를 가져올 것
										
							String[] taste={"봄","여름","가을","겨울","화창한날","아침",
									"오후","저녁","밤/새벽","비/흐림","크리스마스","눈오는날"};
							int[] count=new int[taste.length];
							for(String s:reviewData)
							{
								for(int j=0;j<m.length;j++)
								{
									m[j]=p[j].matcher(s);
									if(m[j].find())
									{
										if(j>=0 && j<=9)
										{
											count[0]++;
										}
										if(j>=10 && j<=21)
										{
											count[1]++;
										}
										if(j>=22 && j<=28)
										{
											count[2]++;
										}
										if(j>=29 && j<=38)
										{
											count[3]++;
										}
										if(j>=39 && j<=44)
										{
											count[4]++;
										}
										if(j>=45 && j<=48)
										{
											count[5]++;
										}
										if(j>=49 && j<=52)
										{
											count[6]++;
										}
										if(j>=53 && j<=55)
										{
											count[7]++;
										}
										if(j>=56 && j<=61)
										{
											count[8]++;
										}
										if(j>=62 && j<=71)
										{
											count[9]++;
										}
										if(j>=72 && j<=75)
										{
											count[10]++;
										}
										if(j>=76 && j<=80)
										{
											count[11]++;
										}
									}
								}
							}
							
							for(int j=0;j<12;j++)
							{
								//if(count[j]>0)
								//{
									System.out.println(taste[j]+":"+count[j]);
								//}
							}
							
							//몽고몽고
							mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38",27017)));
							db=mc.getDB("project3");
							dbc=db.getCollection("whether");  
							
							BasicDBObject obj=new BasicDBObject();
			    			
		    				obj.put("song", song.trim());
		    				for(int ie=0;ie<12;ie++)
		    				{
		    					obj.put(taste[ie], count[ie]);
		    				}
		    				
		    				dbc.insert(obj);
			 
			}catch(Exception ex){}
	 }
	

	
}