package com.aclass.review.naver;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Component;

//네이버 블로그 리뷰 워드클라우드 
// /home/sist/bigdataDev/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MusicProject/main

@Component
public class RManager {
public void rGraph(String song)
{
	   try
	   {
		   //String song = "오늘 취하면 (Feat.창모) (Prod. SUGA)";
		   System.out.println(song);
		   RConnection rc=new RConnection();
		   rc.voidEval("library(wordcloud)");
		   rc.voidEval("library(KoNLP)");
		   rc.voidEval("library(rJava)");
		  
		   rc.voidEval("library(RMongo)");
		   rc.voidEval("library(stringr)");
		   //                  home/sist/bigdataDev/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MusicProject/ 
		   rc.voidEval("png(\"/home/sist/sparkDev/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MusicProject/resources/images/wordcloud.png\")");
		
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
		   ex.printStackTrace();
	   }
}

//느려서 잠정 중단됌 
public void rGraph2(String song)
{
	  try
	  {
		  RConnection rc=new RConnection();
		  rc.voidEval("naver<-read.csv(\"/home/sist/recommend-data/susubar.csv\",header=F,sep=\",\")");
		  rc.voidEval("png(\"/home/sist/sparkDev/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MusicProject/resources/images/susubar.png\")");
		  rc.voidEval("pct<-round((naver$V2/sum(naver$V2))*100,1)");
		  rc.voidEval("lab<-paste(naver$V1,\"\n\",\"(\",pct,\"%)\")");
		  rc.voidEval("pie(naver$V2,labels=lab,col=rainbow(10),main=\"날씨감성\")");
		  rc.voidEval("dev.off()");
		  rc.close();
	  }catch(Exception ex)
	  {
		  System.out.println(ex.getMessage());
	  }
}
   
   
   
   
   
}