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
		<div class="logo_block" style="margin-bottom: 10px">
			<a href="#"><img src="resources/images/nav_issue.png" alt="setalpm" width="190" height="156"/></a><br />
				<span class="slogan">음악의 모든건<br>음악인으로 통한다!</span>
			<p class="text1">음악이 멈추었다...<br>
			 그러자 세상이 멈추었다...<br>
			 <br>
			나를 움직이게 하는 힘 !<br>
			Music makes me high !</p>
		</div>
		<p id="title_ex">NAVER 실시간 검색</p>
		<br/>
		<ul id="navigation" style="padding-top: 0px;list-style-type: decimal;">
		<c:forEach var="vo" items="${nrList }" varStatus="s">
			<c:if test="${s.index%2!=0 }"><li class="color"></c:if>
			<c:if test="${s.index%2==0 }"><li></c:if>
			${vo.rank }&nbsp;&nbsp;&nbsp;&nbsp;<a href="${vo.url }">${vo.title }</a></li>
		</c:forEach>
		</ul>
		<br />
		<br />
		<br />
		<p id="title_ex">DAUM 실시간 검색</p>
		<br />
		<ul id="navigation" style="padding-top: 0px;list-style-type: decimal;">
		<c:forEach var="vo" items="${drList }" varStatus="s">
			<c:if test="${s.index%2!=0 }"><li class="color"></c:if>
			<c:if test="${s.index%2==0 }"><li></c:if>
			${vo.rank }&nbsp;&nbsp;&nbsp;&nbsp;<a href="${vo.url }">${vo.title }</a></li>
		</c:forEach>
		</ul>		
	</div>
</body>
</html>