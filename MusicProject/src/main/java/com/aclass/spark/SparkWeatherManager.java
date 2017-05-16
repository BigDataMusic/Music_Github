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
public class SparkWeatherManager implements Serializable{
	public void execute(){
		try {
			//Spark 연결
			SparkConf conf=new SparkConf().setMaster("local").setAppName("weatherFeelCount");
			// Java에서 연결
			JavaSparkContext sc=new JavaSparkContext(conf);
			final String[] feel = {"봄","여름","가을","겨울","화창한날","아침","오후",
					"저녁","밤/새벽","비/흐림","크리스마스","눈오는 날"};
		 File file=new File("/home/sist/feel-data/weather_data");
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
							if(i>=0 && i<=9)
							{
								list.add(feel[0]);
							}
							if(i>=10 && i<=24)
							{
								list.add(feel[1]);
							}
							if(i>=25 && i<=31)
							{
								list.add(feel[2]);
							}
							if(i>=32 && i<=41)
							{
								list.add(feel[3]);
							}
							if(i>=42 && i<=46)
							{
								list.add(feel[4]);
							}
							if(i>=47 && i<=50)
							{
								list.add(feel[5]);
							}
							if(i>=51 && i<=54)
							{
								list.add(feel[6]);
							}
							if(i>=55 && i<=57)
							{
								list.add(feel[7]);
							}
							if(i>=58 && i<=63)
							{
								list.add(feel[8]);
							}
							if(i>=64 && i<=73)
							{
								list.add(feel[9]);
							}
							if(i>=74 && i<=77)
							{
								list.add(feel[10]);
							}
							if(i>=78 && i<=82)
							{
								list.add(feel[11]);
							}
							//list.add(m[i].group());
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
			File dir=new File("/home/sist/feel-data/weather");
			 if(dir.exists())
			 {
				 File[] files=dir.listFiles();
				 for(File f:files)
				 {
					 f.delete();
				 }
				 dir.delete();
			 }
			counts.saveAsTextFile("/home/sist/feel-data/weather");
			File file1=new File("/home/sist/feel-data/weather/part-00000");
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
			FileWriter fw1=new FileWriter("/home/sist/feel-data/weather.csv");
			fw1.write(data1);
			fw1.close();
			sc.stop();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
