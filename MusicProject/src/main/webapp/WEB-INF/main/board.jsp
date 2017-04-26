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
<center><h1 style="margin-bottom: 10px">Free Board</h1></center>
<!-- <p id="title_ex" style="margin: 0px;padding: 10px 0;">[음악인] TOP 100</p> -->
<div id="content_top100">
	<table width="735">
		<tr>
			<td align="right" valign="middle"><input type="button" value="write"/></td>
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
		<c:forEach var="i" begin="1" end="25">
		<tr class="dataTr">
			<td>${i}</td>
			<td class="song"><a href="#">게시물 제목이 들어가는 자리입니다.</a></td>
			<td>아티스트</td>
			<td>2017.12.31.</td>
			<td>1000</td>
		</tr>
		</c:forEach>
	</table>
</div>
<div id="content_top100" style="margin-top: 10px;">
	<table width="735">
		<tr>
			<td id="chart_rank">
			&lt;&lt;&nbsp;&nbsp;&nbsp;&lt;&nbsp;&nbsp;&nbsp;
				<c:forEach var="i" begin="1" end="10">
					<a href="#">[ ${i} ]</a>&nbsp;
				</c:forEach>
			&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&gt;&gt;
			</td>
		</tr>
		<tr>
			<td id="chart_rank">
				current&nbsp;page&nbsp;/&nbsp;total&nbsp;pages
			</td>
		</tr>
	</table>
</div>
</body>
</html>