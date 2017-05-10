package com.aclass.review.naver;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Component;

//네이버 블로그 리뷰 워드클라우드 
// /home/sist/bigdataDev/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MusicProject/main

@Component
public class RManager {
public void rGraph()
{
	   try
	   {
		   String song = "널 사랑하지 않아";
		   
		   RConnection rc=new RConnection();
		   rc.voidEval("library(wordcloud)");
		   rc.voidEval("library(KoNLP)");
		   rc.voidEval("library(rJava)");
		  
		   rc.voidEval("library(RMongo)");
		 
		   rc.voidEval("png(\"/home/sist/bigdataDev/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MusicProject/resources/images/wordcloud.png\")");
		
		   rc.voidEval("mongo<-mongoDbConnect(\"project3\",\"211.238.142.38\",27017)");

		   rc.voidEval("data<-dbGetQuery(mongo,\"naverAPI\",\"{song:'"+song+"'}\")"); 
		
		   rc.voidEval("news<-data");
		   //rc.voidEval("news<-data$review"); 이거쓰니까 데이터 줄어버려 
		   
		   rc.voidEval("news=gsub(\"[A-Za-z0-9]\",\"\",news)"); //영어숫자를 공백으로 전환
		   rc.voidEval("news=gsub(\"<>\",\"\",news)"); //영어숫자를 공백으로 전환
		   rc.voidEval("news=gsub(\"/\",\"\",news)"); //영어숫자를 공백으로 전환
		   rc.voidEval("data<-sapply(news,extractNoun,USE.NAMES = F)"); //명사로 잘라줌        
		   
		   rc.voidEval("undata<-unlist(data)");
		   rc.voidEval("news_data<-Filter(function(x){nchar(x)>=2 & nchar(x)<=5},undata)"); //2~5글자
		  
		   rc.voidEval("top1<-head(sort(news_data,decreasing = T),50)");
		   rc.voidEval("word<-table(top1)");
		   
		   rc.voidEval("pal <- brewer.pal(9,\"Paired\")"); //색상코드
		   //rc.voidEval("wordcloud(names(word),freq=word,scale = c(2,1),rot.per=0.25,min.freq = 1,random.order = F,colors = rainbow(15))");
		   rc.voidEval("wordcloud(names(word),freq=word,scale=c(6,2),min.freq=5, max.words=Inf, random.order=F, rot.per=.1,colors=pal, family=\"headline\")");
		   rc.voidEval("dev.off()"); //워드클라우드 그리기 
		   rc.close();
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
}

   
   
   
   
   
   
}