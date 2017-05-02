<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/top100_table.css" />
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
var i=0;
$(function() {
	$(".dataTr").click(function() {
		var no=$(this).attr("value");
		if(i==0)
		{
			$("#m"+no).show();
			i=1;
		}
		else
		{
			$("#m"+no).hide();
			i=0
		}
	});
});
</script>
</head>
<body>
<center><h1 style="margin-bottom: 10px">Issue Topic</h1></center>
<!-- <p id="title_ex" style="margin: 0px;padding: 10px 0;">[음악인] TOP 100</p> -->
<div id="content_top100">
	<table width="735">
		<tr>
			<form method="post" action="issue.do">
			<td align="right" valign="middle"><input type="text" value="${data }"/><input type="button" value="News Search"/></td>
			</form>
		</tr>
	</table>
	<table id="table2" width="735">
		<tr>
			<th width="15%">카테고리</th>
			<th width="55%">제목</th>
			<th width="15%">언론사</th>
			<th width="30%">게시일</th>
			<!-- <th width="10%">조회수</th> -->
		</tr>	
		<c:forEach var="vo" items="${nList }" varStatus="s">
		<tr class="dataTr" value="${s.index }">
			<td>${vo.category }</td>
			<td class="song">${vo.title }</td>
			<td>${vo.author }</td>
			<td>${vo.pubDate }</td>
			<td><fmt:formatDate value="${dateFmt}" pattern="yyyy-MM-dd"/></td>
		</tr>
		<tr style="display: none;" id="m${s.index }">
			<td align="left" colspan="4">
				<a href="${vo.link}" target="_blank">${vo.description }</a>
			</td>
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
<div style="display: block;">
<center><h1 style="margin-bottom: 10px">Issue People</h1></center>
</div>
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