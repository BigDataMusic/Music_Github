package com.aclass.mgr;

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
		bm.bugsRankData();
	}

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

				MusicVO vo = new MusicVO();
				vo.setRank(i + 1);
				vo.setTitle(title.trim());
				vo.setPoster(poster.trim());
				vo.setArtist(art.text().trim());
				vo.setAlbumname(al.text().trim());
				vo.setIncrement(in.text());
				// list.add(vo);
				
				System.out.println(vo.getRank()+"위 "+vo.getTitle()+" - "+vo.getArtist());//+" - "+vo.getAlbumname()+" - "
				System.out.println("   "+vo.getPoster());
				System.out.println("   "+vo.getIncrement());
				System.out.println("   "+vo.getAlbumname());
			}

		} catch (Exception ex) {
			System.out.println("bugsRankData " + ex.getMessage());
		}
		return bList;
	}
}
