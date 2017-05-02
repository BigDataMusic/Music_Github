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
/*
 * private int alNo;//d
	private int alRank;
	private String alType;//d
	private String alTitle;//d
	private String alPoster;//d
	private String alArtist;//
	private String alRegdate;//
	private String alGenre;//
	private String saleCo;//
	private String entertainment;//
	private double alPoint;//
	private int alLike;//
	private String alInfo;//
	private String alIncrement;//
 */
public class Album {
	static MongoClient mc;
	static DB db;
	static DBCollection mdbc;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MelonManager mm = new MelonManager();
		try {
			mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38", 27017)));
	    	   db=mc.getDB("mydb");
	    	   mdbc=db.getCollection("MusicAlbum");
			List<AlbumVO> list = mm.getAlbumData(10059324);
			for(AlbumVO vo : list){
				System.out.println("=====================");
				System.out.println("======"+vo.getAlTitle()+"========");
				BasicDBObject obj = new BasicDBObject();
				obj.put("alNo", vo.getAlNo());
				obj.put("alType", vo.getAlType());
				obj.put("alTitle", vo.getAlTitle());
				obj.put("alPoster", vo.getAlPoster());
				obj.put("alArtist", vo.getAlArtist());
				obj.put("alRegdate", vo.getAlRegdate());
				obj.put("alGenre", vo.getAlGenre());
				obj.put("saleCo", vo.getSaleCo());
				obj.put("entertainment", vo.getEntertainment());
				obj.put("alPoint", vo.getAlPoint());
				obj.put("alLike", vo.getAlLike());
				obj.put("alInfo", vo.getAlInfo());
				obj.put("alIncrement", vo.getAlIncrement());
				
				for(int i=1; i<vo.getmList().size()+1; i++){
					obj.put("mTitle"+i, vo.getmList().get(i-1).getTitle());
					System.out.println("mTitle"+Integer.toString(i)+" - "+ vo.getmList().get(i-1).getTitle());
					obj.put("mLyrics"+i, vo.getmList().get(i-1).getLyrics());
				}
				
				
				mdbc.insert(obj);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
	}

}
