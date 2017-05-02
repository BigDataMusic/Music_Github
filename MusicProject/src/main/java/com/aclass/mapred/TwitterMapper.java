package com.aclass.mapred;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TwitterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
     private final IntWritable one=
		          new IntWritable(1);
     private Text result=new Text();
     private String[] data={"문재인","안철수","심상정","홍준표","유승민"};
     private Pattern[] p=new Pattern[data.length];
     private Matcher[] m=new Matcher[data.length];
	  @Override
	  protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		 for(int i=0;i<data.length;i++)
		 {
			 p[i]=Pattern.compile(data[i]);
		 }
		 for(int i=0;i<data.length;i++)
		 {
			 m[i]=p[i].matcher(value.toString());
			 if(m[i].find())
			 {
				 result.set(data[i]);
				 context.write(result, one);
			 }
		 }
	  }
     
}




