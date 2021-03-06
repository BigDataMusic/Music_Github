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
			<a href="#"><img src="resources/images/nav_main.png" alt="setalpm" width="190" height="156"/></a><br />
				<span class="slogan">음악의 모든건<br>음악인으로 통한다!</span>
			<p class="text1">음악이 멈추었다...<br>
			 그러자 세상이 멈추었다...<br>
			 <br>
			나를 움직이게 하는 힘 !<br>
			Music makes me high !</p>
		</div>
		<p id="title_ex">음악인 차트</p>
		<br />
		<ul id="nav">
		  <c:forEach var="vo" items="${bList }" varStatus="i">
		    <c:if test="${i.index<10}">
             <li><a href="#">${vo.title }</a></li>
           </c:if>
		  </c:forEach>
		</ul>
		<br />
		<br />
		<br />
		<p id="title_ex">오늘의 추천음악</p>
		<br />
		<p class="color" align="right" style="padding-right: 40px;'">봄과 어울리는 추천음악</p>
		<br />
		<ul id="nav">		
		<c:forEach  var="vo" items="${list }" varStatus="s">
			<c:if test="${s.index<5 }">
				<li><a href="recommand.do?feel=봄">${vo.song } <br>
				&nbsp;&nbsp;-&nbsp;&nbsp; ${vo.singer }</a></li>
			</c:if>
		</c:forEach>
		</ul>
	</div>
</body>
</html>