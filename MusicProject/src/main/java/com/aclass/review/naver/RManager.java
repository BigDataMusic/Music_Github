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
		   
		   RConnection rc=new RConnection();
		   rc.voidEval("library(wordcloud)");
		   rc.voidEval("library(KoNLP)");
		   rc.voidEval("library(rJava)");
		  
		   rc.voidEval("library(RMongo)");
		   rc.voidEval("library(stringr)");
		   
		   rc.voidEval("png(\"/home/sist/bigdataDev/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MusicProject/resources/images/wordcloud.png\")");
		
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
		   RConnection rc=new RConnection();
		   rc.voidEval("library(rJava)");
		   rc.voidEval("library(RMongo)");
		   
		 //명령어 전달 (내역이름이 브이원, 수치가 브이투) 
	      	 rc.voidEval("png(\"/home/sist/bigdataDev/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MusicProject/resources/images/pie.png\")");
	      	 
			 rc.voidEval("mongo<-mongoDbConnect(\"project3\",\"211.238.142.38\",27017)");
			 rc.voidEval("data<-dbGetQuery(mongo,\"whether\",\"{song:'"+song+"'}\")"); 
	      	  
	      //rc.voidEval("par(mfrow=c(2,2))"); //차트를 두줄에 두개씩 그릴것이다 (한줄에 세개는 2,3)
	   	  //rc.voidEval("barplot(data$V2,names.arg=data$V1,col=rainbow(5))");
		  //rc.voidEval("hist(data$V2)");
	   	  rc.voidEval("pie(data$V2,labels=data$V1,col=rainbow(5))");
	   	  
	   	  
	   	  //차트종료,알연결종료
	   	  rc.voidEval("dev.off()");
	   	  rc.close();
	   	  
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
}
   
   
   
   
   
}