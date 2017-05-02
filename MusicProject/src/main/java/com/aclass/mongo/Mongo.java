package com.aclass.mongo;

import com.aclass.mgr.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.util.*;

public class Mongo {
	private static MongoClient mc;
	private static DB db;
	private static DBCollection dbc;

	public static void main(String[] arg) {
		try {
			//dbc.drop();
			List<AlbumVO> alList = new ArrayList<AlbumVO>();
			MelonManager mm = new MelonManager();
			alList = mm.getAlbumData(10);

			mc = new MongoClient("localhost");
			db = mc.getDB("aclass");
			dbc = db.getCollection("musicAlbum");
			for (AlbumVO vo : alList){
				System.out.println(vo.getAlTitle());
			}
			for (AlbumVO vo : alList) {
				BasicDBObject obj = new BasicDBObject();
				obj.put("alNo", vo.getAlNo());
				obj.put("alTitle", vo.getAlTitle());
				dbc.insert(obj);
			}

		} catch (Exception ex) {
			System.out.println("main "+ex.getMessage());
		}
	}

}
