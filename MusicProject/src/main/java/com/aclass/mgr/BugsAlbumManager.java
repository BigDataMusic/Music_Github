package com.aclass.mgr;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
/*
	private String alPoster;
	private String alRegdate;
 */
@Component
public class BugsAlbumManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BugsAlbumManager bam = new BugsAlbumManager();
		bam.bugsAlbumRankData();
	}

	public List<AlbumVO> bugsAlbumRankData() {
		List<AlbumVO> bList = new ArrayList<AlbumVO>();
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
				
				AlbumVO vo = new AlbumVO();
				vo.setAlRank(i + 1);
				vo.setAlTitle(title.trim());
				vo.setAlPoster(poster.trim());
				vo.setAlArtist(art.text().trim());
				vo.setAlIncrement(in.text());
				// list.add(vo);
				
				System.out.println(vo.getAlRank()+"위 "+vo.getAlTitle());
				System.out.println("   "+vo.getAlArtist());
				System.out.println("   "+vo.getAlPoster());
				System.out.println("   "+vo.getAlIncrement());
			}
			System.out.println("== 완료 ==");
		} catch (Exception ex) {
			System.out.println("bugsAlbumRankData " + ex.getMessage());
		}
		return bList;
	}

}