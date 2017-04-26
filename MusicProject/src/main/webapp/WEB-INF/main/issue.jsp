<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/top100_table.css" />
<title>Insert title here</title>
</head>
<body>
<center><h1 style="margin-bottom: 10px">Issue Topic</h1></center>
<!-- <p id="title_ex" style="margin: 0px;padding: 10px 0;">[음악인] TOP 100</p> -->
<div id="content_top100">
	<table width="735">
		<tr>
			<td align="right" valign="middle"><input type="text"/><input type="button" value="News Search"/></td>
		</tr>
	</table>
	<table id="table2" width="735">
		<tr>
			<th width="10%">글번호</th>
			<th width="45%">제목</th>
			<th width="15%">글쓴이</th>
			<th width="20%">게시일</th>
			<th width="10%">조회수</th>
		</tr>	
		<c:forEach var="i" begin="1" end="15">
		<tr class="dataTr">
			<td>${i}</td>
			<td class="song"><a href="#">뉴스 제목이 들어가는자리입니다.</a></td>
			<td>아티스트</td>
			<td>2017.12.31.</td>
			<td>1000</td>
		</tr>
		</c:forEach>
	</table>
</div>
<div id="content_top100" style="margin-top: 10px;margin-bottom:20px">
	<table width="735">
		<tr>
			<td id="chart_rank">
			&lt;prev&nbsp;&nbsp;&nbsp;
				current&nbsp;page&nbsp;/&nbsp;total&nbsp;pages
			&nbsp;&nbsp;&nbsp;next&gt;
			</td>
		</tr>
	</table>
</div>
<center><h1 style="margin-bottom: 10px">Issue People</h1></center>
<div id="content_top100">
	<div class="issueArtist">
		<img alt="" src="http://music.phinf.naver.net/20170425_198/1493098719862kXihY_JPEG/PC_%B8%AE%BD%BA%C6%AE_190x118.jpg" width="150" height="100">
		<div style="width: 205px; padding:3px 5px 3px 5px;float: right;display: inline-block; vertical-align: middle;">
			<p><span><h3>앨범 발매 프로젝트 노래의 탄생</h3></span><br>
			뮤지션의 창작 활동 지원을 위한 앨범 발매 프로젝트! 시즌1에서 최종 선정된 최재만의 데모곡과 발매곡을 비교 감상해보세요.</p>
		</div>
	</div>
	<div class="issueArtist">
		<img alt="" src="http://music.phinf.naver.net/20170425_198/1493098719862kXihY_JPEG/PC_%B8%AE%BD%BA%C6%AE_190x118.jpg" width="150" height="100">
		<div style="width: 205px; padding:3px 5px 3px 5px;float: right;display: inline-block; vertical-align: middle;">
			<p><span><h3>앨범 발매 프로젝트 노래의 탄생</h3></span><br>
			뮤지션의 창작 활동 지원을 위한 앨범 발매 프로젝트! 시즌1에서 최종 선정된 최재만의 데모곡과 발매곡을 비교 감상해보세요.</p>
		</div>
	</div>
	<div class="issueArtist">
		<img alt="" src="http://music.phinf.naver.net/20170425_198/1493098719862kXihY_JPEG/PC_%B8%AE%BD%BA%C6%AE_190x118.jpg" width="150" height="100">
		<div style="width: 205px; padding:3px 5px 3px 5px;float: right;display: inline-block; vertical-align: middle;">
			<p><span><h3>앨범 발매 프로젝트 노래의 탄생</h3></span><br>
			뮤지션의 창작 활동 지원을 위한 앨범 발매 프로젝트! 시즌1에서 최종 선정된 최재만의 데모곡과 발매곡을 비교 감상해보세요.</p>
		</div>
	</div>
	<div class="issueArtist">
		<img alt="" src="http://music.phinf.naver.net/20170425_198/1493098719862kXihY_JPEG/PC_%B8%AE%BD%BA%C6%AE_190x118.jpg" width="150" height="100">
		<div style="width: 205px; padding:3px 5px 3px 5px;float: right;display: inline-block; vertical-align: middle;">
			<p><span><h3>앨범 발매 프로젝트 노래의 탄생</h3></span><br>
			뮤지션의 창작 활동 지원을 위한 앨범 발매 프로젝트! 시즌1에서 최종 선정된 최재만의 데모곡과 발매곡을 비교 감상해보세요.</p>
		</div>
	</div>
</div>
</body>
</html>