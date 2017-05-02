package com.aclass.mgr;

import java.util.*;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SongDataManager2 {

	List<AlbumVO> alist = new ArrayList<AlbumVO>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SongDataManager2 sm = new SongDataManager2();
		sm.songYearData();
	}

	public List<AlbumVO> songYearData() {
		String data="";
		try {
			String[] genre = { "kpop", "fost", "dramaost" };// "pop", 
			for (int i = 0; i < genre.length; i++) {
				Document doc = Jsoup.connect("http://music.bugs.co.kr/years/" + genre[i]).get();
				Elements yearElem = doc.select("section.sectionPadding div.innerContainer ul li figcaption.info a.title");
				for (int j = 0; j < yearElem.size(); j++) {
					Element yearNo = yearElem.get(j);
					Element yearInfo = yearElem.get(j);
					String year = yearNo.attr("href");
					System.out.println("========================================"+ yearInfo.text() + "========================================\n");

					Document doc2 = Jsoup.connect(year).get();
					Elements aElem = doc2.select("td.left p.artist:eq(0)");
					Elements tElem = doc2.select("td.check input");
					for (int k = 0; k < tElem.size(); k++) {
						Element artist = aElem.get(k);
						String art=artist.text().replace(",", "");
						Element title = tElem.get(k);
						String tit=title.attr("title").replace("", "");

						data+="@t@i@t@l@e@"+tit.trim()+"@a@r@t@i@s@t@"+artist.text().trim()+"\n";
						System.out.println("@t@i@t@l@e@"+tit.trim()+"@a@r@t@i@s@t@"+artist.text().trim());
					}
					data+="\n"+"=============="+ yearInfo.text() + "============="+"\n";
					
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