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
	<div id="content">
		<!-- <div class="search">
			<span>Search</span> <input type="text" />
		</div> -->
		<div class="bg">
			<div class="column3">
				<img src="resources/images/graph1.jpg" alt="" width="480" height="200" />
			</div>
			<div class="column4">
				<p id="title_ex" style="margin: 0px;padding: 0;">종합 순위 차트</p>
				<ul id="navigation1">
					<li class="color"><a href="#">음악인 차트</a></li>
					<li><a href="#">멜론 차트</a></li>
					<li class="color"><a href="#">벅스 차트</a></li>
					<li><a href="#">지니 차트</a></li>
					<li class="color"><a href="#">엠넷 차트</a></li>
					<li><a href="#">네이버 뮤직 차트</a></li>
				</ul>
			</div>
		</div>
		<div class="bg">
			<div class="column1">
				<p id="title_ex" style="margin: 0px;padding: 10px 0;">HOT TRACKS - Album</p>			
				<div id="items">
					<div class="item">
						<a href="#"><img src="http://cdnimg.melon.co.kr/cm/album/images/100/52/968/10052968_500.jpg" class="jacket_thumb" /></a>
					</div>
					<div class="item">
						<a href="#"><img src="http://cdnimg.melon.co.kr/cm/album/images/100/53/651/10053651_500.jpg" class="jacket_thumb" /></a>
					</div>
					<div class="item">
						<a href="#"><img src="http://cdnimg.melon.co.kr/cm/album/images/100/51/659/10051659_500.jpg" class="jacket_thumb" /></a>
					</div>
					<div class="item">
						<a href="#"><img src="http://cdnimg.melon.co.kr/cm/album/images/100/47/890/10047890_500.jpg" class="jacket_thumb" /></a>
					</div>
				</div>
				<p id="title_ex" style="margin: 0px;padding: 10px 0;">HOT TRACKS - Single</p>
				<div id="items">
					<div class="item">
						<a href="#"><img src="http://cdnimg.melon.co.kr/cm/album/images/100/52/968/10052968_500.jpg" class="jacket_thumb" /></a>
					</div>
					<div class="item">
						<a href="#"><img src="http://cdnimg.melon.co.kr/cm/album/images/100/53/651/10053651_500.jpg" class="jacket_thumb" /></a>
					</div>
					<div class="item">
						<a href="#"><img src="http://cdnimg.melon.co.kr/cm/album/images/100/51/659/10051659_500.jpg" class="jacket_thumb" /></a>
					</div>
					<div class="item">
						<a href="#"><img src="http://cdnimg.melon.co.kr/cm/album/images/100/47/890/10047890_500.jpg" class="jacket_thumb" /></a>
					</div>
				</div>
				<p id="title_ex" style="margin: 0px;padding: 10px 0;">HOT M/V</p>
				<iframe id="mv" src="https://www.youtube.com/embed/dMn509ddAkc" frameborder="0" allowfullscreen></iframe>
			</div>
			<div class="column2">
				<p id="title_ex" style="margin: 0px;padding: 0;">HOT ISSUE</p>
				<div class="news">
					<span>NEWS</span><br />
					<c:forEach var="vo" items="${nList }">
					  <p><a href="${vo.link }">${vo.title }</a></p>
					</c:forEach>
					<a href="${vo.url }" class="more">more info</a>
				</div>
				<div class="news">
					<span>Artist</span><br />
					<c:forEach var="vo" items="${niList }" varStatus="status">
					<c:if test="${status.count<='2'}">
					  <a href=${vo.url }>
					    <img src="${vo.poster }" alt="" width="183" height="110" />
					    <p>${vo.title }</p>
					  </a>
					</c:if>
					</c:forEach>
					<a href="${vo.url }" class="more">more info</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>