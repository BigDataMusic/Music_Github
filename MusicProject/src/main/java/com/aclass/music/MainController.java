package com.aclass.music;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.hadoop.conf.Configuration;
import org.apache.xerces.xs.ItemPSVI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.mapreduce.JobRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aclass.mgr.BugsManager;
import com.aclass.mgr.MusicVO;
import com.aclass.mongodb.MusicDAO;
import com.aclass.news.*;

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
	@RequestMapping("issue.do")
	public String main_issue(String data,Model model)
	{
		// 뉴스
		if (data == null)
			data = "오늘 음악";
		List<Item> nList = nmgr.naverNewsAllData(data);
		
		String date="";
		String title="";
		for(Item n:nList){
			date=n.getPubDate().substring(n.getPubDate().indexOf(",")+2,16);
			if(n.getTitle().length()>40){
				title=n.getTitle().substring(0,40)+"..";
        		n.setTitle(title);
			}
			if(n.getCategory().trim().equals("섹션없음")){
				n.setCategory("   -   ");
				System.out.println(n.getCategory());
			}
			/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    	ParsePosition pos=new ParsePosition(i);
			Date sampleDate=sdf.parse(date,pos);
	    	System.out.println(sampleDate.toString());*/
			n.setPubDate(date);
    	}
    	model.addAttribute("nList", nList);
		return "issue";
	}
}
