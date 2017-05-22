package com.aclass.review.naver;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Component;

import com.aclass.mgr.MusicVO;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

//네이버 블로그 리뷰 워드클라우드 
// /home/sist/bigdataDev/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MusicProject/main

@Component
public class RManager {
	
	private MongoClient mc;
	private DB db;
	private DBCollection dbc;
	
public void rGraph(String song)
{
	   try
	   {
		   //String song = "오늘 취하면 (Feat.창모) (Prod. SUGA)";
		   
		   RConnection rc=new RConnection();
		   rc.voidEval("library(wordcloud)");
		   rc.voidEval("library(KoNLP)");
		   rc.voidEval("library(rJava)");
		  
		   rc.voidEval("library(RMongo)");
		   rc.voidEval("library(stringr)");
		   
		   rc.voidEval("png(\"/home/sist/BigdataDev2/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MusicProject/resources/images/wordcloud.png\")");
//		   rc.voidEval("png(\"/home/sist/BigdataDev2/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MusicProject/resources/images/wordcloud.png\")");
		
		   rc.voidEval("mongo<-mongoDbConnect(\"project3\",\"211.238.142.38\",27017)");

		   rc.voidEval("data<-dbGetQuery(mongo,\"naverClick\",\"{song:'"+song+"'}\")"); 
		   rc.voidEval("data2<-dbGetQuery(mongo,\"naverClick2\",\"{song:'"+song+"'}\")"); 
		  
		   rc.voidEval("join<-merge(data,data2,by=\"song\")");
		  
		   rc.voidEval("news<-join");
		   //rc.voidEval("news<-data");
		   //rc.voidEval("news<-data$review"); //이거쓰니까 데이터 줄어버려 
		   
	 
		   rc.voidEval("news=gsub(\"</b>\",\"\",news)"); //영어숫자를 공백으로 전환
		   rc.voidEval("news=gsub(\"[A-Za-z0-9]\",\"\",news)"); //영어숫자를 공백으로 전환
		   rc.voidEval("news=gsub(\"<>\",\"\",news)"); //영어숫자를 공백으로 전환
		   rc.voidEval("news=gsub(\"+\",\"\",news)"); //영어숫자를 공백으로 전환
		   rc.voidEval("news=gsub(\"&\",\"\",news)"); //영어숫자를 공백으로 전환
		   rc.voidEval("news=gsub(\";\",\"\",news)"); //영어숫자를 공백으로 전환
		   
		   rc.voidEval("data<-sapply(news,extractNoun,USE.NAMES = F)"); //명사로 잘라줌        
		   
		   rc.voidEval("undata<-unlist(data)");
		  rc.voidEval("news_data<-Filter(function(x){nchar(x)>=1 & nchar(x)<=5},undata)"); //2~5글자
		  
		  // rc.voidEval("top1<-head(sort(news_data,decreasing = T),50)");
		   rc.voidEval("word<-table(news_data)");
		   
		   rc.voidEval("pal <- brewer.pal(9,\"Paired\")"); //색상코드
		   //rc.voidEval("wordcloud(names(word),freq=word,scale = c(2,1),rot.per=0.25,min.freq = 1,random.order = F,colors = rainbow(15))");
		   rc.voidEval("wordcloud(names(word),freq=word,scale=c(6,2),min.freq=5, random.order=F, rot.per=.1,colors=pal)");
		   rc.voidEval("dev.off()"); //워드클라우드 그리기 
		   rc.close();
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
}




public void rGraph2(String song)
{
	  try
	  {
		  //rc.voidEval("png(\"/home/sist/GithubDev/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MusicProject/resources/images/emotion.png\")");
		  RConnection rc=new RConnection();
		  rc.voidEval("data<-read.csv(\"/home/sist/feel-data/emotion.csv\",header=T,sep=\",\")");
//			rc.voidEval("library(plotrix)");
		  rc.voidEval("library(wordcloud)");
			rc.voidEval("png(\"/home/sist/BigdataDev2/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MusicProject/resources/images/emotion.png\")");
			rc.voidEval("pct<-round(data$count/sum(data$count)*100,1)");
			rc.voidEval("lab<-paste(data$emotion,\"\n\",\"(\",pct,\"%)\")");
			rc.voidEval("pal <- brewer.pal(9,\"Paired\")"); //색상코드
			rc.voidEval("pie(data$count,labels=lab,col=pal,main=\"기분감성\")");
			rc.voidEval("dev.off()");
		  rc.close();
	  }catch(Exception ex)
	  {
		  System.out.println(ex.getMessage());
	  }
} 
   
//하이차트 바그래프
public String createJSON2(String song)
{
	String result="";
	try
	{
		//JSONArray arr=new JSONArray();
		/*
		 *   {} ==> JSONObject => key:value
		 *   [10,20,30] ==> (X)
		 *   [{},{},{}] => JSONArray==>JSONObject
		 */
		
		mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38",27017)));
		db=mc.getDB("project3");
		dbc=db.getCollection("weather");  
		
		//노래에 대한 리뷰커서들 가져옴 
		BasicDBObject where=new BasicDBObject();
		where.put("song", song);
		BasicDBObject obj =(BasicDBObject)dbc.findOne(where); 
		
		String db_feel=obj.getString("feel");
		//봄:35,여름:23,화창한날:5,밤/새벽:10
		System.out.println(db_feel);
		String[] data=db_feel.split(",");
		JSONArray arr=new JSONArray();
		for(String s:data)
		{
			StringTokenizer ss=new StringTokenizer(s, ":");
			JSONObject jo=new JSONObject();
			jo.put("name", ss.nextToken());
			jo.put("y", Integer.parseInt(ss.nextToken()));
			arr.add(jo);
			
		}
		result=arr.toJSONString();//제이순에 적용되도록 형변환
		System.out.println("result:"+result);
		
	}catch(Exception ex)
	{
		System.out.println(ex.getMessage());
	}
	return result;
}


   
}