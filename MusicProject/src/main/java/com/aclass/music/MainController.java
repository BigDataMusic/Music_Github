package com.aclass.music;

import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.mapreduce.JobRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aclass.mgr.BugsManager;
import com.aclass.mgr.MusicVO;
import com.aclass.mongodb.MusicDAO;

@Controller
public class MainController {
	@Autowired
	private MusicDAO dao;
	@Autowired
	private BugsManager bmgr;
	@Autowired
	private Configuration conf;
	@Autowired
	private JobRunner jr;
	@RequestMapping("main.do")
	public String main_page(Model model)
	{
		List<MusicVO> bList = bmgr.bugsRankData();
		model.addAttribute("bList", bList);
		return "main";
	}
	@RequestMapping("content.do")
	public String main_content_page()
	{
		return "content";
	}
	@RequestMapping("top100.do")
	public String main_top100()
	{
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
	public String main_issue()
	{
		return "issue";
	}
}
