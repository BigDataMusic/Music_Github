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
<center><h1 style="margin-bottom: 10px">[음악인] TOP 100</h1></center>
<!-- <p id="title_ex" style="margin: 0px;padding: 10px 0;">[음악인] TOP 100</p> -->
<div id="content_top100">
	<table id="table1" width="735">
		<tr>
			<th width="5%">순위</th>
			<th width="10%">순위변동</th>
			<th width="10%">앨범자켓</th>
			<th width="45%">곡명</th>
			<th width="15%">아티스트</th>
			<th width="15%">앨범</th>
		</tr>	
		<c:forEach var="vo" items="${vList }">
		<tr class="dataTr">
			<td>${vo.rank}</td>
			<td>${vo.increment}</td>
			<td><a href="content.do?song=${vo.title }&singer=${vo.artist}"><img src="${vo.poster }" class="jacket_thumb100" /></a></td>
			<td class="song">${vo.title }</td>
			<td>${vo.artist }</td>
			<td>${vo.albumname }</td>
		</tr>
		</c:forEach>
	</table>
</div>
<div id="content_top100" style="margin-top: 10px;margin-left: 198px;">
	<table width="735">
		<tr>
			<td id="chart_rank">
				<a href="top100.do?cate=${cate}&page=1">[ 1~10 ]</a>&nbsp;&nbsp;&nbsp;
				<a href="top100.do?cate=${cate}&page=2">[ 11~20 ]</a>&nbsp;&nbsp;&nbsp;
				<a href="top100.do?cate=${cate}&page=3">[ 21~30 ]</a>&nbsp;&nbsp;&nbsp;
				<a href="top100.do?cate=${cate}&page=4">[ 31~40 ]</a>&nbsp;&nbsp;&nbsp;
				<a href="top100.do?cate=${cate}&page=5">[ 41~50 ]</a>&nbsp;&nbsp;&nbsp;
				<a href="top100.do?cate=${cate}&page=6">[ 51~60 ]</a>&nbsp;&nbsp;&nbsp;
				<a href="top100.do?cate=${cate}&page=7">[ 61~70 ]</a>&nbsp;&nbsp;&nbsp;
				<a href="top100.do?cate=${cate}&page=8">[ 71~80 ]</a>&nbsp;&nbsp;&nbsp;
				<a href="top100.do?cate=${cate}&page=9">[ 81~90 ]</a>&nbsp;&nbsp;&nbsp;
				<a href="top100.do?cate=${cate}&page=10">[ 91~100 ]</a>
			</td>
		</tr>
	</table>
</div>
</body>
</html>