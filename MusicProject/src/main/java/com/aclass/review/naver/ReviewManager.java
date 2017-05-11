package com.aclass.review.naver;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;


import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;



//몽고디비 project3 - song테이블에서 추천노래 추출 

@Repository
public class ReviewManager {
	private MongoClient mc;
	private DB db;
	private DBCollection dbc;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReviewManager dao=new ReviewManager();
		//dao.foodDrop();
		dao.songData();
	}

	public ReviewManager()
	{
		try
		{
			mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38",27017)));
			db=mc.getDB("project3");
			//dbc=db.getCollection("song"); // 테이블 지정->리뷰 긁어올때 사용(리뷰디에이오)
			dbc=db.getCollection("naverAPI"); // 사이트에 목록띄울때 사용 
			
		}catch(Exception ex){}
	}
	public void foodDrop()
    {
    	dbc.drop();
    }
	
	
    public List<SongVO> songData()
    {
    	List<SongVO> list = new ArrayList<SongVO>();
    	try
    	{
    		
  
    		//DBCursor cursor =dbc.find().skip(1039).limit(1); //송테이블에서 가져오기 1039개를 제외한 다음번부터 1개만 가져와라  
    		//db.getCollection('song').find({no:{$lte:20}}).pretty();
    		DBCursor cursor =dbc.find();
    		
		 	//커서가 끝나는만큼 반복 
		 	while(cursor.hasNext())
		 	{
		 		BasicDBObject obj = (BasicDBObject)cursor.next();
		 		
		 		SongVO vo = new SongVO();
		 		//vo.setNo(obj.getString("no"));
		 		vo.setSong(obj.getString("song"));
		 		vo.setSinger(obj.getString("singer"));
		 		list.add(vo);
		 		
		 		//System.out.println(vo.getNo()+vo.getSong()+vo.getSinger()); //디비 인설트용 
		 		//System.out.println(vo.getSong()+vo.getSinger());
		 	}
			  cursor.close();
    	
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	
   	 return list;
    }
    
  
}