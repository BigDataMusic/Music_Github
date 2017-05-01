package com.aclass.mgr;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class BugsManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BugsManager bm = new BugsManager();
		//bm.bugsRankData();
		//System.out.println("==================== 곡 완료 =====================");
		//bm.bugsAlbumRankData();
		//System.out.println("==================== 앨범 완료 =====================");
		bm.init();
	}
	
	public void init(){
		List<MusicVO> bList=bugsRankData();
		try{
			String data="";
			for(MusicVO vo:bList){
				data+=vo.getRank()+"|"
						+vo.getTitle()+"|"
						+vo.getArtist()+"|"
						+vo.getAlbumname()+"|"
						+vo.getPoster()+"\n";
			}
			data = data.substring(0, data.lastIndexOf("\n"));
			File file = new File("/home/sist/bugsMusicTop100.csv");
			if (file.exists()) {
				file.delete();
			}
			FileWriter fw = new FileWriter("/home/sist/bugsMusicTop100.csv");
			fw.write(data);
			fw.close();
			System.out.println("bugs music 생성 완료");
		}catch(Exception ex){
			System.out.println("init: "+ex.getMessage());
		}
	}
	
	// 곡
	public List<MusicVO> bugsRankData() {
		List<MusicVO> bList = new ArrayList<MusicVO>();
		try {
			Document doc = Jsoup.connect("http://music.bugs.co.kr/chart/track/realtime/total").get();
			Elements tElem = doc.select("td input");
			Elements pElem = doc.select("td a img");
			Elements artElem = doc.select("td.left p.artist a:eq(0)");
			Elements alElem = doc.select("td.left a.album");
			Elements iElem = doc.select("div.ranking p");

			for (int i = 0; i < 100; i++) {
				Element t = tElem.get(i);
				String title = t.attr("title");
				Element pos = pElem.get(i);
				String poster = pos.attr("src");
				Element art = artElem.get(i);
				Element al = alElem.get(i);
				Element in = iElem.get(i);
				String incre=in.text().trim();

				MusicVO vo = new MusicVO();
				
				if(incre.contains("변동없음")){
					incre=incre.replace("변동없음", "");
					vo.setIncrement(incre);
				}else if(incre.contains("상승")){
					incre=incre.replace("계단 상승", "").trim();
					incre="+"+incre;
					vo.setIncrement(incre);
				}else if(incre.contains("하락")){
					incre=incre.replace("계단 하락", "").trim();
					incre="-"+incre;
					vo.setIncrement(incre);
				}
				
				vo.setRank(i + 1);
				vo.setTitle(title.trim());
				vo.setPoster(poster.trim());
				vo.setArtist(art.text().trim());
				vo.setAlbumname(al.text().trim());
				
				bList.add(vo);
				
				/*System.out.println(vo.getRank() + "위 " + vo.getTitle() + " - " + vo.getArtist());
				System.out.println("   " + vo.getPoster());
				System.out.println("   " + vo.getIncrement());
				System.out.println("   " + vo.getAlbumname());*/
			}

		} catch (Exception ex) {
			System.out.println("bugsRankData " + ex.getMessage());
		}
		return bList;
	}

	// 앨범
	public List<AlbumVO> bugsAlbumRankData() {
		List<AlbumVO> bAlList = new ArrayList<AlbumVO>();
		try {
			Document doc = Jsoup.connect("http://music.bugs.co.kr/chart/album/day/total").get();
			Elements tElem = doc.select("a.albumTitle");
			Elements pElem = doc.select("img");
			Elements artElem = doc.select("div.subInfo p.artist");
			Elements iElem = doc.select("figcaption.info div.ranking");

			for (int i = 0; i < 100; i++) {
				Element t = tElem.get(i);
				String title = t.attr("title");
				Element pos = pElem.get(i);
				String poster = pos.attr("src");
				Element art = artElem.get(i);
				Element in = iElem.get(i);
				String incre = in.text().trim();
				incre = incre.substring(incre.indexOf(" "));

				AlbumVO vo = new AlbumVO();
				if (incre.contains("변동없음")) {
					incre = incre.replace("변동없음", "").trim();
					vo.setAlIncrement(incre);
				} else if (incre.contains("상승")) {
					incre = incre.replace("계단 상승", "").trim();
					incre = "+" + incre;
					vo.setAlIncrement(incre);
				} else if (incre.contains("하락")) {
					incre = incre.replace("계단 하락", "").trim();
					incre = "-" + incre;
					vo.setAlIncrement(incre);
				} else {
					vo.setAlIncrement("Hot");
				}

				vo.setAlRank(i + 1);
				vo.setAlTitle(title.trim());
				vo.setAlPoster(poster.trim());
				vo.setAlArtist(art.text().trim());
				
				//bAlList.get(vo);
				/*System.out.println(vo.getAlRank() + "위 " + vo.getAlTitle());
				System.out.println("   " + vo.getAlArtist());
				System.out.println("   " + vo.getAlPoster());
				System.out.println("   " + vo.getAlIncrement());*/
			}
		} catch (Exception ex) {
			System.out.println("bugsAlbumRankData " + ex.getMessage());
		}
		return bAlList;
	}
}