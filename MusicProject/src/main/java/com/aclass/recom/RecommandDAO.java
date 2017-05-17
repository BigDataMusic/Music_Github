package com.aclass.recom;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.aclass.mgr.MusicVO;
import com.aclass.review.naver.SongVO;
import com.aclass.spark.WeatherRecomSparkManager;
import com.mongodb.*;

@Repository
public class RecommandDAO {
	
	 private MongoClient mc;
    private DB db;
    private DBCollection weather;
    
    public RecommandDAO()
    {
    	try
    	{
    		mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38", 27017)));
    		db=mc.getDB("project3");
    		weather=db.getCollection("weather");
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    }	
	public List<SongVO> getWeather(){
		List<SongVO> list = new ArrayList<SongVO>();
		try {
			DBCursor cursor = weather.find();
			while(cursor.hasNext()){
				BasicDBObject obj = (BasicDBObject)cursor.next();
				SongVO vo = new SongVO();
				vo.setSong(obj.getString("song"));
				vo.setSinger(obj.getString("singer"));
				vo.set봄(obj.getInt("봄"));
				vo.set여름(obj.getInt("여름"));
				vo.set가을(obj.getInt("가을"));
				vo.set겨울(obj.getInt("겨울"));
				vo.set화창한날(obj.getInt("화창한날"));
				vo.set아침(obj.getInt("아침"));
				vo.set오후(obj.getInt("오후"));
				vo.set저녁(obj.getInt("저녁"));
				vo.set새벽(obj.getInt("밤/새벽"));
				vo.set흐림(obj.getInt("비/흐림"));
				vo.set크리스마스(obj.getInt("크리스마스"));
				vo.set눈오는날(obj.getInt("눈오는날"));
				list.add(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception\
			System.out.println("getweather - "+e.getMessage());
		}
		return list;
	}
	//"봄"
	public List<SongVO> songGetweatherData(String cate)
    {
    	List<SongVO> list=new ArrayList<SongVO>();
    	try
    	{
			DBCursor cursor = weather.find();
    		while(cursor.hasNext())
    		{
    			BasicDBObject obj = (BasicDBObject)cursor.next();
    			SongVO vo = new SongVO();
    			vo.setSong(obj.getString("song"));
				vo.setSinger(obj.getString("singer"));
				vo.setPoster(obj.getString("poster"));
				
				System.out.println(obj.getInt(cate));
				/*if(obj.getInt(cate) != null)
				{
					vo.set봄(obj.getInt(cate));
					System.out.println(cate+" set완료@@@@@@@@@@@@@@@");
				}*/
				list.add(vo);
    		}
		/*	List<SongVO> vo = new ArrayList<SongVO>();
						for(SongVO v:vo){
							if(v.get봄()!=0){
								System.out.println(v.getSong());
							}
						} */
    		cursor.close();
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	return list;
    }
    public List<SongVO> getMongoFeelData(String rno){
		List<SongVO> list= new ArrayList<SongVO>();
		try {
			DBCursor cursor=null;
			if(rno.equals("1")) cursor=weather.find();
			while(cursor.hasNext()){
				//System.out.println("확인");
				BasicDBObject obj=(BasicDBObject)cursor.next();
				SongVO vo = new SongVO();
				vo.setSong(obj.getString("song"));
				vo.setSinger(obj.getString("singer"));
				//vo.setWeather(obj.getString("feel"));
				System.out.println(obj.getString("singer"));
				//System.out.println(obj.getString("title"));
				list.add(vo);
			}
			cursor.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getMongoFeelData "+e.getMessage());
		}
		return list;
	}
    public List<SongVO> songRecommandData(String 봄,String feel)
    {
 	   List<SongVO> list=
 			   new ArrayList<SongVO>();
 	   try
 	   {
 		   BasicDBObject where=
 				     new BasicDBObject();
 		   where.put("봄", 봄);
 		   DBCursor cursor=weather.find(where);
 		   while(cursor.hasNext())
 		   {
 			   BasicDBObject obj=(BasicDBObject)cursor.next();
 			  SongVO vo=new SongVO();
 			   //vo.setMno(obj.getInt("mno"));
 			   vo.setSong(obj.getString("song"));
 			   vo.setSinger(obj.getString("singer"));
 			   String db_feel=obj.getString("봄");
 			   StringTokenizer st=
 					   new StringTokenizer(db_feel, ",");
 			   // db_feel="SF:2,ani:7"
 			   while(st.hasMoreTokens())
 			   {
 				   String temp=st.nextToken();
 				   String temp2=temp.substring(0,temp.lastIndexOf(":"));
 				   if(temp2.equals(feel))
 				   {
 					   list.add(vo);
 				   }
 			   }
 		   }
 		   cursor.close();
 	   }catch(Exception ex)
 	   {
 		   System.out.println(ex.getMessage());
 	   }
 	   return list;
 	   
    }
}
