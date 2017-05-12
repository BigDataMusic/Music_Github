package com.aclass.mongodb;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.stereotype.Repository;

import com.aclass.recom.RecommandVO;
import com.mongodb.*;

@Repository
public class RecommandDAO {
	private MongoClient mc;
	private DB db;
	private DBCollection dbc;
	private DBCollection dbc1;
	public RecommandDAO(){
		try
    	{
			mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38", 27017)));
			db=mc.getDB("project3");
			dbc=db.getCollection("review");
			dbc1=db.getCollection("recommand");
		}catch(Exception ex){}
	}
	public List<RecommandVO> movieRecommandData(String review,String feel,String weather)
    {
 	   List<RecommandVO> list=
 			   new ArrayList<RecommandVO>();
 	   try
 	   {
 		   BasicDBObject where=
 				     new BasicDBObject();
 		   where.put("review", review);
 		   DBCursor cursor=dbc.find(where);
 		   while(cursor.hasNext())
 		   {
 			   BasicDBObject obj=(BasicDBObject)cursor.next();
 			  RecommandVO vo=new RecommandVO();
 			   vo.setSinger(obj.getString("singer"));
 			   vo.setSong(obj.getString("song"));
 			   vo.setReview(obj.getString("review"));
 			   String db_feel=obj.getString("feel");
 			   StringTokenizer st=
 					   new StringTokenizer(db_feel, ",");
 			   // db_feel="SF:2,ani:7"
 			   while(st.hasMoreTokens())
 			   {
 				   String temp=st.nextToken();
 				   String temp2=temp.substring(0,temp.lastIndexOf(":"));
 				   if(temp2.equals(feel))
 				   {
 					   list.add(vo);
 				   }
 			   }
 		   }
 		   cursor.close();
 	   }catch(Exception ex)
 	   {
 		   System.out.println(ex.getMessage());
 	   }
 	   return list;
 	   
    }
	public List<String> musicRecommandAllData()
    {
 	   List<String> list=new ArrayList<String>();
 	   try
 	   {
 		   DBCursor cursor=dbc.find();
 		   while(cursor.hasNext())
 		   {
 			   BasicDBObject obj=
 					   (BasicDBObject)cursor.next();
 			   String song=obj.getString("song");
 			   if(song.length()>1)
 			   {
 			     list.add(song);
 			   }
 		   }
 		   cursor.close();
 	   }catch(Exception ex)
 	   {
 		   System.out.println(ex.getMessage());
 	   }
 	   return list;
    }
	public RecommandVO recommandGetData(String song)
    {
		RecommandVO vo=new RecommandVO();
 	   try
 	   {
 		   BasicDBObject where=
 				   new BasicDBObject();
 		   where.put("song", song);
 		   BasicDBObject obj=(BasicDBObject)dbc.findOne(where);
 		   vo.setSinger(obj.getString("singer"));
		   vo.setSong(obj.getString("song"));
		   vo.setReview(obj.getString("review"));
 	   }catch(Exception ex)
 	   {
 		   System.out.println(ex.getMessage());
 	   }
 	   return vo;
    }
}
