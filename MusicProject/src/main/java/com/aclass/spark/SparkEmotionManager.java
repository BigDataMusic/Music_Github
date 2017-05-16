package com.aclass.spark;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import scala.Tuple2;

import java.io.*;
@Component
@Scope("prototype")
public class SparkEmotionManager implements Serializable{
	public void execute(){
		try {
			//Spark 연결
			SparkConf conf=new SparkConf().setMaster("local").setAppName("emotionFeelCount");
			// Java에서 연결
			JavaSparkContext sc=new JavaSparkContext(conf);
			final String[] feel = {"사랑/기쁨","이별/슬픔","스트레스/짜증","우울할때","화창한날",
					"멘붕/불안","외로울때",};
		 File file=new File("/home/sist/feel-data/emotion_data");
		 FileReader fr=new FileReader(file);
		 String data="";
		 int k=0;
		 while((k=fr.read())!=-1)
		 {
			 data+=String.valueOf((char)k);
		 }
		 fr.close();
		 
		 String[] feel_data=data.split("\n");
		 final Pattern[] p=new Pattern[feel_data.length];
		 for(int i=0;i<p.length;i++)
		 {
			 p[i]=Pattern.compile(feel_data[i]);
		 }
		 final Matcher[] m=new Matcher[feel_data.length];
		 JavaPairRDD<String,Integer> counts=null;
			// Mapper
			JavaRDD<String> input=sc.textFile("/home/sist/feel-data/review.txt");
			JavaRDD<String> words=input.flatMap(new FlatMapFunction<String, String>() {

				@Override
				public Iterator<String> call(String x) throws Exception {
					List<String> list=new ArrayList<String>();
					for(int i=0;i<m.length;i++){
						// String s="(()()()()())";
						// String s=(([0-9]{1,3}).([0-9]{1,3}).([0-9]{1,3}).([0-9]{1,3}));
						m[i]=p[i].matcher(x);
						if(m[i].find()){
							if(i>=0 && i<=10)
							{
								list.add(feel[0]);
							}
							if(i>=11 && i<=18)
							{
								list.add(feel[1]);
							}
							if(i>=19 && i<=29)
							{
								list.add(feel[2]);
							}
							if(i>=30 && i<=35)
							{
								list.add(feel[3]);
							}
							if(i>=36 && i<=40)
							{
								list.add(feel[4]);
							}
							if(i>=41 && i<=44)
							{
								list.add(feel[5]);
							}
						}
					}
					return list.iterator();
				}
			});
			counts=words.mapToPair(new PairFunction<String, String, Integer>() {

				@Override
				public Tuple2<String, Integer> call(String x) throws Exception {
					
					return new Tuple2<String, Integer>(x, 1);
				}
			}).reduceByKey(new Function2<Integer, Integer, Integer>() {
				
				@Override
				public Integer call(Integer x, Integer y) throws Exception {
					return x+y;
				}
			});
			JavaPairRDD<String, Integer> wcPari=words.mapToPair(new PairFunction<String, String, Integer>() {

				@Override
				public Tuple2<String, Integer> call(String s) throws Exception {
					
					return new Tuple2<String, Integer>(s, 1);
				}
			});
			JavaPairRDD<String, Integer> result=wcPari.reduceByKey(new Function2<Integer, Integer, Integer>() {
				
				@Override
				public Integer call(Integer x, Integer y) throws Exception {
					// TODO Auto-generated method stub
					return x+y;
				}
			});
			File dir=new File("/home/sist/feel-data/emotion");
			 if(dir.exists())
			 {
				 File[] files=dir.listFiles();
				 for(File f:files)
				 {
					 f.delete();
				 }
				 dir.delete();
			 }
			counts.saveAsTextFile("/home/sist/feel-data/emotion");
			File file1=new File("/home/sist/feel-data/emotion/part-00000");
			FileReader fr1=new FileReader(file1);
			String data1="";
			int i=0;
			while((i=fr1.read())!=-1){
				data1+=String.valueOf((char)i);
			}
			fr1.close();
			data1=data1.replace("(", "");
			data1=data1.replace(")", "");
			data1=data1.replace(",", " ");
			FileWriter fw1=new FileWriter("/home/sist/feel-data/emotion.csv");
			fw1.write(data1);
			fw1.close();
			sc.stop();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
}
