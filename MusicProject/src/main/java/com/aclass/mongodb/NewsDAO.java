package com.aclass.mongodb;

import java.net.InetSocketAddress;
import java.util.List;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.aclass.mongodb.*;

public class NewsDAO {
	private MongoClient mc;
	private DB db;
	private DBCollection dbc;

	public NewsDAO() {
		try {
			mc = new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38", 27017)));
			db = mc.getDB("mydb");
			dbc = db.getCollection("music_news");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void naver_newsDrop() {
		try {
			dbc.drop();
		} catch (Exception ex) {}
	}

	public void naver_newsInsert(List<NewsVO> list) {
		try {
			BasicDBObject[] in = new BasicDBObject[list.size()];
			int i = 0;
			for (NewsVO vo : list) {
				BasicDBObject obj = new BasicDBObject();
				obj.put("title", vo.getTitle());
				obj.put("desc", vo.getDescription());
				obj.put("link", vo.getLink());
				obj.put("date", vo.getPubDate());
				obj.put("author", vo.getAuthor());
				obj.put("category", vo.getCategory());
				in[i] = obj;
				i++;
			}
			dbc.insert(in);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
