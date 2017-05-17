package com.aclass.review.naver;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import com.aclass.mgr.MusicVO;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLEncoder;


@Component
public class SongEmotion implements Serializable{
	
	private MongoClient mc;
	private DB db;
	private DBCollection dbc;

	 //실행기 
	public static void main(String[] args){
		
		//String song = "맞지?";
		SongEmotion se = new SongEmotion();
		List<MusicVO> list=se.songAllData();
		int i=0;
		for(MusicVO vo:list)
		{
			se.SongEmotion(vo.getTitle(), vo.getArtist(), vo.getPoster());
			System.out.println("i="+i);
			i++;
		}
		//rm.feelData(song);
		System.out.println("End..");
	}
	
	public List<MusicVO> songAllData()
	{
		List<MusicVO> list=new ArrayList<MusicVO>();
		try
		{
			mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38",27017)));
			db=mc.getDB("mydb");
			dbc=db.getCollection("Top100_Bugs");  
			DBCursor cursor=dbc.find();
			while(cursor.hasNext())
			{
				BasicDBObject obj=(BasicDBObject)cursor.next();
				MusicVO vo=new MusicVO();
				vo.setTitle(obj.getString("title"));
				vo.setArtist(obj.getString("artist"));
				vo.setPoster(obj.getString("poster"));
				list.add(vo);
			}
			cursor.close();
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return list;
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
	 
	 public void SongEmotion(String song,String singer,String poster)
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
				
				//날씨사전 불러오기 (홈 레커멘드데이타에 날씨사전 있어야댐)
				FileReader fr=new FileReader("/home/sist/recommend-data/emotion_data");
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
										
							String[] taste={"사랑/기쁨","이별/슬픔","스트레스/짜증","우울할때",
									"멘붕/불안","외로울때"};
							int[] count=new int[taste.length];
							for(String s:reviewData)
							{
								for(int j=0;j<m.length;j++)
								{
									m[j]=p[j].matcher(s);
									if(m[j].find())
									{
										if(j>=0 && j<=10)
										{
											count[0]++;
										}
										if(j>=11 && j<=18)
										{
											count[1]++;
										}
										if(j>=19 && j<=29)
										{
											count[2]++;
										}
										if(j>=30 && j<=35)
										{
											count[3]++;
										}
										if(j>=36 && j<=40)
										{
											count[4]++;
										}
										if(j>=41 && j<=44)
										{
											count[5]++;
										}
									}
								}
							}
							
							for(int j=0;j<6;j++)
							{
								//if(count[j]>0)
								//{
									System.out.println(taste[j]+":"+count[j]);
								//}
							}
							
							//몽고몽고
							mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38",27017)));
							db=mc.getDB("project3");
							dbc=db.getCollection("emotion");  
							
							BasicDBObject obj=new BasicDBObject();
			    			
							obj.put("song", song.trim());
							obj.put("singer", singer.trim());
							obj.put("poster", poster.trim());
							/*obj.put("feel", (taste[0]+":"+count[0]+","+taste[1]+":"+count[1]+","
										+taste[2]+":"+count[2]+","+taste[3]+":"+count[3]+","
										+taste[4]+":"+count[4]+","+taste[5]+":"+count[5]+","+taste[6]+":"+count[6]+","
										+taste[7]+":"+count[7]+","+taste[8]+":"+count[8]+","+taste[9]+":"+count[9]+","
										+taste[10]+":"+count[10]+","+taste[11]+":"+count[11]).trim()
									);*/


		    				for(int ie=0;ie<6;ie++)
		    				{
		    					
		    					if(count[ie]>0)
		    					{
		    						obj.put(taste[ie], count[ie]);
		    					}
		    				}
		    				
		    				
		    				dbc.insert(obj);
			 
			}catch(Exception ex){}
	 }
	

	 //몽고디비에서 feel가져와서 csv만들기 
	 public void feelData(String song)
	   {
		   
		   try
		   {
				mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38",27017)));
				db=mc.getDB("project3");
				dbc=db.getCollection("emotion");  
				
				
				//노래에 대한 리뷰커서들 가져옴 
				BasicDBObject where=new BasicDBObject();
				
				where.put("song", song);
				
	    		DBCursor cursor =dbc.find(where); 
		
	    		FileWriter fw=new FileWriter("/home/sist/recommend-data/susubar.csv");
				CSVWriter cw=new CSVWriter(fw);
				
				
				
			   while(cursor.hasNext())
			   {
				   BasicDBObject obj=(BasicDBObject)cursor.next();
				   SongVO vo=new SongVO();
				   String db_feel=obj.getString("feel");
				  
				   StringTokenizer st= new StringTokenizer(db_feel, ","); // db_feel="SF:2,ani:7"
				   List<String[]> list= new ArrayList<String[]>();
				   
				   while(st.hasMoreTokens())
				   {
					   String temp=st.nextToken();
					   String temp2=temp.substring(0,temp.lastIndexOf(":"));
					   String temp3=temp.substring(temp.lastIndexOf(":")+1);
				
						   String[] ss={temp2,temp3};
						   list.add(ss);
					   
					   
				   }
				   cw.writeAll(list);
				   fw.close();
				   
				   
			   }
			   cursor.close();
			   
			   System.out.println("생성");
		   }catch(Exception ex)
		   {
			   System.out.println(ex.getMessage());
		   }
	
	   }
	
}