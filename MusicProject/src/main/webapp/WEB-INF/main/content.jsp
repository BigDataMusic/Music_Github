<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Paradise</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
</head>

<body>
	<div id="content">
		<!-- <div class="search">
			<span>Search</span> <input type="text" />
		</div> -->
		<div class="column_dc">
			<!-- <div class="tree">
				<a href="#">Tours</a> &raquo; Maldives
			</div> -->
			<!-- <img src="resources/images/title5.gif" alt="" width="255" height="19" /><br /> -->
			<p id="title_ex" style="margin: 0px;padding: 10px 0;">${vo.song } / ${vo.singer }</p>
			<div class="gallery">
				<iframe id="mv" src="https://www.youtube.com/embed/dMn509ddAkc" frameborder="0" allowfullscreen></iframe>
				<p id="title_ex" style="margin: 0px;padding: 10px 0;">가수의 다른곡</p>
				<div>
					<a href="#" class="arrow"><img src="resources/images/arrow_l.gif" alt="" width="10" height="96" /></a>
					<div>
						<a href="#"><img src="resources/images/photo1.jpg" alt="" width="78" height="78" /></a>
						<a href="#"><img src="resources/images/photo1.jpg" alt="" width="78" height="78" /></a>
						<a href="#"><img src="resources/images/photo1.jpg" alt="" width="78" height="78" /></a>
						<a href="#"><img src="resources/images/photo1.jpg" alt="" width="78" height="78" /></a>
						<a href="#"><img src="resources/images/photo1.jpg" alt="" width="78" height="78" /></a>
						<a href="#"><img src="resources/images/photo1.jpg" alt="" width="78" height="78" /></a>
						<a href="#"><img src="resources/images/photo1.jpg" alt="" width="78" height="78" /></a>
						<a href="#"><img src="resources/images/photo1.jpg" alt="" width="78" height="78" /></a>
						<a href="#"><img src="resources/images/photo1.jpg" alt="" width="78" height="78" /></a>
						<a href="#"><img src="resources/images/photo1.jpg" alt="" width="78" height="78" /></a>
					</div>
					<a href="#" class="arrow"><img src="resources/images/arrow_r.gif" alt="" width="10" height="96" /></a>
				</div>
				
				<p id="title_ex" style="margin-top: 0;padding: 10px 0;">순위 변동</p>
				<img src="resources/images/graph1.jpg" alt="" width="480" height="270" />
			</div>			
		</div>
		<div class="column_dc2">
			<p id="title_ex" style="margin: 0px;padding: 10px 0;">곡 정보</p>
			<!-- <img src="http://sstatic.naver.net/people/portrait/201704/20170417141627678.jpg" alt="" width="200" height="270"/>	 -->
			
			
			<img src="${vo.poster }" width="80" height="80">
			<img src="${vo.poster }" width="80" height="80"><br>
			<img src="${vo.poster }" width="80" height="80">
			<img src="${vo.poster }" width="80" height="80"><br>
			
			<p class="info">
			<br>
			${vo.singer }<br>
			${vo.song } <br> 
			${vo.album } <br> 
			</p>
			<a href="#" class="button">more info</a>
			<p id="title_ex" style="margin: 0px; padding: 10px 0;">이럴때 추천</p>
			<img src="resources/images/wordcloud.png" alt="" width="200" height="200" />
			<div style="height: 10px"></div>
			<img src="resources/images/susubar.png" alt="" width="200" height="200" />
		</div>
	</div>
</body>
</html>
