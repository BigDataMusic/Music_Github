package com.aclass.mgr;
import java.net.URLEncoder;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;


@Component
public class VideoManager {
  /* public static void main(String[] args) {
      // TODO Auto-generated method stub
      VideoManager vm= new VideoManager();
      vm.getVideo("뮤지컬 매디슨 카운티의 다리");
   }*/
   public List<VideoVO> getVideo(String title){
      List<VideoVO> list = new ArrayList<VideoVO>();
      try {
         //String ucode=URLEncoder.encode(title,"UTF-8");
         //ucode="%EB%AE%A4%EC%A7%80%EC%BB%AC+%EB%A7%A4%EB%94%94%EC%8A%A8+%EC%9A%B4%ED%8B%B0%EC%9D%98+%EB%8B%A4%EB%A6%AC";
         Document doc = Jsoup.connect("https://www.youtube.com/results?search_query="+title).get();
    	  //Document doc = Jsoup.connect("https://www.youtube.com/results?search_query=%EB%93%9C%EB%A6%BC%EA%B1%B8%EC%A6%88(DREAMGIRLS)%20%EC%B5%9C%EC%B4%88%20%EB%82%B4%ED%95%9C").get();
         //System.out.println(title);
         Element video=doc.select("ol.item-section").first();
         String test=video.html();
         Elements iElem=doc.select("span.yt-thumb-simple img");
         
       // System.out.println(test);
         test=test.replaceAll("\n", "").replaceAll("yt-lockup yt-lockup-tile yt-lockup-video vve-check clearfix", "");
         test=test.replaceAll("data-context-item-id=\"", "@@@");
         StringTokenizer id = new StringTokenizer(test,"@@@");
        // System.out.println("aaaaa");
         id.nextToken();
         while(id.hasMoreTokens()){
            VideoVO vo= new VideoVO();
            String temp=id.nextToken();
            //System.out.println(temp);
            String vid=temp.substring(0,temp.indexOf("\""));
            String vimg="";
            
            temp=temp.replaceAll("src", "*****");
            temp=temp.replaceAll("onload", "%%%%%");
            temp=temp.replaceAll("data-thumb", "&&&&&");
            temp=temp.replaceAll("data-ytimg", "!!!!!");
            //System.out.println(temp);
            if(temp.contains("*****"))
            {
               vimg=temp.substring(temp.indexOf("*****")+7);
               vimg=vimg.substring(0,vimg.indexOf("\""));
            }
            if(vimg.length()<80){
               vimg=temp.substring(temp.indexOf("&&&&&")+7);
               vimg=vimg.substring(0,vimg.indexOf("\""));
            }
            vo.setVid(vid);
            vo.setVimg(vimg);
            //System.out.println("vid\t"+vid);
            //System.out.println("vimg\t"+vimg+"\n\n");
            list.add(vo);
         }   
      } catch (Exception e) {
         // TODO: handle exception
         System.out.println("getVideo"+e.getMessage());
      }
      return list;
   }

}