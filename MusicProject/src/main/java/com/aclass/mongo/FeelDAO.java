package com.aclass.mongo;

import javax.annotation.Resource;
import java.util.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.aclass.mgr.*;
@Repository
public class FeelDAO {
	@Resource(name="mongoTemplate")
	private MongoTemplate mt;
	public void musicInsert(MusicVO vo){
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
	}
}
