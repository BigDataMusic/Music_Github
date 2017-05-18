package com.aclass.mongo;

import javax.annotation.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;
import com.aclass.mgr.*;
@Repository
public class FeelDAO {
	@Resource(name="mongoTemplate")
	private MongoTemplate mt;
	public MusicVO musicDetailData(String title){
		BasicQuery query=new BasicQuery("{title:"+title+"}");
		MusicVO vo=mt.findOne(query, MusicVO.class, "buks");
		return vo;
	}
}