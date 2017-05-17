<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/top100_table.css" />
</head>
<body>
	<div id="content">
		<center><h1 style="margin-bottom: 10px">[장르/날씨...] 추천음악 Best10</h1></center>
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
			 
					
					<c:forEach var="vo" items="${mList }" varStatus="s">
					                        <c:if test="${s.index<5 }">
					                        <c:forEach var="i" begin="1" end="5">
					                        <tr>
					            <td>${i}</td>
						<td><a href="content.do"><img
								src="http://cdnimg.melon.co.kr/cm/album/images/100/52/968/10052968_500.jpg"
								class="jacket_thumb100" /></a></td>
						<td class="song">곡명</td>
						<td>아티스트</td>
						<td>2017.12.31.</td>
						<td>장르</td>
						<td>발매사발매사발매</td>
					</tr>

				  
				</c:forEach>
</c:if>
					</c:forEach>
						<%-- <td>${i}</td>
						<td><a href="content.do"><img
								src="http://cdnimg.melon.co.kr/cm/album/images/100/52/968/10052968_500.jpg"
								class="jacket_thumb100" /></a></td>
						<td class="song">곡명</td>
						<td>아티스트</td>
						<td>2017.12.31.</td>
						<td>장르</td>
						<td>발매사발매사발매</td> --%>
					
			</table>
		</div>
	</div>
</body>
</html>