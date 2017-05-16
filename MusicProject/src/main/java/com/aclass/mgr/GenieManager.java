package com.aclass.mgr;

import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class GenieManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenieManager gm = new GenieManager();
		gm.genieRankData();
	}

	public List<MusicVO> genieRankData() {
		List<MusicVO> gList = new ArrayList<MusicVO>();
		try {
			int rank = 1;
			for (int j = 1; j <= 2; j++) {
				Document doc = Jsoup.connect("http://www.genie.co.kr/chart/top100?ditc=D&ymd=20170421&hh=18&rtm=Y&pg=" + Integer.toString(j)).get();
				Elements tElem = doc.select("span.music_area span.music a.title");
				Elements pElem = doc.select("span.music-info span.album a img");
				Elements artElem = doc.select("span.music span.meta a.artist");
				Elements alElem = doc.select("span.music span.meta a.albumtitle");
				/*Elements iElem;
				if (doc.select("span.rank span.rankup") != null) {
					iElem=doc.select("span.rank span.rankup");
					System.out.println("상");
				} else if(doc.select("span.rank span.rankdown")!=null){
					iElem=doc.select("span.rank span.rankdown");
					System.out.println("하락");
				} else{
					iElem=doc.select("span.rank span");
					System.out.println("변동없음");
				}*/
				
				for (int i = 0; i < 50; i++) {
					MusicVO vo = new MusicVO();
					
					Element t = tElem.get(i);
					Element pos = pElem.get(i);
					String poster = pos.attr("src");
					poster = poster.substring(poster.indexOf("//") + 2);
					Element art = artElem.get(i);
					Element al = alElem.get(i);
					/*else if (doc.select("span.rank span.rankdown") != null) {
						vo.setIncrement("하락");
					} else {
						vo.setIncrement("-");
					}*/
					// Element in = iElem.get(i);

					/*
					 * if(increment.trim()=="-"){ vo.setIncrement(increment); }
					 */

					vo.setRank(rank);
					vo.setTitle(t.text().trim());
					vo.setPoster(poster.trim());
					vo.setArtist(art.text().trim());
					vo.setAlbumname(al.text().trim());
					vo.setIncrement("-");
					gList.add(vo);
					
					/*System.out.println(vo.getRank() + "위 " + vo.getTitle() + " - " + vo.getArtist());
					System.out.println("   " + vo.getPoster());
					System.out.println("   " + vo.getAlbumname());*/
					
					rank++;
				}
			}

		} catch (Exception ex) {
			System.out.println("genieRankData " + ex.getMessage());
		}
		return gList;
	}
}