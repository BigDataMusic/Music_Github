package com.aclass.mgr;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MnetManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MnetManager mm = new MnetManager();
		mm.mnetRankData();
	}

	public List<MusicVO> mnetRankData() {
		List<MusicVO> mList = new ArrayList<MusicVO>();
		SimpleDateFormat date=new SimpleDateFormat("YYYYMMDD");
		try {
			int rank = 1;
			for (int j = 1; j <= 2; j++) {
				Document doc = Jsoup.connect("http://www.mnet.com/chart/TOP100/"+date+"?pNum="+ Integer.toString(j)).get();
				Elements tElem = doc.select("a.MMLI_Song");
				Elements pElem = doc.select("div.MMLITitle_Album a img");
				Elements artElem = doc.select("a.MMLIInfo_Artist:eq(0)");
				Elements alElem = doc.select("a.MMLIInfo_Album");
				//Elements inElem=doc.select("td.MMLItemUpdown div.MMLIUpdown_Box");
				
				for (int i = 0; i < 50; i++) {
					MusicVO vo = new MusicVO();
					
					Element t = tElem.get(i);
					Element pos = pElem.get(i);
					String poster = pos.attr("src");
					Element art = artElem.get(i);
					Element al = alElem.get(i);
					//Element in=inElem.get(i);
					
					vo.setRank(rank);
					vo.setTitle(t.text().trim());
					vo.setPoster(poster.trim());
					vo.setArtist(art.text().trim());
					vo.setAlbumname(al.text().trim());
					
					//String incre=in.text().trim();
					//vo.setIncrement(incre);
					
					/*System.out.println(vo.getRank() + "위 " + vo.getTitle() + " - " + vo.getArtist());
					System.out.println("   " + vo.getPoster());
					System.out.println("   " + vo.getAlbumname());*/
					//System.out.println("   "+vo.getIncrement());
					rank++;
				}
			}

		} catch (Exception ex) {
			System.out.println("mnetRankData " + ex.getMessage());
		}
		return mList;
	}

}
