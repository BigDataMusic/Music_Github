package com.aclass.mgr;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class MelonManager {
	public static void main(String[] args){
		MelonManager m = new MelonManager();
		m.getMelonTop100();
	}
	public List<MusicVO> getMelonTop100(){
		List<MusicVO> list= new ArrayList<MusicVO>();
		try {
			Document doc=Jsoup.connect("http://www.melon.com/chart/index.htm").get();
			
			//Elements titleElem=doc.select("div.wrap_song_info");
			Elements titleElem=doc.select("div.wrap_song_info strong a");
			Elements artElem=doc.select("div.wrap_song_info div div.rank02");
			Elements alElem=doc.select("div.wrap_song_info div div.rank03");
			Elements pElem=doc.select("a.image_type15 img");
			//Elements artElem=doc.select("div.rank02 span:eq(0)");
			int i=0;
			for(int j=6;j<titleElem.size();j++){
				//System.out.println(j-5+"위 "+titleElem.get(j).text()+"-"+artElem.get(j-6).text().substring(0,artElem.get(j-6).text().length()/2)+"-"+alElem.get(j-6).text());
				//System.out.println(pElem.get(j-6).attr("src"));
				
				MusicVO vo = new MusicVO();
				vo.setRank(j-5);
				vo.setPoster(pElem.get(j-6).attr("src"));
				vo.setTitle(titleElem.get(j).text());
				vo.setArtist(artElem.get(j-6).text());
				vo.setAlbumname(alElem.get(j-6).text());
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			System.out.println("getMelonTop100 "+e.getMessage());
		}
		return list;
	}
	
}
