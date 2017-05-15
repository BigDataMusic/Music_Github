package com.aclass.spark;

import java.util.*;
import java.io.File;
import java.io.FileReader;

public class spark {
	public static void main(String[] args){
		try {
		 File file=new File("/home/sist/recommand-data/recomreview.txt");
		 FileReader fr=new FileReader(file);

		 String data="";
		 int k=0;
		 while((k=fr.read())!=-1) //EOF End Of File
		 {
			 data+=String.valueOf((char)k);
		 }
		 fr.close();
		 StringTokenizer st = new StringTokenizer(data, "@@@");
		 List<String> artist = new ArrayList<String>();
		 List<String> title = new ArrayList<String>();
		 List<String> review = new ArrayList<String>();
		 
		 while(st.hasMoreTokens()){
			 System.out.println(st.nextToken());
			 //artist.add(st.nextToken().toString());
			 title.add(st.nextToken().toString());
			 review.add(st.nextToken().toString());
			 data=data.replace(" " ,"");
		 }
		 System.out.println("확인");
		 /*for(String s : artist){
			 System.out.println(s);
		 }
		 for(String t : title){
			 System.out.println(t);
		 }*/
		 for(String r : review){
			 
			 System.out.println("review"+r);
		 }
		 
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
