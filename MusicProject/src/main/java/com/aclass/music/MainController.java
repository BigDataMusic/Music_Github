package com.aclass.music;

import java.text.SimpleDateFormat;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.hadoop.conf.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.mapreduce.JobRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.json.simple.*;

import java.io.*;

import com.aclass.mgr.*;
import com.aclass.mgr.AlbumVO;
import com.aclass.mgr.BugsManager;
import com.aclass.mgr.MelonManager;
import com.aclass.mgr.MusicVO;
import com.aclass.mongo.MusicManager;
import com.aclass.mongodb.MusicDAO;
import com.aclass.news.*;
import com.aclass.rank.*;
import com.aclass.recom.RecommandDAO;
import com.aclass.review.naver.RManager;
import com.aclass.review.naver.ReviewDAO;
import com.aclass.review.naver.ReviewManager;
import com.aclass.review.naver.SongVO;
import com.aclass.review.naver.SongWhether;
import com.aclass.spark.SparkEmotionManager;
import com.aclass.spark.SparkWeatherManager;

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
	private MnetManager mnetmgr;
	@Autowired
	private GenieManager gmgr;
	@Autowired
	private Configuration conf;
	@Autowired
	private JobRunner jr;
	@Autowired
	private RManager rmanager;	
	@Autowired
	private SparkWeatherManager swgr;
	@Autowired
	private SparkEmotionManager segr;
	@Autowired
	private MusicManager msmgr;
	@Autowired
	private BoardDAO bdao;
	@Autowired
	private ReviewManager reviewmanager;
	@Autowired
	private ReviewDAO reviewdao;
	@Autowired
	private SongWhether songwhether;
	@Autowired
	private RecommandDAO recomdao;
	

	private SparkWeatherManager sparkweather;

	@RequestMapping("main.do")
	public String main_page(String feel, String data,Model model)
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
		List<MusicVO> bList =  dao.getMongoMusicData("musicin");
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
		
		// nav추천
    	if(feel==null)
    	feel="봄";
		List<SongVO> list=recomdao.songRecommandData(feel);
		model.addAttribute("list", list);
		
		return "main";
	}
	@RequestMapping("content.do")
	public String main_content_page(String song,String singer,String title,Model model)
	{
		msmgr.reviewData(title);
		try {
			File dir=new File("/home/sist/feel-data/weather");
			if(dir.exists()){
				File[] lists=dir.listFiles();
				for(File f:lists){
					f.delete();
				}
				dir.delete();
			}
			/*File dir1=new File("/home/sist/feel-data/emotion");
			if(dir1.exists()){
				File[] lists1=dir1.listFiles();
				for(File f:lists1){
					f.delete();
				}
				dir1.delete();
			}*/
		swgr.execute();
		//segr.execute();
		} catch (Exception ex) {
			System.out.println("파일 만들기 : "+ex.getMessage());
		}
		System.out.println(song+ " "+singer);
		reviewdao.naverReviewData(song,singer);
		reviewdao.naverReviewData2(song,singer);
		songwhether.songData(song);
		songwhether.SongWhether(song,singer);
		rmanager.rGraph(song);
		return "content";
	}
	@RequestMapping("top100.do")
	public String main_top100(String cate,String page,Model model)
	{
		if(page==null) page="1";
		if(cate==null) cate="1";
		int start=Integer.parseInt(page)*10-10;
		int end=Integer.parseInt(page)*10;
		
		List<MusicVO> bList=null;
		//음악인
		if(cate.equals("1")) bList= dao.getMongoMusicData("musicin");
		//멜론
		else if(cate.equals("2")) bList=dao.getMongoMusicData("melon");
		//벅스
		else if(cate.equals("3")) bList=dao.getMongoMusicData("bugs");
		//지니
		else if(cate.equals("4")) bList=gmgr.genieRankData();
		//엠넷
		else if(cate.equals("5")) bList=mnetmgr.mnetRankData();
		//네이버
		else if(cate.equals("6")) bList=dao.getMongoMusicData("naver");
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
		model.addAttribute("cate",cate);
		model.addAttribute("vList", vList);
		return "top100";
	}
	@RequestMapping("recommand.do")
	public String main_recommand(String feel,Model model)
	{
		List<SongVO> list=recomdao.songRecommandData(feel);
		List<SongVO> elist=recomdao.emotionRecommandData(feel);
		/*
		 *  [{
		      "category": "가나다라마바사아자차카타파/가나다",
		      "column-1": "1.278"
		    },
		    
		    봄:35,여름:23,화창한날:5,밤/새벽:10
		 */
		List<RecommandFeelVO> fList=
				  new ArrayList<RecommandFeelVO>();
		//System.out.println("list.size():"+list.size());
		try
		{
			int m=0;
			for(SongVO vo:elist)
			{
				if(m>14)break;
				String strFeel=vo.getFeel();
				String[] data=strFeel.split(",");
				for(String s:data)
				{
					StringTokenizer st=new StringTokenizer(s, ":");
					RecommandFeelVO rvo=new RecommandFeelVO();
					rvo.setTitle(vo.getSong());
					rvo.setFeel(st.nextToken());
					rvo.setCount(st.nextToken());
					fList.add(rvo);
				}
				
				m++;
			}
			
		try
		{
			int k=0;
			for(SongVO vo:list)
			{
				if(k>14)break;
				String strFeel=vo.getFeel();
				String[] data=strFeel.split(",");
				for(String s:data)
				{
					StringTokenizer st=new StringTokenizer(s, ":");
					RecommandFeelVO rvo=new RecommandFeelVO();
					rvo.setTitle(vo.getSong());
					rvo.setFeel(st.nextToken());
					rvo.setCount(st.nextToken());
					fList.add(rvo);
				}
				
				k++;
			}
			System.out.println("fList.size():"+fList.size());
			for(RecommandFeelVO ivo:fList)
			{
				for(RecommandFeelVO jvo:fList)
				{
					if(Integer.parseInt(ivo.getCount())>Integer.parseInt(jvo.getCount()))
					{
						String s=jvo.getTitle();
						String f=jvo.getFeel();
						String c=jvo.getCount();
						
						jvo.setTitle(ivo.getTitle());
						jvo.setFeel(ivo.getFeel());
						jvo.setCount(ivo.getCount());
						
						ivo.setTitle(s);
						ivo.setFeel(f);
						ivo.setCount(c);
					}
				}
			}
			
			JSONArray arr=new JSONArray();
			
			for(int i=0;i<fList.size();i++)
			{
				RecommandFeelVO rvo=fList.get(i);
				if(feel.equals(rvo.getFeel()))
				{
					JSONObject obj=new JSONObject();
					obj.put("category", rvo.getTitle());
					obj.put("column-1", rvo.getCount());
					arr.add(obj);
					
				}
			}
		
			
			model.addAttribute("json", arr.toJSONString());
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		model.addAttribute("list", list);
		model.addAttribute("elist", elist);
	 	model.addAttribute("main_jsp", "recommand.jsp");	 	
		return "recommand";
	}
	@RequestMapping("newtracks.do")
	public String main_newtracks(Model model,String page)
	{
		if(page==null) page="1";
		boolean Check=false;
		List<MusicVO> nlist = dao.getMongoMusicData("newMusic");
		List<AlbumVO> alist = dao.AlbumData();
		List<AlbumVO> malist = new ArrayList<AlbumVO>();
		for(int i=0;i<5;i++){
			System.out.println(nlist.get(i).getTitle());
			for(int j=0;j<alist.size();j++){
				//System.out.println(j+" 비교");
				System.out.println(nlist.get(i).getAlno());
				System.out.println(alist.get(j).getAlNo());
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

		
		int start=Integer.parseInt(page)*10-10;
		int end=Integer.parseInt(page)*10;
		List<MusicVO> vList=new ArrayList<MusicVO>();

		for(int i=start;i<10;i++)
		{
			MusicVO nvo=new MusicVO();
			nvo.setN(nlist.get(i).getN());
			nvo.setIncrement(nlist.get(i).getIncrement());
			nvo.setPoster(nlist.get(i).getPoster());
			nvo.setTitle(nlist.get(i).getTitle());
			nvo.setArtist(nlist.get(i).getArtist());
			nvo.setAlbumname(nlist.get(i).getAlbumname());
			nvo.setLyrics(nlist.get(i).getLyrics());
			vList.add(nvo);
		}
		
		model.addAttribute("malist",malist);
		model.addAttribute("nlist",nlist);
		model.addAttribute("vList",vList);
		return "newtracks";
	}
	@RequestMapping("board_insert.do")
	public String board_insert(){
		return "board/board_insert";//forward
	}
	@RequestMapping("board_insert_ok.do")
	public String board_insert_ok(BoardVO vo){
		bdao.boardInsert(vo);
		return "redirect:/board.do";
	}
	@RequestMapping("board.do")
	public String board_list(String page,Model model){
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		List<BoardVO> list=bdao.boardListData(curpage);
		String json=boardListJson(list);
		int totalpage=bdao.boardTotalPage();
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("json", json);
		model.addAttribute("list", list);
		return "board/board";
	}
	@RequestMapping("board_content.do")
	public String board_content(int no,Model model){
		BoardVO vo=bdao.boardGetData(0, no);
		String json=boardDataJson(vo);
		model.addAttribute("json", json);
		return "board/board_content";
	}
	@RequestMapping("board_update.do")
	public String board_update(int no,Model model){
		String json="";
		BoardVO vo=bdao.boardGetData(1, no);
		json=boardDataJson(vo);
		model.addAttribute("json", json);
		return "board/board_update";
	}
	@RequestMapping("board_update_ok.do")
	@ResponseBody
	public String board_update_ok(BoardVO vo){
		String url="";
		boolean bCheck=bdao.boardUpdate(vo);
		if(bCheck==true){
			url="<script>location.href=\"board_content.do?no="+vo.getNo()+"\";"
					+"</script>";
		}else{
			url="<script>alert(\"Password Fail!!\");"
					+"history.back();</script>";
		}
		return url;
	}
	@RequestMapping("board_reply.do")
	   public String board_reply(int no,Model model)
	   {
		   // Model => HttpSevletRequest
		   // addAttribute() => setAttribute()
		   // MVC => forward,sendRedirect
		   // => Controller(DispatcherServlet) => do
		   // MVC (C) ==> MVVC(Domain)
		   model.addAttribute("no", no);
		   return "board/board_reply";// forward
	   }
	   @RequestMapping("board_reply_ok.do")
	   public String board_reply_ok(int pno,BoardVO vo)
	   {
		   // DB (저장)
		   bdao.boardReply(pno, vo);
		   return "redirect:/board.do";
	   }
	   @RequestMapping("board_delete.do")
	   public String board_delete(int no,Model model)
	   {
		   model.addAttribute("no", no);
		   return "board/board_delete";
	   }
	   @RequestMapping("board_delete_ok.do")
	   @ResponseBody
	   public String board_delete_ok(int no,String pwd)
	   {
		   String url="";
		   boolean bCheck=bdao.boardDelete(no, pwd);
		   if(bCheck==true)
		   {
			   url="<script>location.href=\"board.do\";"
					+"</script>";
		   }
		   else
		   {
			   url="<script>alert(\"Password Fail!!\");"
					+"history.back();</script>";
		   }
		   return url;
	   }
	public String boardListJson(List<BoardVO>list){
		String json="";
		try {
			JSONArray arr=new JSONArray();
			// [{},{},{}}
			for(BoardVO vo:list){
				JSONObject obj=new JSONObject();
				obj.put("no", vo.getNo());
				obj.put("name", vo.getName());
				obj.put("subject", vo.getSubject());
				obj.put("regdate", vo.getRegdate());
				obj.put("hit", vo.getHit());
				obj.put("group_tab", vo.getGroup_tab());
				obj.put("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				arr.add(obj);
			}
			json=arr.toString();
		} catch (Exception ex) {
			System.out.println("boardListJson : "+ex.getMessage());
		}
		return json;
	}
	
	public String boardDataJson(BoardVO vo){
		String json="[";
		try {
				/*JSONObject obj=new JSONObject();
				obj.put("no", vo.getNo());
				obj.put("name", vo.getName());
				obj.put("subject", vo.getSubject());
				obj.put("regdate", vo.getRegdate());
				obj.put("hit", vo.getHit());
				obj.put("content", vo.getContent());
				json=obj.toString();*/
				// {no:,name:,subject:...}
			json+=vo.getNo()+",'"+vo.getName()+"','"+vo.getSubject()+"','"+vo.getRegdate()+"','"+vo.getHit()+"','"+vo.getContent()+"']";
			
		} catch (Exception ex) {
			System.out.println("boardListJson : "+ex.getMessage());
		}
		return json;
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
    	List<RankVO> drList=rmgr.daumRankData();
    	List<IssueVO> niList=rmgr.naverIssuData();
    	
    	model.addAttribute("niList", niList);
    	model.addAttribute("nrList", nrList);
    	model.addAttribute("drList", drList);
		model.addAttribute("curpage", curpage);
		model.addAttribute("data", data);
		model.addAttribute("rList", rList);
		return "issue";
	}

	@RequestMapping("lyric.do")
	public String lyrics(String no,Model model){
		List<MusicVO> list = dao.getMongoMusicData("newMusic");
		int n= Integer.parseInt(no);
		MusicVO vo = new MusicVO();
		vo.setPoster(list.get(n).getPoster());
		vo.setTitle(list.get(n).getTitle());
		vo.setArtist(list.get(n).getArtist());
		System.out.println(list.get(n).getLyrics());
		vo.setLyrics(list.get(n).getLyrics().replace("\n", "</br>"));
		System.out.println(list.get(n).getLyrics());
		model.addAttribute("vo",vo);
		return "lyric.empty";
	}
/*
	@PostConstruct
	public void init(){		
		dao.dropTop100();
		dao.insertTop100("melon");
		dao.insertTop100("newMusic");
	}*/
}
