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
		//m.getAlbumData(10057734);
		//m.getAlbumData(10);
		System.out.println(m.getMaxIndex());
		//m.getAlno100();
	}
	public List<String> getAlno100(){
		List<String> list = new ArrayList<String>();
		try {
			Document doc=Jsoup.connect("http://www.melon.com/chart/index.htm").get();
			Elements alno=doc.select("span.bg_album_frame");
			
			for(Element no : alno){
				//System.out.println(no.attr("onclick").substring(no.attr("onclick").indexOf("'")+1,no.attr("onclick").lastIndexOf("'")));
				list.add(no.attr("onclick").substring(no.attr("onclick").indexOf("'")+1,no.attr("onclick").lastIndexOf("'")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public List<MusicVO> getMelonTop100(){
		List<MusicVO> list= new ArrayList<MusicVO>();
		try {
			Document doc=Jsoup.connect("http://www.melon.com/chart/index.htm").get();
			
			Elements titleElem=doc.select("div.wrap_song_info strong a");
			Elements artElem=doc.select("div.wrap_song_info div div.rank02");
			Elements alElem=doc.select("div.wrap_song_info div div.rank03");
			Elements pElem=doc.select("a.image_type15 img");
			Elements increElem=doc.select("span.wrap_rank");
			int i=0;
			for(int j=6;j<titleElem.size();j++){
				System.out.println("====================================================================");
				//System.out.println(increElem.get(j-6).text());
				String temp=increElem.get(j-6).text();
				String up_down="";
				if(temp.substring(3, 5).contains("상승")){
					up_down="+";
				}
				else if(temp.substring(3, 5).contains("하락")){
					up_down="-";
				}
				String increment=up_down+temp.substring(temp.length()-1);
				System.out.println(increment);
				System.out.println(j-5+"위 "+titleElem.get(j).text()+" - "+artElem.get(j-6).text().substring(0,artElem.get(j-6).text().length()/2)+"-"+alElem.get(j-6).text());
				System.out.println(pElem.get(j-6).attr("src"));
				
				MusicVO vo = new MusicVO();
				vo.setRank(j-5);
				vo.setIncrement(increment);
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
			System.out.println("getMaxIndex "+e.getMessage());
		}
		return max;
	}

	//public List<AlbumVO> getAlbumData(int max){
	public List<AlbumVO> getAlbumData(int z){
		List<AlbumVO> list = new ArrayList<AlbumVO>();
		//int alIndex=10057734;
		//for(int z=max;z>=max-100;z--){
		try {
				Document doc = Jsoup.connect("http://www.melon.com/album/detail.htm?albumId=" + z).get();
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

					// 앨범종류
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
					
					AlbumVO vo = new AlbumVO();
					//곡정보
					List<MusicVO> mList= new ArrayList<MusicVO>();
					Elements mnoElems= doc.select("tr td div input");
					/*for(Element l : lyElems){ 곡번호 출력확인
						System.out.println(l.attr("value"));
					}*/
					for(int k=0;k<musicElement.size();k+=3){
						
						String mt=""; //곡제목
						String tt=""; //title곡 판단변수
						String ly=""; //곡 편집 변수
						//System.out.println(mnoElems.get(k/3).attr("value")); //글번호
						MusicVO mvo = new MusicVO();
					
						//System.out.println(k+"-"+musicElement.get(k).text());
						String temp2=musicElement.get(k).text();
						//System.out.println(temp2.substring(6, temp2.indexOf(" 상세정보 페이지 이동")));
						mt=temp2.substring(6, temp2.indexOf(" 상세정보 페이지 이동"));
						
						//앨범 속 곡제목
						System.out.println("곡제목 "+mt);
						mvo.setTitle(mt);
						//가사
						Document ddoc=Jsoup.connect("http://www.melon.com/song/detail.htm?songId="+mnoElems.get(k/3).attr("value")).get();
						if (ddoc.select("div.section_lyric div.lyric").first() != null) {
							Element lyElem=ddoc.select("div.section_lyric div.lyric").first();
							ly=lyElem.toString();
							//System.out.println("가사\n"+ly.substring(ly.indexOf("--> ")+4, ly.lastIndexOf("<br>")).replaceAll("<br>",""));
							mvo.setLyrics(ly.substring(ly.indexOf("--> ")+4, ly.lastIndexOf("<br>")).replaceAll("<br>",""));
						}
						else{
							mvo.setLyrics("가사정보없음");
							//System.out.println("가사정보없음");
						}
						
						
						String temp3=temp2.substring(temp2.indexOf("페이지 이동")+7);
						//System.out.println(temp3);
						if(temp3.length()>mt.length()){
							//int len=temp3.length()-temp2.length();
							tt=temp3.substring(0,temp3.indexOf(" "));
							//System.out.print(tt);
							//System.out.println("------"+tt+"---------\n");
						}
						//타이틀곡 판단
						if(tt.equalsIgnoreCase("title")){
							mvo.setTit_music(true);
						}
						else mvo.setTit_music(false);
						mList.add(mvo);
						vo.setmList(mList);
					}
	

					// 상세정보
					System.out.println(alInfo);
					vo.setAlNo(z);
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
					
					list.add(vo);
				}

				Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println("getAlbumData "+e.getMessage());
		}
		//}
		
		
		return list;
	}
	public List<ArtistVO> getArtistData(int index){
		List<ArtistVO> list = new ArrayList<ArtistVO>();
		try {
			Document doc = Jsoup.connect("http://www.melon.com/artist/detail.htm?artistId="+index).get();
			if(doc.select("p.title_atist").first()!=null){
				ArtistVO vo = new ArtistVO();
				
				//이름
				Element nameElem=doc.select("p.title_atist").first();
				vo.setAtName(nameElem.text().replace("아티스트명", ""));
				
				//프로필사진
				Element ppElem=doc.select("span#artistImgArea img").first();
				if(ppElem.attr("src").equals("http://cdnimg.melon.co.kr")) vo.setAtPoster("정보없음"); 
				else vo.setAtPoster(ppElem.attr("src"));
				
				//신상정보
				Element atinfoElem = doc.select("div.section_atistinfo04").first();
				if (!atinfoElem.text().trim().equals("신상정보")) {
					String ai=atinfoElem.text().replace("신상정보", "");
					if(ai.contains(" 본명 ")) ai=ai.replace(" 본명 ", "\n본명 ");
					if(ai.contains(" 별명 ")) ai=ai.replace(" 별명 ", "\n별명 ");
					if(ai.contains(" 국적 ")) ai=ai.replace(" 국적 ", "\n국적 ");
					if(ai.contains(" 생일 ")) ai=ai.replace(" 생일 ", "\n생일 ");
					if(ai.contains(" 키/몸무게 ")) ai=ai.replace("키/몸무게", "\n키/몸무게 ");
					if(ai.contains(" 별자리 ")) ai=ai.replace("별자리", "\n별자리 ");
					if(ai.contains(" 혈액형 ")) ai=ai.replace("혈액형", "\n혈액형 ");
					ai=ai.substring(1,ai.length());
					System.out.println(ai);
					vo.setAtInfo(ai);
				}
				else vo.setAtInfo("정보없음");
				/*
				//소개
				if (doc.select("div.section_atistinfo02 div.atist_insdc")!= null) {
					Element introElem = doc.select("div.section_atistinfo02 div.atist_insdc").first();
					vo.setAtIntroduce(introElem.text());
				}
				else vo.setAtIntroduce("정보없음");*/
				
				//활동정보
				if (doc.select("div.section_atistinfo03 dl").first()!= null) {
					Element acinfoElem= doc.select("div.section_atistinfo03 dl").first();
					String ac=acinfoElem.text();
					if(ac.contains("활동년대")) ac=ac.replace("활동년대", "\n활동년대");
					if(ac.contains("유형")) ac=ac.replace("유형", "\n유형");
					if(ac.contains("장르")) ac=ac.replace("장르", "\n장르");
					if(ac.contains("소속사명")) ac=ac.replace("소속사명", "\n소속사명");
					if(ac.contains("소속그룹")) ac=ac.replace("소속그룹", "\n소속그룹");
					vo.setAtActiveInfo(ac);
				}
				else vo.setAtActiveInfo("정보없음");
				
				System.out.println(nameElem.text().replace("아티스트명", ""));
				System.out.println(vo.getAtPoster());
				//System.out.println(introElem.text());
				//System.out.println(ac);
				//System.out.println(ai);
				//System.out.println(atinfoElem.text());
				list.add(vo);
				System.out.println("======================================================================");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getArtistData "+e.getMessage());
		}
		
		return list;
	}
	

	
}
