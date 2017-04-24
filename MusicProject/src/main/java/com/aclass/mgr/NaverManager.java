package com.aclass.mgr;

import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class NaverManager {

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      NaverManager nm = new NaverManager();
      nm.naverRankData();
   }

   public List<MusicVO> naverRankData() {
      List<MusicVO> nList = new ArrayList<MusicVO>();
      try {
         int rank=1;
         for(int j=1;j<=2;j++){
            Document doc = Jsoup.connect("http://music.naver.com/listen/top100.nhn?domain=TOTAL&page="+Integer.toString(j)).get();
            Elements tElem = doc.select("a._title span.ellipsis");
            Elements pElem = doc.select("td.name a.thumb img");
            Elements artElem = doc.select("td._artist a");
            //Elements alElem = doc.select("td.left a.album");
            Elements iElem = doc.select("td.change");
            
            for (int i = 0; i < 50; i++) {
               Element t = tElem.get(i);
               Element pos = pElem.get(i);
               String poster = pos.attr("src");
               Element art = artElem.get(i);
               //Element al = alElem.get(i);
               Element in = iElem.get(i);
   
               MusicVO vo = new MusicVO();
               vo.setRank(rank);
               vo.setTitle(t.text().trim());
               vo.setPoster(poster.trim());
               vo.setArtist(art.text().trim());
               //vo.setAlbumname(al.text().trim());
               vo.setIncrement(in.text());
               // list.add(vo);
               
               System.out.println(vo.getRank()+"위 "+vo.getTitle()+" - "+vo.getArtist());
               System.out.println("   "+vo.getPoster());
               System.out.println("   "+vo.getIncrement());
               //System.out.println("   "+vo.getAlbumname());
               
               rank++;
            }
         }

      } catch (Exception ex) {
         System.out.println("naverRankData " + ex.getMessage());
      }
      return nList;
   }
}