package com.aclass.mongo;

import java.net.InetSocketAddress;

import com.mongodb.*;
import java.util.*;

import org.springframework.stereotype.Repository;
import java.io.*;
import java.text.*;
@Repository
public class MusicAlbumDAO {
    private MongoClient mc;
    private DB db;
    private DBCollection tdbc,ndbc;
    private String date;
    public MusicAlbumDAO()
    {
    	try
    	{
    		date=new SimpleDateFormat("yyyyhh").format(new Date());
    		mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38", 27017)));
    	   db=mc.getDB("mydb");
    	   tdbc=db.getCollection("data_ljo"+date);
    	   ndbc=db.getCollection("news");
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    }
    public void dataInsert()
    {
    	try
    	{
    		File file=new File("/home/sist/data-r-00000");
    		FileReader fr=new FileReader(file);
    		String data="";
    		int i=0;
    		while((i=fr.read())!=-1)
    		{
    			data+=String.valueOf((char)i);
    		}
    		fr.close();
    		data=data.substring(0,data.lastIndexOf("\n"));
    		String[] datas=data.split("\n");
    		for(String s:datas)
    		{
    			StringTokenizer st=new StringTokenizer(s);
    			BasicDBObject obj=new BasicDBObject();
    			obj.put("date", date);
    			obj.put("name", st.nextToken().trim());
    			obj.put("count", Integer.parseInt(st.nextToken().trim()));
    			tdbc.insert(obj);
    		}
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    }
}





