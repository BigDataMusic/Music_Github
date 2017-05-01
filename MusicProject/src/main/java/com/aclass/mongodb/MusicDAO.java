package com.aclass.mongodb;

import java.io.File;
import java.io.FileReader;
import java.net.InetSocketAddress;

import org.springframework.stereotype.Repository;

import com.mongodb.*;

@Repository
public class MusicDAO {
	private MongoClient mc;
	private DB db;
	private DBCollection dbc;
	public MusicDAO(){
		try
    	{
			mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38", 27017)));
			db=mc.getDB("mydb");
			dbc=db.getCollection("musicTop100");
		}catch(Exception ex){}
	}
	
	public void musicTop100Drop(){
    	dbc.drop();
    }
	public void musicTop100Insert(){
    	try{
    		File file=new File("/home/sist/bugsMusicTop100.csv");
    		String top100_data="";
    		int i=0;
    		FileReader fr=new FileReader(file);
    		while((i=fr.read())!=-1){
    			top100_data+=String.valueOf((char)i);
    		}
    		fr.close();
    		String[] data=top100_data.split("\n");
    		String[] column={
    				"rank","title","artist","albumname","poster"
    		};
    		for(String s:data){
    			String[] in=s.split("|");
    			BasicDBObject obj=new BasicDBObject();
    			for(i=0;i<column.length;i++){
    				obj.put(column[i], in[i]);
    			}
    			dbc.insert(obj);
    		}    		
    	}catch(Exception ex){
    		System.out.println(ex.getMessage());
    	}
    }
}
