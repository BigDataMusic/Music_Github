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
<div id="content_top100">
	<table id="table1" width="735">
		<tr>
			<th width="5%">순위</th>
			<th width="10%">앨범자켓</th>
			<th width="30%">곡명</th>
			<th width="15%">아티스트</th>
			<th width="15%">발매일</th>
			<th width="10%">장르</th>
			<th width="15%">발매사</th>
		</tr>	
		<c:forEach var="i" begin="1" end="10">
		<tr>
			<td>100</td>
			<td><a href="content.do"><img src="http://cdnimg.melon.co.kr/cm/album/images/100/52/968/10052968_500.jpg" class="jacket_thumb100" /></a></td>
			<td class="song">곡명</td>
			<td>아티스트</td>
			<td>2017.12.31.</td>
			<td>장르</td>
			<td>발매사발매사발매</td>
		</tr>
		</c:forEach>
	</table>
</div>
<div id="content_top100" style="margin-top: 20px;margin-left: 198px;">
	<table width="735">
		<tr>
			<td id="chart_rank">
				<a href="#">[ 1~10 ]</a>&nbsp;&nbsp;&nbsp;
				<a href="#">[ 11~20 ]</a>&nbsp;&nbsp;&nbsp;
				<a href="#">[ 21~30 ]</a>&nbsp;&nbsp;&nbsp;
				<a href="#">[ 31~40 ]</a>&nbsp;&nbsp;&nbsp;
				<a href="#">[ 41~50 ]</a>&nbsp;&nbsp;&nbsp;
				<a href="#">[ 51~60 ]</a>&nbsp;&nbsp;&nbsp;
				<a href="#">[ 61~70 ]</a>&nbsp;&nbsp;&nbsp;
				<a href="#">[ 71~80 ]</a>&nbsp;&nbsp;&nbsp;
				<a href="#">[ 81~90 ]</a>&nbsp;&nbsp;&nbsp;
				<a href="#">[ 91~100 ]</a>
			</td>
		</tr>
	</table>
</div>
</body>
</html>