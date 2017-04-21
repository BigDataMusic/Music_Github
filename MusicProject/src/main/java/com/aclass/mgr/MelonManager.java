package com.aclass.mgr;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class MelonManager {
	public static void main(String[] args){
		MelonManager m = new MelonManager();
		//m.getMelonTop100();
		//m.getAlbumData(m.getMaxIndex());
		m.getAlbumData();
	}
	public List<MusicVO> getMelonTop100(){
		List<MusicVO> list= new ArrayList<MusicVO>();
		try {
			Document doc=Jsoup.connect("http://www.melon.com/chart/index.htm").get();
			
			Elements titleElem=doc.select("div.wrap_song_info strong a");
			Elements artElem=doc.select("div.wrap_song_info div div.rank02");
			Elements alElem=doc.select("div.wrap_song_info div div.rank03");
			Elements pElem=doc.select("a.image_type15 img");
			int i=0;
			for(int j=6;j<titleElem.size();j++){
				System.out.println(j-5+"위 "+titleElem.get(j).text()+"-"+artElem.get(j-6).text().substring(0,artElem.get(j-6).text().length()/2)+"-"+alElem.get(j-6).text());
				System.out.println(pElem.get(j-6).attr("src"));
				
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
	public int getMaxIndex(){
		int max=0;
		try {
			Document doc=Jsoup.connect("http://www.melon.com/new/album/index.htm#params%5BareaFlg%5D=I&params%5BorderBy%5D=issueDate&po=pageObj&startIndex=1").get();
			Elements albumNoElem=doc.select("dd.wrap_btn a");
			for(Element index : albumNoElem){
				//System.out.println(index.attr("data-album-no"));
				if(max<Integer.parseInt(index.attr("data-album-no"))){
					max=Integer.parseInt(index.attr("data-album-no"));
				}
			}
			//System.out.println("최대:"+max);
		} catch (Exception e) {
			System.out.println("getMaxIndex"+e.getMessage());
		}
		return max;
	}

	//public List<AlbumVO> getAlbumData(int max){
	public List<AlbumVO> getAlbumData(){
		List<AlbumVO> list = new ArrayList<AlbumVO>();
		int alIndex=10052334;
		try {
			//while(true){
				Document doc = Jsoup.connect("http://www.melon.com/album/detail.htm?albumId=" + alIndex).get();
				if (doc.select("p.albumname").first() != null) {
					Element aTypeElement = doc.select("span.gubun").first();
					Element aTitleElement = doc.select("p.albumname").first();
					Element posterElement = doc.select("div.wrap_thumb span img").first();
					Elements temp = doc.select("dl.song_info dd");
					Element pointElement = doc.select("span#gradPointLayer").first();
					Element likeElement = doc.select("span#d_like_count").first();
					Element alInfoElement;
					String alInfo;
					if (doc.select("div.dtl_albuminfo").first() == null) {
						alInfo = "정보없음";
					} else {
						alInfoElement = doc.select("div.dtl_albuminfo").first();
						alInfo = alInfoElement.text();
					}
					/*Elements musicElement = doc.select("td.t_left a");
					for(int k=1;k<musicElement.size();k+=4){
						System.out.println(musicElement.get(k).text());
					}*/
					Elements musicElement = doc.select("td.t_left");
					String mt="";
					String tt="";
					for(int k=0;k<musicElement.size();k+=3){
						//System.out.println(k+"-"+musicElement.get(k).text());
						String temp2=musicElement.get(k).text();
						//System.out.println(temp2.substring(6, temp2.indexOf(" 상세정보 페이지 이동")));
						mt=temp2.substring(6, temp2.indexOf(" 상세정보 페이지 이동"));
						System.out.println("mt="+mt);
						String temp3=temp2.substring(temp2.indexOf("페이지 이동")+7);
						System.out.println("t3="+temp3+"\n");
						if(temp3.length()>mt.length()){
							System.out.println("a");
							//int len=temp3.length()-temp2.length();
							tt=temp3.substring(0,temp3.indexOf(" "));
							System.out.println("------"+tt+"---------");
						}
					}
	
					/*// 앨범종류
					System.out.println(aTypeElement.text());
					// 앨범명
					System.out.println(aTitleElement.text().substring(aTypeElement.text().length() + 5).trim());
					// 포스터
					System.out.println(posterElement.attr("src"));
					// 발매일,발매사,기획사
					System.out.println(temp.get(0).text());// 0~4
					// 평점
					System.out.println(pointElement.text());
					// 좋아요
					System.out.println(likeElement.text());
					// 상세정보
					System.out.println(alInfo);*/
					
					
					
					AlbumVO vo = new AlbumVO();
					vo.setAlNo(alIndex);
					vo.setAlArtist(temp.get(0).text());
					vo.setAlRegdate(temp.get(1).text());
					vo.setSaleCo(temp.get(2).text());
					vo.setEntertainment(temp.get(3).text());
					vo.setAlGenre(temp.get(4).text());
					vo.setAlType(aTypeElement.text());
					vo.setAlTitle(aTitleElement.text().substring(aTypeElement.text().length() + 5).trim());
					vo.setAlPoster(posterElement.attr("src"));
					vo.setAlPoint(Double.parseDouble(pointElement.text()));
					vo.setAlLike(Integer.parseInt(likeElement.text()));
					vo.setAlInfo(alInfo);
					MusicVO mvo = new MusicVO();
					alIndex++;
				}
				else alIndex++;
			//}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getAlbumData "+e.getMessage());
		}
		
		
		return list;
	}
	
}
