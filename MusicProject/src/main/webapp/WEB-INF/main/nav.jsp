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
			<a href="#"><img src="resources/images/nav_main.png" alt="setalpm" width="198" height="156"/></a><br />
				<span class="slogan">음악의 모든건<br>음악인으로 통한다!</span>
			<p class="text1">1. 상단 로고 변경<br>
			2. 여기에 걸맞는 내용을<br>&nbsp;&nbsp;&nbsp; 넣어야 하고<br>
			3. 위아래 메뉴들 정해야 하고...<br>
			&nbsp;&nbsp;&nbsp;흠냐...</p>
		</div>
		<p id="title_ex">음악인 차트</p>
		<br />
		<ul id="nav">
		  <c:forEach var="vo" items="${bList }">
		    <c:if test="${vo.rank <=10}">
             <li><a href="#">${vo.title }</a></li>
           </c:if>
		  </c:forEach>
		</ul>
		<br />
		<br />
		<br />
		<p id="title_ex">오늘의 추천음악</p>
		<br />
		<p class="color" align="right" style="padding-right: 40px;'">오늘의 날씨 : ㅋㅋㅋ</p>
		<br />
		<ul id="nav">		
			<li><a href="#">Illum secundum</a></li>
			<li><a href="#">Illum secundum</a></li>
			<li><a href="#">Illum secundum</a></li>
			<li><a href="#">Illum secundum</a></li>
			<li><a href="#">Illum secundum</a></li>
		</ul>
		<!-- <ul id="navigation">
			<li class="color"><a href="#">Illum secundum</a></li>
			<li><a href="#">Aenean mturpis</a></li>
			<li class="color"><a href="#">Maecenas hendrerit</a></li>
			<li><a href="#">Tellus er sodales</a></li>
			<li class="color"><a href="#">Illum secundum</a></li>
			<li><a href="#">Aenean mturpis</a></li>
			<li class="color"><a href="#">Maecenas hendrerit</a></li>
			<li><a href="#">Tellus er sodales</a></li>
			<li class="color"><a href="#">Illum secundum</a></li>
			<li><a href="#">Aenean mturpis</a></li>
			<li class="color"><a href="#">Maecenas hendrerit</a></li>
			<li><a href="#">Tellus er sodales</a></li>
			<li class="color"><a href="#">Illum secundum</a></li>
			<li><a href="#">Aenean mturpis</a></li>
			<li class="color"><a href="#">Illum secundum</a></li>
			<li><a href="#">Maecenas hendrerit</a></li>
			<li class="color"><a href="#">Aenean mturpis</a></li>
			<li><a href="#">Illum secundum</a></li>
			<li class="color"><a href="#">Tellus er sodales</a></li>
		</ul> -->
	</div>
</body>
</html>