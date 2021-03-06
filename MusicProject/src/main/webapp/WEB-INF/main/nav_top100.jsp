<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="sidebar">
		<div class="logo_block">
			<a href="#"><img src="resources/images/nav_top100.png" alt="setalpm" width="190" height="156"/></a><br />
				<span class="slogan">음악의 모든건<br>음악인으로 통한다!</span>
			<p class="text1">
			국내 유수의 차트를 정리하고<br>
			음악인만의 포인트를 부여하여<br>
			음악인만의 랭킹을 <br>
			한땀한땀 정성들여 만듭니다.<br>
			Music is Life~!!</p>
		</div>
		<p id="title_ex">음원 차트</p>
		<br />
		<ul id="navigation">
			<li class="color"><a href="top100.do?cate=1&page=1">음악인 차트</a></li>
			<li><a href="top100.do?cate=2&page=1">멜론 차트</a></li>
			<li class="color"><a href="top100.do?cate=3&page=1">벅스 차트</a></li>
			<li><a href="top100.do?cate=4&page=1">지니 차트</a></li>
			<li class="color"><a href="top100.do?cate=5&page=1">엠넷 차트</a></li>
			<li><a href="top100.do?cate=6&page=1">네이버 뮤직 차트</a></li>
		</ul>
		<br />
		<br />
		<br />
		<p id="title_ex">오늘의 추천음악</p>
		<br />
		<p class="color" align="right" style="padding-right: 40px;'">봄과 어울리는 추천음악</p>
		<br />
		<ul id="nav">		
		<c:forEach var="vo" items="${list }" varStatus="s">
			<c:if test="${s.index<5 }">
				<li><a href="recommand.do?feel=봄">${vo.song } <br>
				&nbsp;&nbsp;-&nbsp;&nbsp; ${vo.singer }</a></li>
			</c:if>
		</c:forEach>
		</ul>
	</div>
</body>
</html>