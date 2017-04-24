package com.aclass.mgr;

import java.util.*;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SongManager {

	List<AlbumVO> alist = new ArrayList<AlbumVO>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SongManager sm = new SongManager();
		sm.songYearData();
	}

	public List<AlbumVO> songYearData() {
		String data="";
		try {
			String[] genre = { "kpop", "pop", "fost", "dramaost" };
			for (int i = 0; i < genre.length; i++) {
				Document doc = Jsoup.connect("http://music.bugs.co.kr/years/" + genre[i]).get();
				Elements yearElem = doc.select("section.sectionPadding div.innerContainer ul li figcaption.info a.title");
				for (int j = 0; j < yearElem.size(); j++) {
					Element yearNo = yearElem.get(j);
					Element yearInfo = yearElem.get(j);
					String year = yearNo.attr("href");
					System.out.println(yearInfo.text() + "========================================");

					Document doc2 = Jsoup.connect(year).get();
					Elements aElem = doc2.select("td.left p.artist:eq(0)");
					Elements tElem = doc2.select("td.check input");
					for (int k = 0; k < tElem.size(); k++) {
						Element artist = aElem.get(k);
						Element title = tElem.get(k);
						String tit=title.attr("title");

						data+=tit.trim()+","+artist.text().trim()+"\n";
						System.out.println(tit.trim()+","+artist.text().trim());
					}
				}
			}
			System.out.println("완료");
			FileWriter fw=new FileWriter("/home/sist/song.txt");
			fw.write(data);
			fw.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return alist;
	}

}