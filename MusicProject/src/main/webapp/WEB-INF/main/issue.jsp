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
		<c:forEach var="vo" items="${rList }" varStatus="s">
		<tr class="dataTr" value="${s.index }">
			<td>${vo.category }</td>
			<td class="song">${vo.title }</td>
			<td>${vo.author }</td>
			<td>${vo.pubDate }</td>
			<td><fmt:formatDate value="${dateFmt}" pattern="yyyy-MM-dd"/></td>
		</tr>
		<tr style="display: none;" id="m${s.index }">
			<td align="left" colspan="4" class="song" bgcolor="#97d5e0">
				<a href="${vo.link}" target="_blank">${vo.description }</a>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
<div id="content_top100" style="margin-top: 10px;margin-bottom:20px">
	<table width="735">
		<tr>
			<td id="chart_rank" valign="middle">
			  <a href="issue.do?page=${curpage>1?curpage-1:curpage}" style="vertical-align: middle;">
			  <img alt="" src="resources/images/f-left.png" style="vertical-align: middle;">&nbsp;&nbsp;&nbsp;prev</a>&nbsp;&nbsp;&nbsp;
				${curpage }&nbsp;page&nbsp;/&nbsp;4&nbsp;pages&nbsp;&nbsp;&nbsp;
			  <a href="issue.do?page=${curpage<totalpage?curpage+1:curpage}" style="vertical-align: middle;">next&nbsp;&nbsp;&nbsp;
			  <img alt="" src="resources/images/f-right.png" style="vertical-align: middle;"></a>
			</td>
		</tr>
	</table>
</div>
<div style="display: block;">
<center><h1 style="margin-bottom: 10px">Issue People</h1></center>
</div>
<div id="content_top100">
	<c:forEach var="vo" items="${niList }">
	<div class="issueArtist">
		<img alt="" src="${vo.poster }" width="150" height="100">
		<div style="width: 205px; padding:3px 5px 3px 5px;float: right;display: inline-block; vertical-align: middle;">
			<div style="height: 60px">
			<span><h3 style="text-decoration: none;color:#555;">${vo.title }</h3></span>
			</div>
			<a href="${vo.url}" style="text-decoration: none;color:#555;">
			<input type="button" value="상세보기" style="margin-left:130px;"></a>
		</div>		
	</div>
	</c:forEach>
</div>
</body>
</html>