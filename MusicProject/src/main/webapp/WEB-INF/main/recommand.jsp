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
					<th width="20%">앨범자켓</th>
					<th width="45%">곡명</th>
					<th width="35%">아티스트</th>
					
				</tr>
			 	 <c:forEach var="vo" items="${list }" varStatus="s">
			 	 <c:if test="${s.index<10 }">
					<tr>
						<td><img
								src="${vo.poster }"
								class="jacket_thumb100" /></td>
						<td class="song">${vo.song }</td>
						<td>${vo.singer }</td>
						
					</tr>
					</c:if>
				</c:forEach>
				<c:forEach var="vo" items="${elist }" varStatus="s">
			 	 <c:if test="${s.index<10 }">
					<tr>
						<td><img
								src="${vo.poster }"
								class="jacket_thumb100" /></td>
						<td class="song">${vo.song }</td>
						<td>${vo.singer }</td>
					</tr>
					</c:if>
				</c:forEach>
						
			</table>
		</div>
	</div>
</body>
</html>