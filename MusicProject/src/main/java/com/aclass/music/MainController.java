package com.aclass.music;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("main.do")
	public String main_page()
	{
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
}
