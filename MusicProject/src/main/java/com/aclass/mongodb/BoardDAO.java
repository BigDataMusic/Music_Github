package com.aclass.mongodb;
import java.net.InetSocketAddress;
import java.util.*;
import java.text.*;
import org.springframework.stereotype.Repository;

import com.mongodb.*;
@Repository
public class BoardDAO {
	private MongoClient mc;
	private DB db;
	private DBCollection dbc;
	public BoardDAO(){
		try {
			mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38", 27017)));
			db=mc.getDB("mydb");
			dbc=db.getCollection("MusicBoard");
		} catch (Exception ex) {
			System.out.println("BoardDAO : "+ex.getMessage());
		}
	}
	public void boardInsert(BoardVO vo){
		try {
			DBCursor cursor=dbc.find();
			int max=0;
			int gi=0;
			// {ket:value....},{},{},{}
			while(cursor.hasNext()){
				BasicDBObject obj=(BasicDBObject)cursor.next();
				int no=obj.getInt("no");
				int group_id=obj.getInt("group_id");
				if(max<no)
					max=no;
				if(gi<group_id)
					gi=group_id;
			}
			cursor.close();
			
			BasicDBObject in=new BasicDBObject();
			in.put("no", max+1);
			in.put("name", vo.getName());
			in.put("subject", vo.getSubject());
			in.put("content", vo.getContent());
			in.put("pwd", vo.getPwd());
			in.put("regdate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			in.put("hit", 0);
			in.put("group_id", gi+1);
			in.put("group_step", 0);
			in.put("group_tab",0);
			in.put("root", 0);
			in.put("depth", 0);
			dbc.insert(in);
		} catch (Exception ex) {
			System.out.println("boardInsert : "+ex.getMessage());
		}
	}
	// 목록출력
	public List<BoardVO> boardListData(int page){
		List<BoardVO> list=new ArrayList<BoardVO>();
		try {
			int rowSize=25;
			int skip=(page*rowSize)-(rowSize);
			DBCursor cursor=dbc.find().sort(new BasicDBObject("group_id",-1).append("group_step", 1)).skip(skip).limit(rowSize);
			while(cursor.hasNext()){
				BasicDBObject obj=(BasicDBObject)cursor.next();
				BoardVO vo=new BoardVO();
				vo.setNo(obj.getInt("no"));
				vo.setName(obj.getString("name"));
				vo.setSubject(obj.getString("subject"));
				vo.setRegdate(obj.getString("regdate"));
				vo.setHit(obj.getInt("hit"));
				vo.setGroup_tab(obj.getInt("group_tab"));
				list.add(vo);
			}
			cursor.close();
		} catch (Exception ex) {
			System.out.println("boardListData : "+ex.getMessage());
		}
		return list;
	}
	public int boardTotalPage(){
		int total = 0;
    	try {
    		DBCursor cursor = dbc.find();
    		int count = cursor.count();
    		total=(int)(Math.ceil(count/25.0));
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return total;
	}
	//내용보기,수정하기
	/*
	 *   자바 => 알고르지므 (라이브러리)
	 *   웹 => 흐름 => 프레임워크 (스프링)
	 *   ====================================== 서버
	 *   프론트페이지 => angular,jquery,react
	 *   node-js : spring
	 *   DataBase : SQL,
	 *   옵션 : 빅데이터 (R)
	 *   
	 */
	public void boardReply(int root,BoardVO vo)
	   {
		   // root ==> group_id,step,tab
		   try
		   {
			   BasicDBObject where=
					   new BasicDBObject();
			   where.put("no", root);
			   // db.board.find() == all
			   // db.board.find({no:10})
			   BasicDBObject pvo=(BasicDBObject)dbc.findOne(where);
			   int gi=pvo.getInt("group_id");
			   int gs=pvo.getInt("group_step");
			   int gt=pvo.getInt("group_tab");
			   int depth=pvo.getInt("depth");
			   // 변경  update board set group_step=group_step+1 where group_id=gi and group_step>gs
			   BasicDBObject[] where2=
				   {
					  new BasicDBObject("group_id",gi),
					  new BasicDBObject("group_step",new BasicDBObject("$gt",gs))
				   };
			   BasicDBObject up=new BasicDBObject();
			   up.put("group_step", gs+1);
			   dbc.update(new BasicDBObject("$and",where2), 
					            new BasicDBObject("$set",up));
			   // insert
			   int no=0;
			   DBCursor cursor=dbc.find();
			   while(cursor.hasNext())
			   {
				   BasicDBObject obj=(BasicDBObject)cursor.next();
				   int n=obj.getInt("no");
				   if(no<n)
					   no=n;
			   }
			   cursor.close();
			   BasicDBObject in=new BasicDBObject();
			   in.put("no", no+1);
			   in.put("name", vo.getName());
			   in.put("subject", vo.getSubject());
			   in.put("content", vo.getContent());
			   in.put("pwd", vo.getPwd());
			   in.put("regdate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			   in.put("hit", 0);
			   in.put("group_id", gi);
			   in.put("group_step", gs+1);
			   in.put("group_tab", gt+1);
			   in.put("root", root);
			   in.put("depth", 0);
			   dbc.insert(in);
			   // depth증가
			   BasicDBObject up2=new BasicDBObject();
			   up2.put("depth", depth+1);
			   dbc.update(where, new BasicDBObject("$set",up2));
		   }catch(Exception ex)
		   {
			   System.out.println(ex.getMessage());
		   }
	   }
	   // 삭제하기 
	   public boolean boardDelete(int no,String pwd)
	   {
		   boolean bCheck=false;
		   try
		   {
			   BasicDBObject where=
					   new BasicDBObject();
			   where.put("no", no);
			   BasicDBObject data=(BasicDBObject)dbc.findOne(where);
			   String db_pwd=data.getString("pwd");
			   int depth=data.getInt("depth");
			   int root=data.getInt("root");
			   int group_id=data.getInt("group_id");
			   int group_step=data.getInt("group_step");
			   if(db_pwd.equals(pwd))
			   {
				   if(group_step==0)
				   {
					   BasicDBObject where2=
							   new BasicDBObject();
					   where2.put("group_id", group_id);
					   dbc.remove(where2);
				   }
				   else
				   {
					   dbc.remove(where);
					   BasicDBObject obj=
							   new BasicDBObject();
					   obj.put("no", root);
					   BasicDBObject d=
							   (BasicDBObject)dbc.findOne(obj);
					   int dep=d.getInt("depth");
					   BasicDBObject up=
							   new BasicDBObject();
					   up.put("depth", dep-1);
					   dbc.update(obj, new BasicDBObject("$set",up));
				   }
				   bCheck=true;
				   
			   }
			   else
			   {
				   bCheck=false;
			   }
		   }catch(Exception ex)
		   {
			   System.out.println(ex.getMessage());
		   }
		   return bCheck;
	   }
	   // 내용보기,수정하기 
	   /*
	    *   자바 => 알고리즘 (라이브러리)
	    *   웹 => 흐름 => 프레임워크 (스프링)
	    *   ================================= 서버
	    *   프런트 => angularjs,jquery,react
	    *  node-js : spring
	    *  DB : SQL 
	    *  ==================================
	    *   옵션 : 빅데이터 (R)
	    *   
	    *   int[] arr=new int[10];
	    *   int index=0;
	    *    input()
	    *     {
	    *        arr[index]=10;
	    *        index++;
	    *     }
	    *    output()
	    *     {
	    *         arr[arr.length-1]
	    *     }
	    *     순서도 
	    */
	public BoardVO boardGetData(int type,int no){
		BoardVO vo=new BoardVO();
		try {
			BasicDBObject where=new BasicDBObject();
			where.put("no", no);
			// where no=10
			// where.put("no",new BasicDBObject("$lt",no))
			// no<10 $lt,$gt,$le,$ge
			// $set(update), $regex(like), $in(in연산자)
			if(type==0){
				BasicDBObject obj=(BasicDBObject)dbc.findOne(where);
				BasicDBObject updateObj=new BasicDBObject();
				updateObj.put("hit", obj.getInt("hit")+1);
				dbc.update(where,new BasicDBObject("$set",updateObj));
			}
			BasicDBObject data=(BasicDBObject)dbc.findOne(where);
			vo.setNo(data.getInt("no"));
			vo.setName(data.getString("name"));
			vo.setSubject(data.getString("subject"));
			vo.setContent(data.getString("content"));
			vo.setRegdate(data.getString("regdate"));
			vo.setHit(data.getInt("hit"));
			
		} catch (Exception ex) {
			System.out.println("boardGetData : "+ex.getMessage());
		}
		return vo;
	}
	public boolean boardUpdate(BoardVO vo){
		boolean bCheck=false;
		try {
			BasicDBObject where=new BasicDBObject();
			where.put("no", vo.getNo());
			BasicDBObject data=(BasicDBObject)dbc.findOne(where);
			String db_pwd=data.getString("pwd");
			if(db_pwd.equals(vo.getPwd())){
				bCheck=true;
				BasicDBObject up=new BasicDBObject();
				up.put("name", vo.getName());
				up.put("subject", vo.getSubject());
				up.put("content", vo.getContent());
				dbc.update(where, new BasicDBObject("$set",up));
			}else{
				bCheck=false;
			}
		} catch (Exception ex) {
			System.out.println("boardUpdate : "+ex.getMessage());
		}
		return bCheck;
	}
}
