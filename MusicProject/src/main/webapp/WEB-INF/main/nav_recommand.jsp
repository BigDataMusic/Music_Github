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
				width="190" height="156" /></a><br /> <span class="slogan">음악의
				모든건<br>음악인으로 통한다!
			</span>
			<!-- <p class="text1">
				1. 상단 로고 변경<br> 2. 여기에 걸맞는 내용을<br>&nbsp;&nbsp;&nbsp; 넣어야
				하고<br> 3. 위아래 메뉴들 정해야 하고...<br> &nbsp;&nbsp;&nbsp;흠냐...
			</p> -->
		</div>
		<div id="accordian">
			<ul>
				<li class="active">
				<h3>날씨별 추천</h3>
					<ul>
						<li><a href="recommand.do?feel=봄">봄</a></li>
						<li><a href="recommand.do?feel=여름">여름</a></li>
						<li><a href="recommand.do?feel=가을">가을</a></li>
						<li><a href="recommand.do?feel=겨울">겨울</a></li>
						<li><a href="recommand.do?feel=화창한날">화창한날</a></li>
						<li><a href="recommand.do?feel=아침">아침</a></li>
						<li><a href="recommand.do?feel=오후">오후</a></li>
						<li><a href="recommand.do?feel=저녁">저녁</a></li>
						<li><a href="recommand.do?feel=밤/새벽">밤/새벽</a></li>
						<li><a href="recommand.do?feel=비/흐림">비/흐림</a></li>
						<li><a href="recommand.do?feel=크리스마스">크리스마스</a></li>
						<li><a href="recommand.do?feel=눈오는날">눈오는 날</a></li>
					</ul>
					<h3>감정별 추천</h3>
					<ul>
						<li><a href="recommand.do?feel=사랑/기쁨">사랑/기쁨</a></li>
						<li><a href="recommand.do?feel=이별/슬픔">이별/슬픔</a></li>
						<li><a href="recommand.do?feel=스트레스/짜증">스트레스/짜증</a></li>
						<li><a href="recommand.do?feel=우울할때">우울할때</a></li>
						<li><a href="recommand.do?feel=멘붕/불안">멘붕/불안</a></li>
						<li><a href="recommand.do?feel=외로울때">외로울때</a></li>
					</ul>
				</li>
				<!-- <li>
					<h3>감정별 추천</h3>
					<ul>
						<li><a href="recommand.do?feel=사랑/기쁨">사랑/기쁨</a></li>
						<li><a href="recommand.do?feel=이별/슬픔">이별/슬픔</a></li>
						<li><a href="recommand.do?feel=스트레스/짜증">스트레스/짜증</a></li>
						<li><a href="recommand.do?feel=우울할때">우울할때</a></li>
						<li><a href="recommand.do?feel=멘붕/불안">멘붕/불안</a></li>
						<li><a href="recommand.do?feel=외로울때">외로울때</a></li>
					</ul>
				</li> -->
			</ul>
		</div>
	</div>	
</body>
</html>