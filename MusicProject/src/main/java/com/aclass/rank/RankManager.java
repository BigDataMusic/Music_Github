package com.aclass.rank;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class RankManager {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RankManager rm = new RankManager();
		rm.naverRankData();
		rm.daumRankData();
		rm.naverIssuData();
	}
	
	public List<RankVO> naverRankData() {
		List<RankVO> nrList = new ArrayList<RankVO>();
		try {
			Document doc = Jsoup.connect("https://www.naver.com/").get();
			Elements tElem = doc.select("ul.ah_l li.ah_item a");
			Elements uElem = doc.select("div:eq(2) ul.ah_l li.ah_item a");

			for (int i = 0; i < 10; i++) {
				RankVO vo = new RankVO();
				Element t = tElem.get(i);
				String tit= t.text().trim();
				int rank=Integer.parseInt(tit.substring(0, tit.indexOf(" ")));
				tit=tit.substring(tit.indexOf(" ")+1).trim();
				
				Element u=uElem.get(i);
				String url=u.attr("href").trim();
				
				vo.setRank(rank);
				vo.setTitle(tit);
				vo.setUrl(url);
				
				nrList.add(vo);
				//System.out.println(vo.getRank()+"위 "+vo.getTitle()+" "+vo.getUrl());
			}
		} catch (Exception ex) {
			System.out.println("naverRankData " + ex.getMessage());
		}
		return nrList;
	}
	
	public List<RankVO> daumRankData() {
		List<RankVO> drList = new ArrayList<RankVO>();
		try {
			Document doc = Jsoup.connect("http://www.daum.net/").get();
			Elements rElem = doc.select("ol.list_hotissue li div.roll_txt div.rank_cont");
			Elements tElem = doc.select("ol.list_hotissue li div.roll_txt div.rank_cont span.txt_issue a.link_issue");
			
			int j=0;
			for (int i = 0; i < 10; i++) {
				RankVO vo = new RankVO();
				Element r = rElem.get(j);
				String rEl= r.text().trim();
				int rank=Integer.parseInt(rEl.substring(0, rEl.indexOf("위")).trim());
				
				Element t = tElem.get(j);
				String tit= t.attr("title").trim();
				String url=t.attr("href").trim();
				
				vo.setRank(rank);
				vo.setTitle(tit);
				vo.setUrl(url);
				
				drList.add(vo);
				//System.out.println(vo.getRank()+"위 "+vo.getTitle()+vo.getUrl());
				j+=2;
			}
		} catch (Exception ex) {
			System.out.println("rankData " + ex.getMessage());
		}
		return drList;
	}
	
	public List<IssueVO> naverIssuData() {
		List<IssueVO> niList = new ArrayList<IssueVO>();
		try {
			Document doc = Jsoup.connect("http://music.naver.com/recommend/specialList.nhn").get();
			Elements tElem = doc.select("ul.pmt_list li dl dt a");
			Elements pElem = doc.select("ul.pmt_list li a.thumb_img img");
			Elements uElem=doc.select("ul.pmt_list li a.thumb_img");
			
			for (int i = 0; i < 4; i++) {
				IssueVO vo = new IssueVO();
				Element t = tElem.get(i);
				Element p=pElem.get(i);
				String poster=p.attr("src");
				Element u=uElem.get(i);
				String url=u.attr("href");
				
				vo.setTitle(t.text());
				vo.setPoster(poster);
				vo.setUrl("http://music.naver.com"+url.trim());
				niList.add(vo);
				
				//System.out.println(vo.getTitle()+"-"+vo.getPoster());
				//System.out.println(vo.getUrl());
			}
		} catch (Exception ex) {
			System.out.println("naverIssuData " + ex.getMessage());
		}
		return niList;
	}

}
