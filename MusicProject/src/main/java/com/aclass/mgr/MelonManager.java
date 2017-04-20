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
			
			//Elements d1Elem=doc.select("div.wrap_song_info");
			Elements d1Elem=doc.select("div.wrap_song_info strong a");
			Elements d2Elem=doc.select("div.wrap_song_info div div.rank02");
			Elements d3Elem=doc.select("div.wrap_song_info div div.rank03");
			//Elements d2Elem=doc.select("div.rank02 span:eq(0)");
			int i=0;
			for(int j=6;j<d1Elem.size();j++){
				System.out.println(j-5+"위 "+d1Elem.get(j).text()+"-"+d2Elem.get(j-6).text().substring(0,d2Elem.get(j-6).text().length()/2)+"-"+d3Elem.get(j-6).text());
			}
			
		} catch (Exception e) {
			System.out.println("getMelonTop100 "+e.getMessage());
		}
		return list;
	}
}
