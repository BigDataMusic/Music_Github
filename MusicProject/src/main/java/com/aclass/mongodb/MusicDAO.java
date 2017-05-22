package com.aclass.mongodb;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.aclass.mgr.AlbumVO;
import com.aclass.mgr.BugsManager;
import com.aclass.mgr.MelonManager;
import com.aclass.mgr.MusicVO;
import com.mongodb.*;

@Repository
public class MusicDAO {
	@Autowired
	private MelonManager mmgr;
	@Autowired
	private BugsManager bmgr;
	@Autowired
	private MongoTemplate mt;
	
	private MongoClient mc;
	private DB db;
	private DBCollection mdbc,bdbc,navdbc,midbc,newMusicDBC,AlbumDBC;
	public MusicDAO(){
		try
    	{
			mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.38", 27017)));
			db=mc.getDB("mydb");
			bdbc=db.getCollection("Top100_Bugs");
			mdbc=db.getCollection("Top100_Melon");
			navdbc=db.getCollection("Top100_Naver");
			newMusicDBC=db.getCollection("NewMusic");
			midbc=db.getCollection("Top100_MusicIn");
			AlbumDBC=db.getCollection("MusicAlbum");
		}catch(Exception ex){}
	}
	public List<MusicVO> getMongoMusicData(String musicSite){
		List<MusicVO> list= new ArrayList<MusicVO>();
		try {
			DBCursor cursor=null;
			if(musicSite.equals("melon")) cursor=mdbc.find();
			else if(musicSite.equals("bugs")) cursor=bdbc.find();
			else if(musicSite.equals("newMusic")) cursor=newMusicDBC.find();
			else if(musicSite.equals("naver"))cursor=navdbc.find();
			else if(musicSite.equals("musicin"))cursor=midbc.find().sort(new BasicDBObject("rank",1));
			while(cursor.hasNext()){
				//System.out.println("확인");
				BasicDBObject obj=(BasicDBObject)cursor.next();
				MusicVO vo = new MusicVO();
				if(musicSite.equals("newMusic")){
					vo.setAlno(obj.getString("alno"));
					vo.setN(obj.getInt("no"));
					vo.setNo(obj.getInt("mno"));
					vo.setLyrics(obj.getString("lyrics"));
				}
				vo.setRank(obj.getInt("rank"));
				vo.setTitle(obj.getString("title"));
				vo.setPoster(obj.getString("poster"));
				vo.setArtist(obj.getString("artist"));
				vo.setAlbumname(obj.getString("album"));
				vo.setIncrement(obj.getString("increment"));
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
				for(int k=1; obj.getString("mTitle"+k)!=null; k++){
					MusicVO mvo = new MusicVO();
					mvo.setTitle(obj.getString("mTitle"+k));
					mvo.setLyrics(obj.getString("mLyrics"+k));
					mvolist.add(mvo);
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
	
	public List<String> musicTitleAllData() {
		List<String> list = new ArrayList<String>();
		try {
			DBCursor cursor = bdbc.find();
			while (cursor.hasNext()) {
				BasicDBObject obj = (BasicDBObject) cursor.next();
				String title = obj.getString("title");
				if (title.length() > 1) {
					list.add(title);
				}

			}
			cursor.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return list;
	}
	
	public void dropTop100(){
		mt.dropCollection("Top100_Melon");
		mt.dropCollection("NewMusic");
	}
	
	public void insertTop100(String musicSite){
		try {
			DBCollection indata=null;
			List<MusicVO> list = null;
			if(musicSite.equals("melon")){
				indata=mdbc;
				list=mmgr.getMelonTop100();
			}
			else if(musicSite.equals("bugs")){ 
				indata=bdbc;
				list=bmgr.bugsRankData();
			}
			else if(musicSite.equals("newMusic")){
				indata=newMusicDBC;
				list=mmgr.getMelonNewMusic();
			}
			else if(musicSite.equals("naver"))indata=navdbc;
			int cnt=0;
			for(MusicVO vo : list){
				BasicDBObject obj = new BasicDBObject();
				if(musicSite.equals("newMusic")){
					obj.put("no", ++cnt);
					obj.put("mno", vo.getNo());
					obj.put("lyrics", vo.getLyrics());
					obj.put("alno", vo.getAlno());
				}
				obj.put("title", vo.getTitle());
				obj.put("rank", vo.getRank());
				obj.put("poster", vo.getPoster());
				obj.put("artist", vo.getArtist());
				obj.put("album", vo.getAlbumname());
				obj.put("increment", vo.getIncrement());
				
				indata.insert(obj);
			}
			
		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("insertTop100 "+e.getMessage());
		}
	}
}