package com.aclass.mongodb;

import java.io.File;
import java.io.FileReader;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aclass.mgr.AlbumVO;
import com.aclass.mgr.MelonManager;
import com.aclass.mgr.MusicVO;
import com.mongodb.*;
import com.sist.mapred.SongVO;


@Repository
public class MusicDAO {
	@Autowired
	private MelonManager mmgr;
	
	private MongoClient mc;
	private DB db,db2;
	private DBCollection mdbc,bdbc,navdbc,newMusicDBC,AlbumDBC,wdbc;
	public MusicDAO(){
		try
    	{
			mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38", 27017)));
			db=mc.getDB("mydb");
			bdbc=db.getCollection("Top100_Bugs");
			mdbc=db.getCollection("Top100_Melon");
			navdbc=db.getCollection("Top100_Naver");
			newMusicDBC=db.getCollection("NewMusic");
			AlbumDBC=db.getCollection("MusicAlbum");
			db2=mc.getDB("project3");
			wdbc=db2.getCollection("weather");
		}catch(Exception ex){}
	}
	
	/*public void musicTop100Drop(){
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
    }*/
	public List<MusicVO> newMusicData(){
		List<MusicVO> list= new ArrayList<MusicVO>();
		try {
			DBCursor cursor=newMusicDBC.find();
			while(cursor.hasNext()){
				BasicDBObject obj=(BasicDBObject)cursor.next();
				MusicVO vo = new MusicVO();
				vo.setN(obj.getInt("no"));
				vo.setNo(obj.getInt("mno"));
				vo.setTitle(obj.getString("title"));
				vo.setPoster(obj.getString("poster"));
				vo.setArtist(obj.getString("artist"));
				vo.setLyrics(obj.getString("lyrics"));
				vo.setAlbumname(obj.getString("alname"));
				vo.setAlno(obj.getString("alno"));
				//System.out.println(obj.getString("alno\t"));
				list.add(vo);
			}
			cursor.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("newMusicData "+e.getMessage());
		}
		return list;
	}
	public List<MusicVO> getMongoMusicData(String musicSite){
		List<MusicVO> list= new ArrayList<MusicVO>();
		try {
			DBCursor cursor=null;
			if(musicSite.equals("melon")) cursor=mdbc.find();
			else if(musicSite.equals("bugs")) cursor=bdbc.find();
			else if(musicSite.equals("newMusic")) cursor=newMusicDBC.find();
			else if(musicSite.equals("naver"))cursor=navdbc.find();
			while(cursor.hasNext()){
				//System.out.println("확인");
				BasicDBObject obj=(BasicDBObject)cursor.next();
				MusicVO vo = new MusicVO();
				vo.setRank(obj.getInt("rank"));
				vo.setTitle(obj.getString("title"));
				vo.setPoster(obj.getString("poster"));
				vo.setArtist(obj.getString("artist"));
				vo.setAlbumname(obj.getString("album"));
				vo.setIncrement(obj.getString("increment"));
				//System.out.println(obj.getString("title"));
				list.add(vo);
			}
			cursor.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("bugsMusicData "+e.getMessage());
		}
		return list;
	}
	// main
	public List<MusicVO> bugsMusicData(){
		List<MusicVO> list= new ArrayList<MusicVO>();
		try {
			DBCursor cursor=bdbc.find();
			while(cursor.hasNext()){
				//System.out.println("확인");
				BasicDBObject obj=(BasicDBObject)cursor.next();
				MusicVO vo = new MusicVO();
				vo.setRank(obj.getInt("rank"));
				vo.setTitle(obj.getString("title"));
				vo.setPoster(obj.getString("poster"));
				vo.setArtist(obj.getString("artist"));
				vo.setAlbumname(obj.getString("album"));
				vo.setIncrement(obj.getString("increment"));
				//System.out.println(obj.getString("title"));
				list.add(vo);
			}
			cursor.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("bugsMusicData "+e.getMessage());
		}
		return list;
	}
	public List<AlbumVO> AlbumData(){
		List<AlbumVO> list= new ArrayList<AlbumVO>();
		try {
			DBCursor cursor=AlbumDBC.find();
			String alNo;
			while(cursor.hasNext()){
				BasicDBObject obj=(BasicDBObject)cursor.next();
				AlbumVO vo = new AlbumVO();
				vo.setAlNo(obj.getInt("alNo"));
				vo.setAlType(obj.getString("alType"));
				vo.setAlTitle(obj.getString("alTitle"));
				vo.setAlPoster(obj.getString("alPoster"));
				vo.setAlArtist(obj.getString("alArtist"));
				vo.setAlRegdate(obj.getString("alRegdate"));
				vo.setAlGenre(obj.getString("alGenre"));
				vo.setSaleCo(obj.getString("saleCo"));
				vo.setEntertainment(obj.getString("entertainment"));
				vo.setAlPoint(obj.getDouble("alPoint"));
				vo.setAlLike(obj.getInt("alLike"));
				vo.setAlInfo(obj.getString("alInfo"));
				List<MusicVO> mvolist = new ArrayList<MusicVO>();
				MusicVO mvo = new MusicVO();
				for(int k=1;;k++){
					if(obj.getString("mTitle"+k)!=null){
						mvo.setTitle(obj.getString("mTitle"+k));
						mvo.setTitle(obj.getString("mLyrics"+k));
						mvolist.add(mvo);
					}
					else break;
				}
				vo.setmList(mvolist);
				list.add(vo);
			}
			cursor.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("AlbumData "+e.getMessage());
		}
		return list;
	}

	public AlbumVO albumInsert(int alno){
		AlbumVO vo = new AlbumVO();
		try {
			vo=mmgr.getAlbumData(alno);
			//	System.out.println("=====================");
				//System.out.println("======"+vo.getAlTitle()+"========");
				BasicDBObject obj = new BasicDBObject();
				obj.put("alNo", vo.getAlNo());
				obj.put("alType", vo.getAlType());
				obj.put("alTitle", vo.getAlTitle());
				obj.put("alPoster", vo.getAlPoster());
				obj.put("alArtist", vo.getAlArtist());
				obj.put("alRegdate", vo.getAlRegdate());
				obj.put("alGenre", vo.getAlGenre());
				obj.put("saleCo", vo.getSaleCo());
				obj.put("entertainment", vo.getEntertainment());
				obj.put("alPoint", vo.getAlPoint());
				obj.put("alLike", vo.getAlLike());
				obj.put("alInfo", vo.getAlInfo());
				obj.put("alIncrement", vo.getAlIncrement());
				
				for(int i=1; i<vo.getmList().size()+1; i++){
					obj.put("mTitle"+i, vo.getmList().get(i-1).getTitle());
					//System.out.println("mTitle"+Integer.toString(i)+" - "+ vo.getmList().get(i-1).getTitle());
					obj.put("mLyrics"+i, vo.getmList().get(i-1).getLyrics());
				}
				AlbumDBC.insert(obj);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("albumInsert "+e.getMessage());
		}
		return vo;
	}
	public List<MusicVO> AllMusicRank(){
		/*
		 * 
			bdbc=db.getCollection("Top100_Bugs");
			newMusicDBC=db.getCollection("NewMusic");
			AlbumDBC=db.getCollection("MusicAlbum");
		 */
		List<MusicVO> rlist = new ArrayList<MusicVO>();
		List<MusicVO> buglist=getMongoMusicData("bugs");
		int cnt=0;
		System.out.println("==============================");
		for(MusicVO bvo : buglist){
			cnt++;
			System.out.println(cnt+" "+bvo.getTitle());
			if(cnt==100) break;
		}
		cnt=0;
		System.out.println("==============================");
		List<MusicVO> mellist=getMongoMusicData("melon");
		for(MusicVO mvo : mellist){
			cnt++;
			System.out.println(cnt+" "+mvo.getTitle());
		}
		
		
		return rlist;
	}
	
	
	public List<String> musicTitleAllData()
	   {
		   List<String> list=new ArrayList<String>();
		   try
		   {
			   DBCursor cursor=bdbc.find();
			   while(cursor.hasNext())
			   {
				   BasicDBObject obj=
						   (BasicDBObject)cursor.next();
				   String title=obj.getString("title");
				   if(title.length()>1)
				   {
				     list.add(title);
				   }
				   
			   }
			   cursor.close();
		   }catch(Exception ex)
		   {
			   System.out.println(ex.getMessage());
		   }
		   return list;
	   }
	/*
	 * private int n;
	private int no;
	private int rank;
	private String increment;
	private String poster;
	private String title;
	private String artist;
	private String albumname;
	private int like;
	private String lyrics;
	private boolean tit_music;
	private String alno;
	 * 
	 * 
	 */
	public MusicVO musicGetData()
	   {
			MusicVO vo=new MusicVO();
		   try
		   {
			   BasicDBObject where=
					   new BasicDBObject();
			   //where.put("title", title);
			   BasicDBObject obj=(BasicDBObject)wdbc.findOne(where);
			   vo.setNo(obj.getInt("no"));
			   vo.setRank(obj.getInt("rank"));
			   vo.setPoster(obj.getString("poster"));
			   vo.setTitle(obj.getString("title"));
			   vo.setArtist(obj.getString("artist"));
			   //vo.getAlbumname());
			   //vo.getLike(obj.getInt("like"));
			   //vo.getIncrement(obj.getString("increment"));
			   
		   }catch(Exception ex)
		   {
			   System.out.println(ex.getMessage());
		   }
		   return vo;
	   }
	
	public List<MusicVO> songData()
    {
    	List<SongVO> list = new ArrayList<SongVO>();
    	try
    	{
    		DBCursor cursor =dbc.find(); //송테이블에서 가져오기 
    		//db.getCollection('song').find({singer:'\t거미\t'})
    		
		 	//커서가 끝나는만큼 반복 
    		int i=1;
		 	while(cursor.hasNext())
		 	{
		 		BasicDBObject obj = (BasicDBObject)cursor.next();
		 		
		 		SongVO vo = new SongVO();
		 		vo.setSong(obj.getString("song"));
		 		vo.setSinger(obj.getString("singer"));
		 		vo.setNaverReview(obj.getString("review"));
		 		list.add(vo);
		 		
		 		System.out.println(i+" : "+vo.getNaverReview());
		 		i++;
		 	}
			  cursor.close();
    	
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	
   	 return list;
    }
	
}
