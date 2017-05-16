package com.aclass.mongo;

import javax.annotation.Resource;

import java.net.InetSocketAddress;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.aclass.mgr.*;
import com.aclass.review.naver.SongVO;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
@Repository
public class FeelDAO {
	@Autowired
	
	
	private DB db2;
	private MongoClient mc;
	private DBCollection wdbc;
	@Resource(name="mongoTemplate")
	private MongoTemplate mt;
	
	public FeelDAO(){
		try {
		mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38", 27017)));
		db2=mc.getDB("project3");
		wdbc=db2.getCollection("whether");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("FeelDAO - "+e.getMessage());
		}
	}
/*	public static void main(String[] args){
		FeelDAO a = new FeelDAO();
		a.getweather();
	}*/
	public List<SongVO> getweather(){
		List<SongVO> list = new ArrayList<SongVO>();
		try {
			DBCursor cursor = wdbc.find();
			while(cursor.hasNext()){
				BasicDBObject obj = (BasicDBObject)cursor.next();
				SongVO vo = new SongVO();
				vo.setSong(obj.getString("song"));
				vo.setSinger(obj.getString("singer"));
				System.out.println(obj.getString("singer"));
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
	/*public void musicInsert(MusicVO vo){
		mt.insert(vo, "buks");
	}
	public void musicDrop(){
		mt.dropCollection("buks");
	}
	public void musicFeelInsert(MusicFeelCountDataVO fvo){
		mt.insert(fvo, "music_feel");
	}
	public void musicFeelDrop(){
		mt.dropCollection("music_feel");
	}
	public List<MusicVO> musicAllData(){
		Query query=new Query();
		List<MusicVO> list=mt.find(query, MusicVO.class, "buks");
		return list;
	}
	public MusicVO musicDetailData(int rank){
		BasicQuery query=new BasicQuery("{rank:"+rank+"}");
		MusicVO vo=mt.findOne(query, MusicVO.class, "buks");
		return vo;
	}*/
}
