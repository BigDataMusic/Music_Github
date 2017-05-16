<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
		$(function() {
			$("#accordian h3").click(function() {
				$("#accordian ul ul").slideUp();
				if (!$(this).next().is(":visible")) {
					$(this).next().slideDown();
				}
			});
		});
</script>
</head>
<body>
	<div id="sidebar">
		<div class="logo_block">
			<a href="#"><img src="resources/images/nav_newtracks.png" alt="setalpm"
				width="190" height="156" /></a><br /> <span class="slogan">음악의
				모든건<br>음악인으로 통한다!
			</span>
			<p class="text1">
				1. 상단 로고 변경<br> 2. 여기에 걸맞는 내용을<br>&nbsp;&nbsp;&nbsp; 넣어야
				하고<br> 3. 위아래 메뉴들 정해야 하고...<br> &nbsp;&nbsp;&nbsp;흠냐...
			</p>
		</div>
		<p id="title_ex">신곡(장르별)</p>
		<br />
		<img src="resources/images/chart.jpg" alt="" width="180" height="200" />
		<br />
		<br />
		<br />
		<p id="title_ex">신곡출시가수</p>
		<br/>
		<ul id="navigation" style="padding-top: 0px">
			<c:forEach var="vo" items="${nlist }" varStatus="i">
				<c:if test="${i.index<10}">
					<c:if test="${i.index%2==0 }">
						<li class="color">
					</c:if>
					<c:if test="${i.index%2!=0 }">
						<li>
					</c:if>
					<a href="#">${vo.artist.trim() }</a></li>
				</c:if>
			</c:forEach>
		</ul>	
	</div>	
</body>
</html>