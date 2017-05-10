package com.aclass.mongo;

import java.net.InetSocketAddress;
import java.util.List;

import com.aclass.mgr.BugsManager;
import com.aclass.mgr.MusicVO;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class Top100Bugs {
	static MongoClient mc;
	static DB db;
	static DBCollection mdbc;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BugsManager bm = new BugsManager();
		
		
		try {
			mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38", 27017)));
	    	db=mc.getDB("mydb");
	    	mdbc=db.getCollection("Top100_Bugs");
			List<MusicVO> list=bm.bugsRankData();
			for(MusicVO vo : list){
				System.out.println("=====================");
				System.out.println("======"+vo.getTitle()+"========");
				BasicDBObject obj = new BasicDBObject();
				obj.put("title", vo.getTitle());
				obj.put("rank", vo.getRank());
				obj.put("poster", vo.getPoster());
				obj.put("artist", vo.getArtist());
				obj.put("album", vo.getAlbumname());
				obj.put("increment", vo.getIncrement());
				
				mdbc.insert(obj);
			}
			//Thread.sleep(20000);
	    	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
	}

}