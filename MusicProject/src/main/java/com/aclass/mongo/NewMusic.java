package com.aclass.mongo;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.*;
import com.aclass.mgr.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class NewMusic {
	static MongoClient mc;
	static DB db;
	static DBCollection mdbc;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MelonManager mm = new MelonManager();
		List<MusicVO> nm = mm.getMelonNewMusic();
		System.out.println(nm.size());
		try {
			mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38", 27017)));
	    	db=mc.getDB("mydb");
	    	mdbc=db.getCollection("NewMusic");
	    	int cnt=1;
	    	for(MusicVO vo : nm){
					System.out.println("=====================");
					System.out.println("======"+vo.getTitle()+"========");
					BasicDBObject obj = new BasicDBObject();
					obj.put("no", cnt);
					obj.put("mno", vo.getNo());
					obj.put("title", vo.getTitle());
					obj.put("poster", vo.getPoster());
					obj.put("artist", vo.getArtist());
					obj.put("lyrics", vo.getLyrics());
					obj.put("alname", vo.getAlbumname());
					obj.put("alno", vo.getAlno());
					
					mdbc.insert(obj);
					cnt++;
	    	}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}

