package com.aclass.music;

import java.util.*;

import org.apache.hadoop.conf.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.mapreduce.JobRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aclass.mgr.BugsManager;
import com.aclass.mgr.MusicVO;
import com.aclass.mongodb.MusicDAO;
import com.aclass.news.*;
import com.aclass.mongodb.*;

@Controller
public class MainController {
	@Autowired
	private NewsManager nmgr;
	@Autowired//@Resource(name = "musicDAO")
	private MusicDAO dao;
	@Autowired
	private BugsManager bmgr;
	@Autowired
	private Configuration conf;
	@Autowired
	private JobRunner jr;
	@RequestMapping("main.do")
	public String main_page(String data,Model model)
	{
		List<MusicVO> bList = bmgr.bugsRankData();
		
		// 뉴스
		if(data==null)
    		data="오늘 음악";
    	List<Item> nList=nmgr.naverNewsData(data);
    	
    	String title="";
    	for(Item n:nList){
        	if(n.getTitle().length()>20){
        		title=n.getTitle().substring(0,20)+"..";
        		n.setTitle(title);
        	}    		
    	}
    	model.addAttribute("nList", nList);
		model.addAttribute("bList", bList);
		return "main";
	}
	@RequestMapping("content.do")
	public String main_content_page()
	{
		return "content";
	}
	@RequestMapping("top100.do")
	public String main_top100(Model model)
	{
		List<MusicVO> bList=bmgr.bugsRankData();
		model.addAttribute("bList", bList);
		return "top100";
	}
	@RequestMapping("recommand.do")
	public String main_recommand()
	{
		return "recommand";
	}
	@RequestMapping("newtracks.do")
	public String main_newtracks()
	{
		return "newtracks";
	}
	@RequestMapping("board.do")
	public String main_board()
	{
		return "../board/board";
	}
	@RequestMapping("issue.do")
	public String main_issue(String page,String data,Model model)
	{
		// 뉴스
		if (data == null)
			data = "오늘 음악";
		List<Item> list = nmgr.naverNewsAllData(data);
		List<NewsVO> nList=new ArrayList<NewsVO>();
		String date="";
		String title="";
		for(Item n:list){
			NewsVO vo=new NewsVO();
			if(n.getTitle().length()>40){
				title=n.getTitle().substring(0,40)+"..";
        		n.setTitle(title);
        		vo.setTitle(n.getTitle());
			}
			else{
				vo.setTitle(n.getTitle());
			}
			if(n.getCategory().trim().equals("섹션없음")){
				n.setCategory("   -   ");
				vo.setCategory(n.getCategory());
			}
			else{
				vo.setCategory(n.getCategory());
			}
			
			vo.setAuthor(n.getAuthor());
			vo.setDescription(n.getDescription());
			vo.setLink(n.getLink());
			
			date=n.getPubDate().substring(n.getPubDate().indexOf(",")+2,16);
			n.setPubDate(date);
			vo.setPubDate(n.getPubDate());
			
			nList.add(vo);
    	}
		
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int i=0;
		int j=0;
		int pagecnt=(curpage*10)-10;
		List<NewsVO> rList=new ArrayList<NewsVO>();
		for (NewsVO vo:nList) {
			if (i < 10 && j >= pagecnt) {
				rList.add(vo);
				i++;
			}
			j++;
		}		
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("data", data);
		model.addAttribute("rList", rList);
		return "issue";
	}
}
