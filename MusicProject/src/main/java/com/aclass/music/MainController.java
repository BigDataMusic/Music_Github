package com.aclass.music;

import java.util.*;

import javax.annotation.Resource;

import org.apache.hadoop.conf.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.mapreduce.JobRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aclass.mgr.AlbumVO;
import com.aclass.mgr.BugsManager;
import com.aclass.mgr.MelonManager;
import com.aclass.mgr.MusicVO;
import com.aclass.mongodb.MusicDAO;
import com.aclass.news.*;
import com.aclass.rank.*;
import com.aclass.rank.RankVO;
import com.aclass.review.naver.RManager;
import com.aclass.review.naver.ReviewDAO;
import com.aclass.review.naver.ReviewManager;
import com.aclass.review.naver.SongVO;
import com.aclass.mongodb.*;

@Controller
public class MainController{
	@Autowired
	private RankManager rmgr;
	@Autowired
	private NewsManager nmgr;
	@Autowired//@Resource(name = "musicDAO")
	private MusicDAO dao;
	@Autowired
	private BugsManager bmgr;
	@Autowired
	private MelonManager mmgr;
	@Autowired
	private Configuration conf;
	@Autowired
	private JobRunner jr;
	
	@Autowired
	private RManager rmanager;
	@Autowired
	private ReviewManager reviewmanager;
	@Autowired
	private ReviewDAO reviewdao;
	
	@RequestMapping("main.do")
	public String main_page(String data,Model model)
	{
		// 음악인차트
		/*List<MusicVO> bList = bmgr.bugsRankData();
		String mTitle="";
		for(MusicVO b:bList){
			if(b.getTitle().length()>15){
				mTitle=b.getTitle().substring(0,15)+"..";
        		b.setTitle(mTitle);
			}
		}*/
		List<MusicVO> bList = dao.bugsMusicData();
		String mTitle="";
		for(MusicVO b:bList){
			if(b.getTitle().length()>15){
				mTitle=b.getTitle().substring(0,15)+"..";
				b.setTitle(mTitle);
			}
		}
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
    	// Artist
    	List<IssueVO> niList=rmgr.naverIssuData();
    	
    	model.addAttribute("niList", niList);
    	model.addAttribute("nList", nList);
		model.addAttribute("bList", bList);
		return "main";
	}
	@RequestMapping("content.do")
	public String main_content_page(String song,String singer)
	{
		reviewdao.naverReviewData(song,singer);
		reviewdao.naverReviewData2(song,singer);
		rmanager.rGraph(song);
		return "content";
	}
	@RequestMapping("top100.do")
	public String main_top100(String page,Model model)
	{
		if(page==null)
			page="1";
		int start=Integer.parseInt(page)*10-10;
		int end=Integer.parseInt(page)*10;
		
		List<MusicVO> bList=bmgr.bugsRankData();
		List<MusicVO> vList=new ArrayList<MusicVO>();
		
		for(int i=start;i<end;i++)
		{
			MusicVO nvo=new MusicVO();
			nvo.setRank(bList.get(i).getRank());
			nvo.setIncrement(bList.get(i).getIncrement());
			nvo.setPoster(bList.get(i).getPoster());
			nvo.setTitle(bList.get(i).getTitle());
			nvo.setArtist(bList.get(i).getArtist());
			nvo.setAlbumname(bList.get(i).getAlbumname());
			vList.add(nvo);
		}
			
		model.addAttribute("vList", vList);
		return "top100";
	}
	@RequestMapping("recommand.do")
	public String main_recommand(Model model)
	{
		List<SongVO> list = reviewmanager.songData();
		
	 	model.addAttribute("main_jsp", "recommand.jsp");
		model.addAttribute("list", list);
		return "recommand";
	}
	@RequestMapping("newtracks.do")
	public String main_newtracks(Model model)
	{
		boolean Check=false;
		List<MusicVO> nlist = dao.newMusicData();
		List<AlbumVO> alist = dao.AlbumData();
		List<AlbumVO> malist = new ArrayList<AlbumVO>();
		for(int i=0;i<5;i++){
			System.out.println(nlist.get(i).getAlno());
			for(int j=0;j<alist.size();j++){
				System.out.println(j+" 비교");
				if(nlist.get(i).getAlno().equals(Integer.toString(alist.get(j).getAlNo()))){	
					System.out.println("있는 앨범정보");
					malist.add(alist.get(j));
					Check=true;
					break;
				}
				else if(j==alist.size()-1){
					System.out.println("없는 앨범정보");
					dao.albumInsert(Integer.parseInt(nlist.get(i).getAlno()));
					malist.add(mmgr.getAlbumData(Integer.parseInt(nlist.get(i).getAlno())));
					//dao.getAlbumData(nlist.get(i).getAlno());
				}
			}
		}
		model.addAttribute("malist",malist);
		model.addAttribute("nlist",nlist);
		return "newtracks";
	}
	@RequestMapping("board.do")
	public String main_board()
	{
		return "board/board";
	}
	@RequestMapping("board_content.do")
	public String main_board_content()
	{
		return "board/board_content";
	}
	@RequestMapping("board_insert.do")
	public String main_board_insert()
	{
		return "board/board_insert";
	}
	@RequestMapping("board_update.do")
	public String main_board_update()
	{
		return "board/board_update";
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
		int pagecnt=(curpage*14)-14;
		List<NewsVO> rList=new ArrayList<NewsVO>();
		for (NewsVO vo:nList) {
			if (i < 14 && j >= pagecnt) {
				rList.add(vo);
				i++;
			}
			j++;
		}		
		
    	List<RankVO> nrList=rmgr.naverRankData();
    	List<RankVO> drList=rmgr.naverRankData();
    	List<IssueVO> niList=rmgr.naverIssuData();
    	
    	model.addAttribute("niList", niList);
    	model.addAttribute("nrList", nrList);
    	model.addAttribute("drList", drList);
		model.addAttribute("curpage", curpage);
		model.addAttribute("data", data);
		model.addAttribute("rList", rList);
		return "issue";
	}
}
