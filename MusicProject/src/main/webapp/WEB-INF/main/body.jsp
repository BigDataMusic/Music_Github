<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://code.highcharts.com/highcharts.js'></script>
<script src='http://code.highcharts.com/modules/exporting.js'></script>
<script src="resources/js/index.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/chartstyles.css">
</head>
<body>
	<div id="content">
		<div class="bg">
			<div class="column3">
				<div id="container"></div>
				<div class="column1">
				<p id="title_ex" style="margin: 0px;padding: 10px 0;">HOT TRACKS</p>
				<div id="items">
					<c:forEach var="vo" items="${slist }" varStatus="i">
						<c:if test="${i.index<4 }">
							<div class="item">
								<a href="#"><img src="${vo.alPoster }" class="jacket_thumb" /></a>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<p id="title_ex" style="margin: 0px;padding: 10px 0;">HOT M/V</p>
				<iframe id="mv" src="https://www.youtube.com/embed/${vid }" frameborder="0" allowfullscreen></iframe>
			</div>
			</div>
			<div class="column4">
				<p id="title_ex" style="margin: 0px;padding: 0;">종합 순위 차트</p>
				<ul id="navigation1">
					<li class="color"><a href="top100.do?cate=1&page=1" style="text-decoration: none; color: #555">음악인 차트</a></li>
					<li class="color"><a href="top100.do?cate=2&page=1" style="text-decoration: none; color: #555">멜론 차트</a></li>
					<li class="color"><a href="top100.do?cate=3&page=1" style="text-decoration: none; color: #555">벅스 차트</a></li>
					<li class="color"><a href="top100.do?cate=4&page=1" style="text-decoration: none; color: #555">지니 차트</a></li>
					<li class="color"><a href="top100.do?cate=5&page=1" style="text-decoration: none; color: #555">엠넷 차트</a></li>
					<li class="color"><a href="top100.do?cate=6&page=1" style="text-decoration: none; color: #555">네이버 뮤직 차트</a></li>
				</ul>
				<div style="height: 30px"></div>
				<p id="title_ex" style="margin: 0px;padding: 0;">HOT ISSUE</p>
				<div class="news">
					<span>NEWS</span><br />
					<c:forEach var="vo" items="${nList }">
					  <p><a href="${vo.link }" style="text-decoration: none; color: #555">${vo.title }</a></p>
					</c:forEach>
					<a href="${vo.url }" class="more">more info</a>
				</div>
				<div class="news">
					<span>Artist</span><br />
					<c:forEach var="vo" items="${niList }" varStatus="status">
					<c:if test="${status.count<='2'}">
					  <a href=${vo.url } style="text-decoration: none; color: #555">
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