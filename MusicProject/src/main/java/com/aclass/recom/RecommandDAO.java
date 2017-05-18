package com.aclass.recom;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private DBCollection emotion;
    public SongVO songDetailData(String song)
    {
    	RecommandDAO dao=new RecommandDAO();
    	SongVO vo=new SongVO();
    	try
    	{
    		BasicDBObject where=new BasicDBObject();
    		where.put("song", song);
    		BasicDBObject obj=(BasicDBObject)weather.findOne(where); // BasicDBObject -> 전체가져옴  / findOne 블록단위 가져옴
    		vo.setNo(obj.getString("no"));
    		vo.setSong(obj.getString("song"));
    		vo.setSinger(obj.getString("singer"));
    		vo.setPoster(obj.getString("poster"));
    		vo.setAlbum(obj.getString("album"));
    		
    		
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    		
    	}
    	return vo;
    }
    public RecommandDAO()
    {
    	try
    	{
    		mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38", 27017)));
    		db=mc.getDB("project3");
    		weather=db.getCollection("weather");
    		emotion=db.getCollection("emotion");
    		//emotion=db.getCollection("emotion");
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
				//cursor.
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
	public static void main(String[] arg)
	{
		RecommandDAO dao=new RecommandDAO();
		List<SongVO> list=dao.songRecommandData("봄");
		for(SongVO vo:list)
		{
			System.out.println(vo.getSinger()+" "+vo.getSong());
		}
	}
    public List<SongVO> songRecommandData(String feel)
    {
    	List<SongVO> list=
  			   new ArrayList<SongVO>();
    	try
    	{
    		DBCursor cursor=weather.find();
    		Pattern p=Pattern.compile(feel);
    		
    		while(cursor.hasNext())
    		{
    		    BasicDBObject obj=(BasicDBObject)cursor.next();
    		    String strFeel=obj.getString("feel");
    		    Matcher m=p.matcher(strFeel);
    		    if(m.find())
    		    {
    		    	SongVO vo=new SongVO();
    		    	vo.setSong(obj.getString("song"));
    				vo.setSinger(obj.getString("singer"));
    				vo.setPoster(obj.getString("poster"));
    			    vo.setFeel(obj.getString("feel"));
    			    vo.setAlbum(obj.getString("album"));
    				list.add(vo);
    		    	
    		    }
    		}
    		cursor.close();
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	return list;
    }
    public List<SongVO> emotionRecommandData(String feel)
    {
    	List<SongVO> list=
  			   new ArrayList<SongVO>();
    	try
    	{
    		DBCursor cursor=emotion.find();
    		Pattern p=Pattern.compile(feel);
    		
    		while(cursor.hasNext())
    		{
    		    BasicDBObject obj=(BasicDBObject)cursor.next();
    		    String strFeel=obj.getString("feel");
    		    Matcher m=p.matcher(strFeel);
    		    if(m.find())
    		    {
    		    	SongVO vo=new SongVO();
    		    	vo.setSong(obj.getString("song"));
    				vo.setSinger(obj.getString("singer"));
    				vo.setPoster(obj.getString("poster"));
    			    vo.setFeel(obj.getString("feel"));
    			    vo.setAlbum(obj.getString("album"));
    				list.add(vo);
    		    	
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
