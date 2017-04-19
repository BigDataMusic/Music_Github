package com.aclass.mgr;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Melon {
	public static void main(String[] args){
		Melon m = new Melon();
		m.getMelonTop100();
	}
	public List<MusicVO> getMelonTop100(){
		List<MusicVO> list= new ArrayList<MusicVO>();
		try {
			Document doc=Jsoup.connect("http://www.melon.com/chart/index.htm").get();
			//Elements tElem=doc.select("a.fc_mgray");
			Elements tElem=doc.select("div.ellipsis span strong a");
			Elements aElem=doc.select("div.rank02 a span:eq(0)");
			for(int i=3; i<tElem.size(); i++){
				Element title=tElem.get(i);
				Element artist=aElem.get(i-3);
				System.out.println(title.text()+"-"+artist.text());
				
			}
			
		} catch (Exception e) {
			System.out.println("getMelonTop100 "+e.getMessage());
		}
		return list;
	}
}
