<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<a href="#"><img src="resources/images/nav_recommand.png" alt="setalpm"
				width="198" height="156" /></a><br /> <span class="slogan">음악의
				모든건<br>음악인으로 통한다!
			</span>
			<p class="text1">
				1. 상단 로고 변경<br> 2. 여기에 걸맞는 내용을<br>&nbsp;&nbsp;&nbsp; 넣어야
				하고<br> 3. 위아래 메뉴들 정해야 하고...<br> &nbsp;&nbsp;&nbsp;흠냐...
			</p>
		</div>
		<div id="accordian">
			<ul>
				<li class="active">
					<h3>장르별 추천</h3>
					<ul>
						<li><a href="#">menu01</a></li>
						<li><a href="#">menu02</a></li>
						<li><a href="#">menu03</a></li>
						<li><a href="#">menu04</a></li>
						<li><a href="#">menu05</a></li>
						<li><a href="#">menu06</a></li>
						<li><a href="#">menu07</a></li>
						<li><a href="#">menu08</a></li>
						<li><a href="#">menu09</a></li>
						<li><a href="#">menu10</a></li>
					</ul>
				</li>
				<li>
					<h3>날씨별 추천</h3>
					<ul>
						<li><a href="#">menu01</a></li>
						<li><a href="#">menu02</a></li>
						<li><a href="#">menu03</a></li>
						<li><a href="#">menu04</a></li>
						<li><a href="#">menu05</a></li>
						<li><a href="#">menu06</a></li>
						<li><a href="#">menu07</a></li>
						<li><a href="#">menu08</a></li>
						<li><a href="#">menu09</a></li>
						<li><a href="#">menu10</a></li>
					</ul>
				</li>
				<li>
					<h3>장소별 추천</h3>
					<ul>
						<li><a href="#">menu01</a></li>
						<li><a href="#">menu02</a></li>
						<li><a href="#">menu03</a></li>
						<li><a href="#">menu04</a></li>
						<li><a href="#">menu05</a></li>
						<li><a href="#">menu06</a></li>
						<li><a href="#">menu07</a></li>
						<li><a href="#">menu08</a></li>
						<li><a href="#">menu09</a></li>
						<li><a href="#">menu10</a></li>
					</ul>
				</li>
				<li>
					<h3>상황별 추천</h3>
					<ul>
						<li><a href="#">menu01</a></li>
						<li><a href="#">menu02</a></li>
						<li><a href="#">menu03</a></li>
						<li><a href="#">menu04</a></li>
						<li><a href="#">menu05</a></li>
						<li><a href="#">menu06</a></li>
						<li><a href="#">menu07</a></li>
						<li><a href="#">menu08</a></li>
						<li><a href="#">menu09</a></li>
						<li><a href="#">menu10</a></li>
					</ul>
				</li>
				<li>
					<h3>감정별 추천</h3>
					<ul>
						<li><a href="#">menu01</a></li>
						<li><a href="#">menu02</a></li>
						<li><a href="#">menu03</a></li>
						<li><a href="#">menu04</a></li>
						<li><a href="#">menu05</a></li>
						<li><a href="#">menu06</a></li>
						<li><a href="#">menu07</a></li>
						<li><a href="#">menu08</a></li>
						<li><a href="#">menu09</a></li>
						<li><a href="#">menu10</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>	
</body>
</html>