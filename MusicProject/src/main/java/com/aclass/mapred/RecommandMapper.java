package com.aclass.mapred;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecommandMapper extends Mapper<LongWritable, Text, Text, IntWritable>{	
    private final IntWritable one=
    		        new IntWritable(1);
    private Text result=new Text();
	 @Override
	 protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		  FileReader fr=new FileReader("/home/sist/food_review/weather_data");
         String data="";
         int i=0;
         while((i=fr.read())!=-1)
           {
       	   data+=String.valueOf((char)i);  
           }
         fr.close();
         String food[]=data.split("\n");
         Pattern[] p=new Pattern[food.length];
         for(i=0;i<p.length;i++)
           {
       	   p[i]=Pattern.compile(food[i]);
           }
         Matcher[] m=new Matcher[food.length];
		String[] weather={"봄","여름","가을","겨울","화창한날","아침","오후",
				"저녁","밤/새벽","비/흐림","크리스마스","눈오는 날"};
		for(int j=0;j<m.length;j++)
		{
			m[j]=p[j].matcher(value.toString());
			if(m[j].find())
			{
				if(j>=0 && j<=9)
				{
					result.set(weather[0]);
					context.write(result, one);
				}
				if(j>=10 && j<=24)
				{
					result.set(weather[1]);
					context.write(result, one);
				}
				if(j>=25 && j<=31)
				{
					result.set(weather[2]);
					context.write(result, one);
				}
				if(j>=32 && j<=41)
				{
					result.set(weather[3]);
					context.write(result, one);
				}
				if(j>=42 && j<=46)
				{
					result.set(weather[4]);
					context.write(result, one);
				}
				if(j>=47 && j<=50)
				{
					result.set(weather[5]);
					context.write(result, one);
				}
				if(j>=51 && j<=54)
				{
					result.set(weather[5]);
						context.write(result, one);
				}
				if(j>=55 && j<=57)
				{
					result.set(weather[5]);
					context.write(result, one);
				}
				if(j>=58 && j<=63)
				{
					result.set(weather[5]);
					context.write(result, one);
				}
				if(j>=64 && j<=73)
				{
					result.set(weather[5]);
					context.write(result, one);
				}
				if(j>=74 && j<=77)
				{
					result.set(weather[5]);
					context.write(result, one);
				}
				if(j>=78 && j<=82)
				{
					result.set(weather[5]);
					context.write(result, one);
				}
			}
		}
	}
}